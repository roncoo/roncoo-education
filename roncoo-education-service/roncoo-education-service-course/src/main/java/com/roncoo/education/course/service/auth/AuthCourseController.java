package com.roncoo.education.course.service.auth;

import com.roncoo.education.common.config.ThreadContext;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.course.service.auth.biz.AuthCourseBiz;
import com.roncoo.education.course.service.auth.req.AuthCourseSignReq;
import com.roncoo.education.course.service.auth.resp.AuthCourseSignResp;
import com.roncoo.education.course.service.biz.CourseBiz;
import com.roncoo.education.course.service.biz.req.CourseReq;
import com.roncoo.education.course.service.biz.resp.CourseResp;
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
@Api(tags = "auth-课程信息")
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/auth/course")
public class AuthCourseController {

    @NotNull
    private final AuthCourseBiz biz;

    @NotNull
    private final CourseBiz courseBiz;

    /**
     * 课程详情接口
     */
    @ApiOperation(value = "课程详情", notes = "校验课程是否可以学习")
    @RequestMapping(value = "/view", method = RequestMethod.POST)
    public Result<CourseResp> view(@RequestBody CourseReq req) {
        return courseBiz.view(req, ThreadContext.userId());
    }

    /**
     * 课程信息列表接口
     *
     * @author fengyw
     */
    @ApiOperation(value = "学习配置", notes = "获取课时学习需要的配置参数")
    @RequestMapping(value = "/sign", method = RequestMethod.POST)
    public Result<AuthCourseSignResp> sign(@RequestBody AuthCourseSignReq req) {
        return biz.sign(req);
    }
}
