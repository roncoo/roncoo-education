package com.roncoo.education.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * LLM平台
 *
 * @author roncoo
 */
@Getter
@AllArgsConstructor
public enum LlmPlatformEnum {

    /**
     * OpenAI 及兼容协议（/chat/completions），适用于：OpenAI、阿里百炼/通义千问、DeepSeek、月之暗面等
     */
    OPENAI_COMPATIBLE(1, "OpenAI兼容协议", "openai_compatible%", "OPENAI_COMPATIBLE"),

    /**
     * Anthropic Messages 协议（/messages），适用于：Claude 系列
     */
    ANTHROPIC_MESSAGES(2, "Anthropic Messages协议", "anthropic%", "ANTHROPIC_MESSAGES"),

    /**
     * OpenAI Responses API（/responses），适用于：OpenAI 新版 Responses 接口
     */
    OPENAI_RESPONSES(3, "OpenAI Responses协议", "openai_responses%", "OPENAI_RESPONSES");

    private Integer code;

    private String desc;

    /**
     * 标记
     */
    private String tag;

    /**
     * 模式
     */
    private String mode;

    /**
     * 根据编码获取枚举信息
     *
     * @param code 编码
     * @return 枚举信息
     */
    public static LlmPlatformEnum byCode(Integer code) {
        for (LlmPlatformEnum val : LlmPlatformEnum.values()) {
            if (val.getCode().equals(code)) {
                return val;
            }
        }
        return null;
    }
}
