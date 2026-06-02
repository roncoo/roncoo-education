package com.roncoo.education.common.llm;

import com.roncoo.education.common.core.enums.LlmPlatformEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.util.Map;

/**
 * LLM 辅助工具类，提供便捷的 LLM 调用方法
 *
 * @author roncoo
 */
@Slf4j
@Component
public class LlmHelper {

    private final Map<String, LlmFace> llmFaceMap;

    public LlmHelper(Map<String, LlmFace> llmFaceMap) {
        this.llmFaceMap = llmFaceMap;
    }

    /**
     * 根据配置获取对应协议的 LlmFace 实现
     *
     * @throws LlmException 平台未配置或实现未注册时抛出
     */
    public LlmFace getLlmFace(Llm config) {
        if (config == null || config.getLlmPlatform() == null) {
            throw new LlmException("LLM配置为空或未指定平台");
        }
        LlmPlatformEnum platformEnum = LlmPlatformEnum.byCode(config.getLlmPlatform());
        if (platformEnum == null) {
            throw new LlmException("不支持的LLM平台: " + config.getLlmPlatform());
        }
        LlmFace face = llmFaceMap.get(platformEnum.getMode());
        if (face == null) {
            throw new LlmException("未找到LLM实现: " + platformEnum.getMode());
        }
        return face;
    }

    /**
     * 同步调用 - 仅用户消息
     */
    public LlmResponse chat(String userMessage, Llm config) {
        return getLlmFace(config).chat(LlmRequest.user(userMessage), config);
    }

    /**
     * 同步调用 - 系统提示词 + 用户消息
     */
    public LlmResponse chat(String systemPrompt, String userMessage, Llm config) {
        return getLlmFace(config).chat(LlmRequest.simple(systemPrompt, userMessage), config);
    }

    /**
     * 同步调用 - 完整请求
     */
    public LlmResponse chat(LlmRequest request, Llm config) {
        return getLlmFace(config).chat(request, config);
    }

    /**
     * 流式调用 - 仅用户消息
     */
    public Flux<String> chatStream(String userMessage, Llm config) {
        LlmRequest request = LlmRequest.user(userMessage);
        request.setStream(true);
        return getLlmFace(config).chatStream(request, config);
    }

    /**
     * 流式调用 - 系统提示词 + 用户消息
     */
    public Flux<String> chatStream(String systemPrompt, String userMessage, Llm config) {
        LlmRequest request = LlmRequest.simple(systemPrompt, userMessage);
        request.setStream(true);
        return getLlmFace(config).chatStream(request, config);
    }

    /**
     * 流式调用 - 完整请求
     */
    public Flux<String> chatStream(LlmRequest request, Llm config) {
        request.setStream(true);
        return getLlmFace(config).chatStream(request, config);
    }

    /**
     * 测试连接
     */
    public boolean testConnection(Llm config) {
        try {
            return getLlmFace(config).testConnection(config);
        } catch (Exception e) {
            log.error("LLM连接测试失败", e);
            return false;
        }
    }

    /**
     * 检查配置是否有效（平台有值且有对应实现）
     */
    public boolean isConfigValid(Llm config) {
        if (config == null || config.getLlmPlatform() == null) {
            return false;
        }
        try {
            getLlmFace(config);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
