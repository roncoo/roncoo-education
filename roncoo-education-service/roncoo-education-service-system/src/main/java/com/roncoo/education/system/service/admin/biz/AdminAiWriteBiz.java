package com.roncoo.education.system.service.admin.biz;

import cn.hutool.core.util.IdUtil;
import com.roncoo.education.common.base.BaseBiz;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.LlmPlatformEnum;
import com.roncoo.education.common.core.enums.WriteTypeEnum;
import com.roncoo.education.common.llm.Llm;
import com.roncoo.education.common.llm.LlmHelper;
import com.roncoo.education.common.llm.LlmRequest;
import com.roncoo.education.common.llm.LlmUtil;
import com.roncoo.education.system.service.admin.req.AdminAiWriteCompletionsReq;
import com.roncoo.education.system.service.admin.req.AdminAiWriteCreateReq;
import com.roncoo.education.system.service.biz.SysConfigCommonBiz;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;

import java.io.IOException;
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
    private final SysConfigCommonBiz sysConfigCommonBiz;

    @NotNull
    private final LlmHelper llmHelper;

    /**
     * 创建AI写作任务，返回aiToken
     */
    public Result<String> create(AdminAiWriteCreateReq req) {
        Llm llm = sysConfigCommonBiz.getSysConfig(Llm.class);
        if (llm == null || !StringUtils.hasText(llm.getAiApiKey())) {
            return Result.error("AI功能未配置，请在系统配置中设置 aiApiKey");
        }

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
            LlmUtil.writeSseError(response, "任务已过期，请重新创建");
            return;
        }

        // 解析任务数据
        String[] parts = taskData.split("\\|\\|", 2);
        if (parts.length < 2) {
            LlmUtil.writeSseError(response, "任务数据异常");
            return;
        }
        int writeType = Integer.parseInt(parts[0]);
        String userPrompt = parts[1];

        // 读取AI配置
        Llm llm = sysConfigCommonBiz.getSysConfig(Llm.class);
        if (llm == null || !StringUtils.hasText(llm.getAiApiKey())) {
            LlmUtil.writeSseError(response, "AI功能未配置");
            return;
        }
        // 默认使用 OpenAI 兼容协议
        if (llm.getLlmPlatform() == null) {
            llm.setLlmPlatform(LlmPlatformEnum.OPENAI_COMPATIBLE.getCode());
        }

        // 构建系统提示词
        String systemPrompt = buildSystemPrompt(WriteTypeEnum.byCode(writeType));

        // 创建 LLM 请求
        LlmRequest llmRequest = LlmRequest.simple(systemPrompt, userPrompt);
        llmRequest.setStream(true);
        if (StringUtils.hasText(llm.getAiModel())) {
            llmRequest.setModel(llm.getAiModel());
        }

        // 获取流式响应并输出
        Flux<String> streamResponse = llmHelper.chatStream(llmRequest, llm);
        LlmUtil.streamToResponse(streamResponse, response);
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
}
