package com.roncoo.education.course.service.auth;

import com.roncoo.education.course.service.auth.biz.AuthUserCourseBiz;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * AUTH-课程用户关联表
 *
 * @author wujing
 * @date 2022-08-25
 */
@Api(tags = "AUTH-课程用户关联表")
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/auth/user/course")
public class AuthUserCourseController {

    @NotNull
    private final AuthUserCourseBiz biz;

}
