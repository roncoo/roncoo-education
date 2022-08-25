package com.roncoo.education.user.service.auth;

import com.roncoo.education.user.service.auth.biz.AuthLecturerBiz;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * AUTH-讲师信息
 *
 * @author wujing
 * @date 2022-08-25
 */
@Api(tags = "AUTH-讲师信息")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/auth/lecturer")
public class AuthLecturerController {

    @NotNull
    private final AuthLecturerBiz biz;

}
