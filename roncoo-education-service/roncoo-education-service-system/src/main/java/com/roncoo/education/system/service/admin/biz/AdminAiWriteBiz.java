package com.roncoo.education.system.service.admin.biz;

import cn.hutool.core.util.IdUtil;
import com.roncoo.education.common.base.BaseBiz;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.WriteTypeEnum;
import com.roncoo.education.system.dao.SysConfigDao;
import com.roncoo.education.system.dao.impl.mapper.entity.SysConfig;
import com.roncoo.education.system.service.admin.req.AdminAiWriteCompletionsReq;
import com.roncoo.education.system.service.admin.req.AdminAiWriteCreateReq;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

/**
 * ADMIN-AI写作
 *
 * @author roncoo
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AdminAiWriteBiz extends BaseBiz {

    private static final String AI_TOKEN_PREFIX = "ai:write:token:";
    private static final int AI_TOKEN_EXPIRE_MINUTES = 10;

    @NotNull
    private final SysConfigDao sysConfigDao;

    /**
     * 创建AI写作任务，返回aiToken
     */
    public Result<String> create(AdminAiWriteCreateReq req) {
        // 校验AI配置
        String apiKey = getConfigValue("aiApiKey");
        if (!StringUtils.hasText(apiKey)) {
            return Result.error("AI功能未配置，请在系统配置中设置 aiApiKey");
        }

        // 生成唯一token，将任务信息存入缓存
        String aiToken = IdUtil.fastSimpleUUID();
        String taskData = req.getWriteType() + "||" + req.getUserPrompt();
        cacheRedis.set(AI_TOKEN_PREFIX + aiToken, taskData, AI_TOKEN_EXPIRE_MINUTES, TimeUnit.MINUTES);

        return Result.success(aiToken);
    }

    /**
     * SSE流式输出AI写作内容
     */
    public void completions(AdminAiWriteCompletionsReq req, HttpServletResponse response) throws IOException {
        // 从缓存获取任务信息
        String taskData = cacheRedis.get(AI_TOKEN_PREFIX + req.getAiToken());
        if (!StringUtils.hasText(taskData)) {
            writeSseError(response, "任务已过期，请重新创建");
            return;
        }

        // 解析任务数据
        String[] parts = taskData.split("\\|\\|", 2);
        if (parts.length < 2) {
            writeSseError(response, "任务数据异常");
            return;
        }
        int writeType = Integer.parseInt(parts[0]);
        String userPrompt = parts[1];

        // 读取AI配置
        String apiKey = getConfigValue("aiApiKey");
        String baseUrl = getConfigValue("aiBaseUrl");
        String model = getConfigValue("aiModel");

        if (!StringUtils.hasText(apiKey)) {
            writeSseError(response, "AI功能未配置");
            return;
        }
        if (!StringUtils.hasText(baseUrl)) {
            baseUrl = "https://api.openai.com";
        }
        if (!StringUtils.hasText(model)) {
            model = "gpt-3.5-turbo";
        }

        // 构建系统提示词
        String systemPrompt = buildSystemPrompt(WriteTypeEnum.byCode(writeType));
        // 构建请求体
        String requestBody = buildRequestBody(model, systemPrompt, userPrompt);

        // 设置SSE响应头
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Connection", "keep-alive");
        response.setHeader("Access-Control-Allow-Origin", "*");

        PrintWriter writer = response.getWriter();

        // 调用OpenAI兼容API（流式）
        String apiUrl = baseUrl.replaceAll("/$", "") + "/v1/chat/completions";
        HttpClient client = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(30))
                .build();
        try {

            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(apiUrl))
                    .header("Content-Type", "application/json")
                    .header("Authorization", "Bearer " + apiKey)
                    .POST(HttpRequest.BodyPublishers.ofString(requestBody, StandardCharsets.UTF_8))
                    .timeout(Duration.ofSeconds(120))
                    .build();

            HttpResponse<java.io.InputStream> httpResponse = client.send(httpRequest,
                    HttpResponse.BodyHandlers.ofInputStream());

            if (httpResponse.statusCode() != 200) {
                String errorBody = new String(httpResponse.body().readAllBytes(), StandardCharsets.UTF_8);
                log.error("AI服务请求失败，状态码：{}，响应体：{}", httpResponse.statusCode(), errorBody);
                writeSseError(response, "AI服务请求失败，状态码：" + httpResponse.statusCode());
                return;
            }

            // 逐行读取SSE流
            try (java.io.BufferedReader reader = new java.io.BufferedReader(
                    new java.io.InputStreamReader(httpResponse.body(), StandardCharsets.UTF_8))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.startsWith("data: ")) {
                        String data = line.substring(6).trim();
                        if ("[DONE]".equals(data)) {
                            writer.write("data: [DONE]\n\n");
                            writer.flush();
                            break;
                        }
                        // 解析JSON提取content
                        String content = extractContent(data);
                        if (content != null) {
                            // SSE 每条消息以 \n\n 结尾，content 中的换行需编码为 \\n 避免破坏协议格式
                            String encoded = content.replace("\\", "\\\\").replace("\n", "\\n").replace("\r", "");
                            writer.write("data: " + encoded + "\n\n");
                            writer.flush();
                        }
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("AI写作请求被中断", e);
            writeSseError(response, "请求被中断");
        } catch (Exception e) {
            log.error("AI写作请求异常", e);
            writeSseError(response, "AI服务异常，请稍后重试");
        }
    }

    /**
     * 根据写作类型构建系统提示词
     */
    private String buildSystemPrompt(WriteTypeEnum writeType) {
        if (writeType == null) {
            writeType = WriteTypeEnum.GENERAL;
        }
        return switch (writeType) {
            case COURSE_INTRO ->
                    "你是一位专业的在线教育课程文案撰写专家。请根据用户的要求，撰写吸引人的课程简介，突出课程价值、学习收益和目标受众。使用Markdown格式输出。";
            case ARTICLE ->
                    "你是一位专业的文章写作专家。请根据用户的要求，撰写结构清晰、内容丰富的文章。使用Markdown格式输出，包含适当的标题层级。";
            case MARKETING ->
                    "你是一位专业的营销文案专家。请根据用户的要求，撰写有吸引力的营销文案，突出产品/服务的核心价值和用户痛点解决方案。使用Markdown格式输出。";
            case SYLLABUS ->
                    "你是一位专业的教学设计专家。请根据用户的要求，设计详细的教学大纲，包含课程目标、章节安排、知识点和学习目标。使用Markdown格式输出。";
            default -> "你是一位专业的内容创作助手。请根据用户的要求，创作高质量的内容。使用Markdown格式输出。";
        };
    }

    /**
     * 构建OpenAI兼容的请求体
     */
    private String buildRequestBody(String model, String systemPrompt, String userPrompt) {
        // 转义特殊字符
        String escapedSystem = escapeJson(systemPrompt);
        String escapedUser = escapeJson(userPrompt);
        return String.format("""
                {
                  "model": "%s",
                  "stream": true,
                  "messages": [
                    {"role": "system", "content": "%s"},
                    {"role": "user", "content": "%s"}
                  ]
                }
                """, model, escapedSystem, escapedUser);
    }

    /**
     * 从OpenAI SSE数据中提取content字段
     */
    private String extractContent(String jsonData) {
        try {
            // 简单解析：找到 "content":"..." 字段
            int idx = jsonData.indexOf("\"content\":");
            if (idx < 0) return null;
            int start = jsonData.indexOf("\"", idx + 10);
            if (start < 0) return null;
            // 处理null值
            String afterKey = jsonData.substring(idx + 10).trim();
            if (afterKey.startsWith("null")) return null;
            start = start + 1;
            // 找结束引号（处理转义）
            StringBuilder sb = new StringBuilder();
            for (int i = start; i < jsonData.length(); i++) {
                char c = jsonData.charAt(i);
                if (c == '\\' && i + 1 < jsonData.length()) {
                    char next = jsonData.charAt(i + 1);
                    switch (next) {
                        case 'n' -> {
                            sb.append('\n');
                            i++;
                        }
                        case 'r' -> {
                            sb.append('\r');
                            i++;
                        }
                        case 't' -> {
                            sb.append('\t');
                            i++;
                        }
                        case '"' -> {
                            sb.append('"');
                            i++;
                        }
                        case '\\' -> {
                            sb.append('\\');
                            i++;
                        }
                        default -> sb.append(c);
                    }
                } else if (c == '"') {
                    break;
                } else {
                    sb.append(c);
                }
            }
            String result = sb.toString();
            return result.isEmpty() ? null : result;
        } catch (Exception e) {
            log.warn("解析AI响应内容失败: {}", jsonData, e);
            return null;
        }
    }

    /**
     * 转义JSON字符串中的特殊字符
     */
    private String escapeJson(String text) {
        if (text == null) return "";
        return text.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r")
                .replace("\t", "\\t");
    }

    /**
     * 写入SSE错误消息
     */
    private void writeSseError(HttpServletResponse response, String message) throws IOException {
        response.setContentType("text/event-stream");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.write("data: [ERROR]" + message + "\n\n");
        writer.flush();
    }

    /**
     * 从系统配置中获取配置值
     */
    private String getConfigValue(String configKey) {
        try {
            SysConfig config = sysConfigDao.getByConfigKey(configKey);
            return config != null ? config.getConfigValue() : null;
        } catch (Exception e) {
            log.warn("获取系统配置失败, key={}", configKey, e);
            return null;
        }
    }
}
