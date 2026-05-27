package com.roncoo.education.system.service.api;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.service.api.biz.ApiSysConfigBiz;
import com.roncoo.education.system.service.api.resp.ApiSysConfigWebsiteResp;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.NotNull;

/**
 * AUTH-系统配置
 *
 * @author wujing
 * @date 2022-08-25
 */
@Tag(name = "api-系统配置")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/api/sys/config")
public class ApiSysConfigController {

    @NotNull
    private final ApiSysConfigBiz biz;

    @Operation(summary = "网站基本信息")
    @GetMapping(value = "/website")
    public Result<ApiSysConfigWebsiteResp> website() {
        return biz.website();
    }

}
