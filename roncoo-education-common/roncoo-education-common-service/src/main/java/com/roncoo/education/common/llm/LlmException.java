package com.roncoo.education.common.llm;

/**
 * LLM 异常
 *
 * @author roncoo
 */
public class LlmException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public LlmException(String message) {
        super(message);
    }

    public LlmException(String message, Throwable cause) {
        super(message, cause);
    }

    public LlmException(Throwable cause) {
        super(cause);
    }
}
