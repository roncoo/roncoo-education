package com.roncoo.education.system.service.admin;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.admin.biz.AdminAiWriteBiz;
import com.roncoo.education.system.service.admin.req.AdminAiWriteCompletionsReq;
import com.roncoo.education.system.service.admin.req.AdminAiWriteCreateReq;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * ADMIN-AI写作
 *
 * @author roncoo
 */
@Tag(name = "admin-AI写作")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/admin/ai/write")
public class AdminAiWriteController {

    @NotNull
    private final AdminAiWriteBiz biz;

    @Operation(summary = "创建AI写作任务", description = "创建AI写作任务，返回aiToken用于获取流式内容")
    @PostMapping(value = "/create")
    public Result<String> create(@RequestBody @Valid AdminAiWriteCreateReq req) {
        return biz.create(req);
    }

    @Operation(summary = "获取AI写作流式内容", description = "通过SSE流式获取AI写作内容")
    @GetMapping(value = "/completions")
    public void completions(@Valid AdminAiWriteCompletionsReq req, HttpServletResponse response) throws IOException {
        biz.completions(req, response);
    }
}
