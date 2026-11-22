package com.roncoo.education.common.llm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * LLM 请求对象
 *
 * @author roncoo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LlmRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 消息列表
     */
    private List<LlmMessage> messages;

    /**
     * 模型名称（可选，覆盖配置中的模型）
     */
    private String model;

    /**
     * 温度参数（可选，覆盖配置中的温度）
     */
    private Double temperature;

    /**
     * 最大token数（可选，覆盖配置中的最大token数）
     */
    private Integer maxTokens;

    /**
     * 是否流式输出
     */
    @Builder.Default
    private Boolean stream = false;

    /**
     * 添加消息
     */
    public LlmRequest addMessage(LlmMessage message) {
        if (this.messages == null) {
            this.messages = new ArrayList<>();
        }
        this.messages.add(message);
        return this;
    }

    /**
     * 添加系统消息
     */
    public LlmRequest addSystemMessage(String content) {
        return addMessage(LlmMessage.system(content));
    }

    /**
     * 添加用户消息
     */
    public LlmRequest addUserMessage(String content) {
        return addMessage(LlmMessage.user(content));
    }

    /**
     * 添加助手消息
     */
    public LlmRequest addAssistantMessage(String content) {
        return addMessage(LlmMessage.assistant(content));
    }

    /**
     * 创建简单请求（系统提示词 + 用户消息）
     */
    public static LlmRequest simple(String systemPrompt, String userMessage) {
        return LlmRequest.builder()
                .messages(new ArrayList<>())
                .build()
                .addSystemMessage(systemPrompt)
                .addUserMessage(userMessage);
    }

    /**
     * 创建简单用户请求（仅用户消息）
     */
    public static LlmRequest user(String userMessage) {
        return LlmRequest.builder()
                .messages(new ArrayList<>())
                .build()
                .addUserMessage(userMessage);
    }
}
