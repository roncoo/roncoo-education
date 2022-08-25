package com.roncoo.education.system.service.api;

import com.roncoo.education.system.service.api.biz.ApiSysConfigBiz;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * API-系统配置
 *
 * @author wujing
 * @date 2022-08-25
 */
@Api(tags = "API-系统配置")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/api/sys/config")
public class ApiSysConfigController {

    @NotNull
    private final ApiSysConfigBiz biz;

}
