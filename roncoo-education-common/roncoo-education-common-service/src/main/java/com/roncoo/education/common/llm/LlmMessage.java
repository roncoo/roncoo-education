package com.roncoo.education.common.llm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * LLM 消息对象
 *
 * @author roncoo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LlmMessage implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 消息角色
     */
    private MessageRole role;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息角色枚举
     */
    public enum MessageRole {
        /**
         * 系统消息（系统提示词）
         */
        SYSTEM("system"),

        /**
         * 用户消息
         */
        USER("user"),

        /**
         * 助手消息（AI回复）
         */
        ASSISTANT("assistant");

        private final String value;

        MessageRole(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    /**
     * 创建系统消息
     */
    public static LlmMessage system(String content) {
        return LlmMessage.builder()
                .role(MessageRole.SYSTEM)
                .content(content)
                .build();
    }

    /**
     * 创建用户消息
     */
    public static LlmMessage user(String content) {
        return LlmMessage.builder()
                .role(MessageRole.USER)
                .content(content)
                .build();
    }

    /**
     * 创建助手消息
     */
    public static LlmMessage assistant(String content) {
        return LlmMessage.builder()
                .role(MessageRole.ASSISTANT)
                .content(content)
                .build();
    }
}
