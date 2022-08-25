package com.roncoo.education.course.service.api;

import com.roncoo.education.course.service.api.biz.ApiCourseBiz;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * API-课程信息
 *
 * @author wujing
 * @date 2022-08-25
 */
@Api(tags = "API-课程信息")
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/api/course")
public class ApiCourseController {

    @NotNull
    private final ApiCourseBiz biz;

}
