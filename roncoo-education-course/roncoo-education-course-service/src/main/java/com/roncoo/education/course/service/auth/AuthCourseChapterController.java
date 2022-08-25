package com.roncoo.education.course.service.auth;

import com.roncoo.education.course.service.auth.biz.AuthCourseChapterBiz;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * AUTH-章节信息
 *
 * @author wujing
 * @date 2022-08-25
 */
@Api(tags = "AUTH-章节信息")
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/auth/course/chapter")
public class AuthCourseChapterController {

    @NotNull
    private final AuthCourseChapterBiz biz;

}
