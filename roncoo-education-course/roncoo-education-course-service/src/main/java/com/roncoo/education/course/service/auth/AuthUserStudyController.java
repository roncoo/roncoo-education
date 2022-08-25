package com.roncoo.education.course.service.auth;

import com.roncoo.education.course.service.auth.biz.AuthUserStudyBiz;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * AUTH-课程用户学习日志
 *
 * @author wujing
 * @date 2022-08-25
 */
@Api(tags = "AUTH-课程用户学习日志")
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/auth/user/study")
public class AuthUserStudyController {

    @NotNull
    private final AuthUserStudyBiz biz;

}
