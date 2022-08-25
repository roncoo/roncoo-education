package com.roncoo.education.course.service.auth;

import com.roncoo.education.course.service.auth.biz.AuthCourseChapterPeriodBiz;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * AUTH-课时信息
 *
 * @author wujing
 * @date 2022-08-25
 */
@Api(tags = "AUTH-课时信息")
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/auth/course/chapter/period")
public class AuthCourseChapterPeriodController {

    @NotNull
    private final AuthCourseChapterPeriodBiz biz;

}
