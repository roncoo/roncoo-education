package com.roncoo.education.user.service.auth;

import com.roncoo.education.user.service.auth.biz.AuthUsersBiz;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * AUTH-用户信息
 *
 * @author wujing
 * @date 2022-08-25
 */
@Api(tags = "AUTH-用户信息")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/auth/users")
public class AuthUsersController {

    @NotNull
    private final AuthUsersBiz biz;

}
