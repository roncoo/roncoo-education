package com.roncoo.education.course.service.auth;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.course.service.auth.biz.AuthUserCourseBiz;
import com.roncoo.education.course.service.auth.req.AuthUserCourseReq;
import com.roncoo.education.course.service.auth.resp.AuthUserCourseResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * AUTH-课程用户关联表
 *
 * @author wujing
 * @date 2022-08-25
 */
@Api(tags = "auth-课程用户关联表")
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/auth/user/course")
public class AuthUserCourseController {

    @NotNull
    private final AuthUserCourseBiz biz;

    /**
     * 课程信息列表接口
     *
     * @author fengyw
     */
    @ApiOperation(value = "我的课程分页", notes = "根据条件进行课程分页")
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Result<Page<AuthUserCourseResp>> listForPage(@RequestBody AuthUserCourseReq req) {
        return biz.listForPage(req);
    }

}
