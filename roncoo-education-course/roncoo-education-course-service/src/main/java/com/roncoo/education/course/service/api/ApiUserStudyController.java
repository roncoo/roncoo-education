package com.roncoo.education.course.service.api;

import com.roncoo.education.course.service.api.biz.ApiUserStudyBiz;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * API-课程用户学习日志
 *
 * @author wujing
 * @date 2022-09-03
 */
@Api(tags = "API-课程用户学习日志")
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/api/user/study")
public class ApiUserStudyController {

    @NotNull
    private final ApiUserStudyBiz biz;

}
