package com.roncoo.education.system.service.api;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.api.biz.ApiWebsiteAppBiz;
import com.roncoo.education.system.service.api.req.ApiWebsiteAppReq;
import com.roncoo.education.system.service.api.resp.ApiWebsiteAppResp;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * API-app版本管理
 *
 * @author fengyw
 * @date 2024-06-07
 */
@Tag(name = "api-app版本管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/api/website/app")
public class ApiWebsiteAppController {

    @NotNull
    private final ApiWebsiteAppBiz biz;

    @Operation(summary = "APP版本检测", description = "返回APP版本信息")
    @PostMapping(value = "/check")
    public Result<ApiWebsiteAppResp> check(@RequestBody @Valid ApiWebsiteAppReq req) {
        return biz.check(req);
    }

}
