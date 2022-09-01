package com.roncoo.education.course.service.auth;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.course.service.auth.biz.AuthCourseBiz;
import com.roncoo.education.course.service.auth.req.AuthCourseReq;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * AUTH-课程信息
 *
 * @author wujing
 * @date 2022-08-25
 */
@Api(tags = "AUTH-课程信息")
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/auth/course")
public class AuthCourseController {

    @NotNull
    private final AuthCourseBiz biz;

    /**
     * 课程信息列表接口
     *
     * @author fengyw
     */
    @ApiOperation(value = "观看授权", notes = "课程观看授权")
    @RequestMapping(value = "/sign", method = RequestMethod.POST)
    public Result<String> sign(@RequestBody AuthCourseReq req) {
        return biz.sign(req);
    }

}
