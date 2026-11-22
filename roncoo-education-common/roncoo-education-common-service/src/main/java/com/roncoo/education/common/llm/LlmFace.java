package com.roncoo.education.common.llm;

import reactor.core.publisher.Flux;

/**
 * LLM 功能接口
 *
 * @author roncoo
 */
public interface LlmFace {

    /**
     * 同步调用
     *
     * @param request LLM请求对象
     * @param config  LLM配置
     * @return LLM响应对象
     */
    LlmResponse chat(LlmRequest request, Llm config);

    /**
     * 流式调用
     *
     * @param request LLM请求对象
     * @param config  LLM配置
     * @return 内容流
     */
    Flux<String> chatStream(LlmRequest request, Llm config);

    /**
     * 测试连接
     *
     * @param config LLM配置
     * @return 连接是否正常
     */
    boolean testConnection(Llm config);
}
