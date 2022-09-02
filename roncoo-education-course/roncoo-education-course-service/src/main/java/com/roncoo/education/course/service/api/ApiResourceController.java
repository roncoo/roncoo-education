package com.roncoo.education.course.service.api;

import com.roncoo.education.course.service.api.biz.ApiResourceBiz;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * API-课程视频信息
 *
 * @author wujing
 * @date 2022-09-02
 */
@Api(tags = "API-课程视频信息")
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/api/resource")
public class ApiResourceController {

    @NotNull
    private final ApiResourceBiz biz;

}
