package com.roncoo.education.course.service.auth;

import com.roncoo.education.course.service.auth.biz.AuthResourceBiz;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * AUTH-课程视频信息
 *
 * @author wujing
 * @date 2022-08-25
 */
@Api(tags = "AUTH-课程视频信息")
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/auth/resource")
public class AuthResourceController {

    @NotNull
    private final AuthResourceBiz biz;

}
