package com.roncoo.education.user.service.api;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.service.api.biz.ApiLecturerBiz;
import com.roncoo.education.user.service.api.req.LecturerPageReq;
import com.roncoo.education.user.service.api.resp.LecturerPageResp;
import com.roncoo.education.user.service.api.resp.LecturerViewResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.constraints.NotNull;

/**
 * API-讲师信息
 *
 * @author fengyw
 */
@Api(tags = "api-讲师信息")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/api/lecturer")
public class ApiLecturerController {

    @NotNull
    private final ApiLecturerBiz biz;

    @ApiOperation(value = "讲师列出", notes = "讲师列出")
    @PostMapping(value = "/search")
    public Result<Page<LecturerPageResp>> search(@RequestBody LecturerPageReq req) {
        return biz.search(req);
    }

    @ApiOperation(value = "讲师信息查看", notes = "讲师信息查看")
    @ApiImplicitParam(name = "id", value = "主键ID", dataTypeClass = Long.class, paramType = "query", required = true)
    @GetMapping(value = "/view")
    public Result<LecturerViewResp> view(@RequestParam Long id) {
        return biz.view(id);
    }

}
