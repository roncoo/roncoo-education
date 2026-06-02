package com.roncoo.education.common.llm;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * LLM 响应对象
 *
 * @author roncoo
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LlmResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 生成的内容
     */
    private String content;

    /**
     * 使用的模型
     */
    private String model;

    /**
     * token 使用情况
     */
    private TokenUsage tokenUsage;

    /**
     * 完成原因
     */
    private String finishReason;

    /**
     * Token 使用情况
     */
    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TokenUsage implements Serializable {
        private static final long serialVersionUID = 1L;

        /**
         * 提示词 token 数
         */
        private Integer promptTokens;

        /**
         * 完成内容 token 数
         */
        private Integer completionTokens;

        /**
         * 总 token 数
         */
        private Integer totalTokens;
    }

    /**
     * 判断是否成功
     */
    public boolean isSuccess() {
        return content != null && !content.isEmpty();
    }
}
