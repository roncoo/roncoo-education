package com.roncoo.education.common.llm.impl;

import com.roncoo.education.common.llm.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.anthropic.AnthropicChatModel;
import org.springframework.ai.anthropic.AnthropicChatOptions;
import org.springframework.ai.anthropic.api.AnthropicApi;
import org.springframework.ai.chat.messages.AssistantMessage;
import org.springframework.ai.chat.messages.Message;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.metadata.Usage;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;

import java.util.ArrayList;
import java.util.List;

/**
 * Anthropic Messages 协议实现（/v1/messages）
 *
 * <p>适用于 Anthropic Claude 系列模型（claude-sonnet-4-5、claude-opus-4-0 等）。
 * apiUrl 为空时使用 Anthropic 官方地址，也支持兼容 Anthropic 协议的代理端点。
 *
 * @author roncoo
 */
@Slf4j
@Component(value = "ANTHROPIC_MESSAGES")
public class AnthropicMessagesLlmImpl implements LlmFace {

    /** 1.1.x 默认模型跟随官方文档，显式写出避免隐式依赖 */
    private static final String DEFAULT_MODEL = "claude-sonnet-4-5";
    private static final String DEFAULT_BASE_URL = "https://api.anthropic.com";
    private static final double DEFAULT_TEMPERATURE = 0.7;
    private static final int DEFAULT_MAX_TOKENS = 4096;

    @Override
    public LlmResponse chat(LlmRequest request, Llm config) {
        try {
            AnthropicChatModel chatModel = buildChatModel(request, config);
            ChatResponse response = chatModel.call(new Prompt(toMessages(request)));
            return toResponse(response);
        } catch (Exception e) {
            log.error("Anthropic Messages同步调用失败, url={}", config.getAiBaseUrl(), e);
            return LlmResponse.builder().content("").finishReason("error").build();
        }
    }

    @Override
    public Flux<String> chatStream(LlmRequest request, Llm config) {
        try {
            AnthropicChatModel chatModel = buildChatModel(request, config);
            return chatModel.stream(new Prompt(toMessages(request)))
                    .mapNotNull(resp -> {
                        if (resp.getResult() == null || resp.getResult().getOutput() == null) {
                            return null;
                        }
                        return resp.getResult().getOutput().getText();
                    })
                    .filter(StringUtils::hasText);
        } catch (Exception e) {
            log.error("Anthropic Messages流式调用失败, url={}", config.getAiBaseUrl(), e);
            return Flux.error(e);
        }
    }

    @Override
    public boolean testConnection(Llm config) {
        try {
            LlmResponse resp = chat(LlmRequest.user("Hi"), config);
            return resp.isSuccess();
        } catch (Exception e) {
            log.error("Anthropic Messages连接测试失败", e);
            return false;
        }
    }

    // -------------------------------------------------------------------------

    private AnthropicChatModel buildChatModel(LlmRequest request, Llm config) {
        String baseUrl = StringUtils.hasText(config.getAiBaseUrl()) ? config.getAiBaseUrl() : DEFAULT_BASE_URL;

        AnthropicApi anthropicApi = AnthropicApi.builder()
                .baseUrl(baseUrl)
                .apiKey(config.getAiApiKey())
                .build();

        AnthropicChatOptions options = AnthropicChatOptions.builder()
                .model(resolveModel(request, config))
                .temperature(resolveTemperature(request, config))
                .maxTokens(resolveMaxTokens(request, config))
                .build();

        return AnthropicChatModel.builder()
                .anthropicApi(anthropicApi)
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
        LlmResponse.TokenUsage tokenUsage = null;

        if (resp.getMetadata() != null) {
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
                .tokenUsage(tokenUsage)
                .finishReason(finishReason)
                .build();
    }

    private String resolveModel(LlmRequest request, Llm config) {
        if (StringUtils.hasText(request.getModel())) {
            return request.getModel();
        }
        return StringUtils.hasText(config.getAiModel()) ? config.getAiModel() : DEFAULT_MODEL;
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
