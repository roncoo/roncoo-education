package com.roncoo.education.system.service.api;

import com.roncoo.education.system.service.api.biz.ApiWebsiteAppBiz;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

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

}
