package com.roncoo.education.course.service.auth;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.course.service.auth.biz.AuthUserCourseCommentBiz;
import com.roncoo.education.course.service.auth.req.AuthUserCourseCommentPageReq;
import com.roncoo.education.course.service.auth.req.AuthUserCourseCommentReq;
import com.roncoo.education.course.service.auth.resp.AuthUserCourseCommentResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * AUTH-课程评论
 *
 * @author wujing
 * @date 2023-03-24
 */
@Api(tags = "auth-课程评论")
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/auth/user/course/comment")
public class AuthUserCourseCommentController {

    @NotNull
    private final AuthUserCourseCommentBiz biz;

    /**
     * 课程评论列表
     *
     * @author fengyw
     */
    @ApiOperation(value = "课程评论列出", notes = "根据条件进行课程评论分页")
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Result<Page<AuthUserCourseCommentResp>> listForPage(@RequestBody AuthUserCourseCommentPageReq req) {
        return biz.listForPage(req);
    }


    /**
     * 课程评论添加
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "课程评论添加", notes = "用户进行课程评论")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result<String> add(@RequestBody AuthUserCourseCommentReq req) {
        return biz.add(req);
    }

}
