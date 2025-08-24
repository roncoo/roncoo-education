package com.roncoo.education.system.service.api;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.api.biz.ApiWebsiteAppBiz;
import com.roncoo.education.system.service.api.req.ApiWebsiteAppReq;
import com.roncoo.education.system.service.api.resp.ApiWebsiteAppResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import jakarta.validation.constraints.NotNull;

/**
 * API-app版本管理
 *
 * @author fengyw
 * @date 2024-06-07
 */
@Api(tags = "api-app版本管理")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/api/website/app")
public class ApiWebsiteAppController {

    @NotNull
    private final ApiWebsiteAppBiz biz;

    @ApiOperation(value = "APP版本检测", notes = "返回APP版本信息")
    @PostMapping(value = "/check")
    public Result<ApiWebsiteAppResp> check(@RequestBody @Valid ApiWebsiteAppReq req) {
        return biz.check(req);
    }

}
