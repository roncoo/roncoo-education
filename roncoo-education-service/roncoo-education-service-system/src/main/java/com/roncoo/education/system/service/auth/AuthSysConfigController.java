package com.roncoo.education.system.service.auth;

import com.roncoo.education.system.service.auth.biz.AuthSysConfigBiz;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * AUTH-系统配置
 *
 * @author wujing
 * @date 2022-08-25
 */
@Api(tags = "auth-系统配置")
@RestController
@RequiredArgsConstructor
@RequestMapping("/system/auth/sys/config")
public class AuthSysConfigController {

    @NotNull
    private final AuthSysConfigBiz biz;

}
