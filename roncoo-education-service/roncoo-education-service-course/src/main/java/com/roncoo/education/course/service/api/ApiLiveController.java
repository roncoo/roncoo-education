package com.roncoo.education.course.service.api;

import com.roncoo.education.course.service.api.biz.ApiLiveBiz;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * API-直播信息
 *
 * @author fengyw
 * @date 2024-06-08
 */
@Api(tags = "api-直播信息")
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/api/live")
public class ApiLiveController {

    @NotNull
    private final ApiLiveBiz biz;

}
