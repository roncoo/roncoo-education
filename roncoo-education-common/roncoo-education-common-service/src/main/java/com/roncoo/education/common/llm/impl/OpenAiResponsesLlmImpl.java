package com.roncoo.education.common.llm.impl;

import com.roncoo.education.common.llm.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.metadata.Usage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.ai.openai.OpenAiChatOptions;
import org.springframework.ai.openai.api.OpenAiApi;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

/**
 * OpenAI Responses API 实现（/v1/responses）
 *
 * <p>OpenAI 2025 年推出的新一代 Responses API，支持内置工具（web search、
 * code interpreter 等）及多轮状态管理（store=true）。
 * Spring AI 1.1.x 通过 {@link OpenAiChatOptions#getStore()} 路由到 /v1/responses 端点。
 *
 * @author roncoo
 */
@Slf4j
@Component(value = "OPENAI_RESPONSES")
public class OpenAiResponsesLlmImpl implements LlmFace {

    private static final String DEFAULT_BASE_URL = "https://api.openai.com";
    private static final double DEFAULT_TEMPERATURE = 0.7;
    private static final int DEFAULT_MAX_TOKENS = 2000;

    @Override
    public LlmResponse chat(LlmRequest request, Llm config) {
        try {
            OpenAiChatModel chatModel = buildChatModel(request, config);
            ChatResponse response = chatModel.call(new Prompt(toMessages(request)));
            return toResponse(response);
        } catch (Exception e) {
            log.error("OpenAI Responses同步调用失败, url={}", config.getAiBaseUrl(), e);
            return LlmResponse.builder().content("").finishReason("error").build();
        }
    }

    @Override
    public Flux<String> chatStream(LlmRequest request, Llm config) {
        try {
            OpenAiChatModel chatModel = buildChatModel(request, config);
            return chatModel.stream(new Prompt(toMessages(request)))
                    .mapNotNull(resp -> {
                        if (resp.getResult() == null || resp.getResult().getOutput() == null) {
                            return null;
                        }
                        return resp.getResult().getOutput().getText();
                    })
                    .filter(StringUtils::hasText);
        } catch (Exception e) {
            log.error("OpenAI Responses流式调用失败, url={}", config.getAiBaseUrl(), e);
            return Flux.error(e);
        }
    }

    @Override
    public boolean testConnection(Llm config) {
        try {
            LlmResponse resp = chat(LlmRequest.user("Hi"), config);
            return resp.isSuccess();
        } catch (Exception e) {
            log.error("OpenAI Responses连接测试失败", e);
            return false;
        }
    }

    // -------------------------------------------------------------------------

    private OpenAiChatModel buildChatModel(LlmRequest request, Llm config) {
        String baseUrl = StringUtils.hasText(config.getAiBaseUrl()) ? config.getAiBaseUrl() : DEFAULT_BASE_URL;

        OpenAiApi openAiApi = OpenAiApi.builder()
                .baseUrl(baseUrl)
                .apiKey(config.getAiApiKey())
                .build();

        // store=true 告知 Spring AI 使用 /v1/responses 端点（Responses API）
        OpenAiChatOptions options = OpenAiChatOptions.builder()
                .model(resolveModel(request, config))
                .temperature(resolveTemperature(request, config))
                .maxTokens(resolveMaxTokens(request, config))
                .store(true)
                .build();

        return OpenAiChatModel.builder()
                .openAiApi(openAiApi)
                .defaultOptions(options)
                .build();
    }

    /**
     * 将 LlmMessage 列表转换为 Spring AI Message 列表
     */
    private List<Message> toMessages(LlmRequest request) {
        List<Message> messages = new ArrayList<>();
        for (LlmMessage msg : request.getMessages()) {
            messages.add(switch (msg.getRole()) {
                case SYSTEM -> new SystemMessage(msg.getContent());
                case ASSISTANT -> new AssistantMessage(msg.getContent());
                default -> new UserMessage(msg.getContent());
            });
        }
        return messages;
    }

    private LlmResponse toResponse(ChatResponse resp) {
        String content = resp.getResult().getOutput().getText();
        String finishReason = resp.getResult().getMetadata().getFinishReason();
        String model = null;
        LlmResponse.TokenUsage tokenUsage = null;

        if (resp.getMetadata() != null) {
            model = resp.getMetadata().getModel();
            Usage usage = resp.getMetadata().getUsage();
            if (usage != null) {
                tokenUsage = LlmResponse.TokenUsage.builder()
                        .promptTokens(usage.getPromptTokens() != null ? usage.getPromptTokens().intValue() : 0)
                        .completionTokens(usage.getCompletionTokens() != null ? usage.getCompletionTokens().intValue() : 0)
                        .totalTokens(usage.getTotalTokens() != null ? usage.getTotalTokens().intValue() : 0)
                        .build();
            }
        }

        return LlmResponse.builder()
                .content(content)
                .model(model)
                .tokenUsage(tokenUsage)
                .finishReason(finishReason)
                .build();
    }

    private String resolveModel(LlmRequest request, Llm config) {
        if (StringUtils.hasText(request.getModel())) {
            return request.getModel();
        }
        return StringUtils.hasText(config.getAiModel()) ? config.getAiModel() : "gpt-4o";
    }

    private double resolveTemperature(LlmRequest request, Llm config) {
        if (request.getTemperature() != null) return request.getTemperature();
        return config.getTemperature() != null ? config.getTemperature() : DEFAULT_TEMPERATURE;
    }

    private int resolveMaxTokens(LlmRequest request, Llm config) {
        if (request.getMaxTokens() != null) return request.getMaxTokens();
        return config.getMaxTokens() != null ? config.getMaxTokens() : DEFAULT_MAX_TOKENS;
    }
}
