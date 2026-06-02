package com.roncoo.education.common.llm;

import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * LLM 工具类
 *
 * @author roncoo
 */
@Slf4j
public class LlmUtil {

    /**
     * SSE 流式输出到 HttpServletResponse
     *
     * @param flux     内容流
     * @param response HTTP响应
     * @throws IOException IO异常
     */
    public static void streamToResponse(Flux<String> flux, HttpServletResponse response) throws IOException {
        // 设置SSE响应头
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Connection", "keep-alive");
        response.setHeader("Access-Control-Allow-Origin", "*");

        PrintWriter writer = response.getWriter();

        try {
            // 处理流式响应
            flux.doOnNext(content -> {
                        try {
                            if (content != null && !content.isEmpty()) {
                                // SSE 每条消息以 \n\n 结尾，content 中的换行需编码为 \\n 避免破坏协议格式
                                String encoded = encodeForSse(content);
                                writer.write("data: " + encoded + "\n\n");
                                writer.flush();
                            }
                        } catch (Exception e) {
                            log.error("写入SSE响应失败", e);
                        }
                    })
                    .doOnError(error -> {
                        log.error("LLM流式请求异常", error);
                        try {
                            writeSseError(response, "LLM服务异常: " + error.getMessage());
                        } catch (IOException e) {
                            log.error("写入错误响应失败", e);
                        }
                    })
                    .doOnComplete(() -> {
                        try {
                            writer.write("data: [DONE]\n\n");
                            writer.flush();
                        } catch (Exception e) {
                            log.error("写入完成标记失败", e);
                        }
                    })
                    .blockLast(); // 阻塞等待流完成
        } catch (Exception e) {
            log.error("LLM流式输出异常", e);
            writeSseError(response, "流式输出异常");
        }
    }

    /**
     * 为 SSE 编码内容
     */
    public static String encodeForSse(String content) {
        if (content == null) {
            return "";
        }
        return content.replace("\\", "\\\\")
                .replace("\n", "\\n")
                .replace("\r", "");
    }

    /**
     * 从 SSE 解码内容
     */
    public static String decodeFromSse(String encoded) {
        if (encoded == null) {
            return "";
        }
        return encoded.replace("\\n", "\n")
                .replace("\\\\", "\\");
    }

    /**
     * 写入 SSE 错误消息
     */
    public static void writeSseError(HttpServletResponse response, String message) throws IOException {
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write("data: [ERROR]" + message + "\n\n");
        writer.flush();
    }

    /**
     * 计算文本的大概 token 数（英文约为 4 字符 = 1 token，中文约为 1.5 字符 = 1 token）
     */
    public static int estimateTokens(String text) {
        if (text == null || text.isEmpty()) {
            return 0;
        }

        int chineseCount = 0;
        int englishCount = 0;

        for (char c : text.toCharArray()) {
            if (isChinese(c)) {
                chineseCount++;
            } else {
                englishCount++;
            }
        }

        // 中文约 1.5 字符 = 1 token，英文约 4 字符 = 1 token
        return (int) Math.ceil(chineseCount / 1.5) + (int) Math.ceil(englishCount / 4.0);
    }

    /**
     * 判断字符是否为中文
     */
    private static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        return ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS;
    }

    /**
     * 截断文本到指定 token 数
     */
    public static String truncateToTokens(String text, int maxTokens) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        int estimatedTokens = estimateTokens(text);
        if (estimatedTokens <= maxTokens) {
            return text;
        }

        // 粗略计算需要保留的字符数
        double ratio = (double) maxTokens / estimatedTokens;
        int targetLength = (int) (text.length() * ratio);

        return text.substring(0, Math.min(targetLength, text.length())) + "...";
    }
}
