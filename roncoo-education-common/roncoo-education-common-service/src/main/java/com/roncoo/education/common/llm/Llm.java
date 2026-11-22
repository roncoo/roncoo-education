package com.roncoo.education.common.llm;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * LLM 配置
 *
 * <p>统一使用协议维度的配置字段，不再按服务商拆分。
 * 任何兼容对应协议的服务商均可通过这组字段接入。
 *
 * @author roncoo
 */
@Data
@Accessors(chain = true)
public class Llm implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * LLM平台/协议类型，参考：LlmPlatformEnum
     */
    private Integer llmPlatform;

    /**
     * API Key（Bearer Token 或 x-api-key，由实现层按协议填充对应请求头）
     */
    private String aiApiKey;

    /**
     * API 基础地址（不含路径后缀，实现层自动拼接 /chat/completions 等）
     * 为空时各实现使用自身默认地址
     */
    private String aiBaseUrl;

    /**
     * 模型名称，如 gpt-4o、claude-3-5-sonnet-20241022、qwen-max 等
     */
    private String aiModel;

    /**
     * 温度参数，控制输出随机性，范围 0.0~2.0，默认 0.7
     */
    private Double temperature;

    /**
     * 最大输出 token 数，默认 2000
     */
    private Integer maxTokens;

    /**
     * 请求超时（秒），默认 60
     */
    private Integer timeout;
}
