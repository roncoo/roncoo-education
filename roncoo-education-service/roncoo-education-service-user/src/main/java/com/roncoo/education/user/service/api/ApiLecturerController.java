package com.roncoo.education.user.service.api;

import com.roncoo.education.common.base.page.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.service.api.biz.ApiLecturerBiz;
import com.roncoo.education.user.service.api.req.LecturerPageReq;
import com.roncoo.education.user.service.api.resp.LecturerPageResp;
import com.roncoo.education.user.service.api.resp.LecturerViewResp;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.NotNull;

/**
 * API-讲师信息
 *
 * @author fengyw
 */
@Tag(name = "api-讲师信息")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/api/lecturer")
public class ApiLecturerController {

    @NotNull
    private final ApiLecturerBiz biz;

    @Operation(summary = "讲师列出")
    @PostMapping(value = "/search")
    public Result<Page<LecturerPageResp>> search(@RequestBody LecturerPageReq req) {
        return biz.search(req);
    }

    @Operation(summary = "讲师信息查看")
    @Parameter(name = "id", description = "主键ID", required = true)
    @GetMapping(value = "/view")
    public Result<LecturerViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

}
