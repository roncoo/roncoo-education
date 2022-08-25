package com.roncoo.education.user.service.auth;

import com.roncoo.education.user.service.auth.biz.AuthLogLoginBiz;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * AUTH-用户登录日志
 *
 * @author wujing
 * @date 2022-08-25
 */
@Api(tags = "AUTH-用户登录日志")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/auth/log/login")
public class AuthLogLoginController {

    @NotNull
    private final AuthLogLoginBiz biz;

}
