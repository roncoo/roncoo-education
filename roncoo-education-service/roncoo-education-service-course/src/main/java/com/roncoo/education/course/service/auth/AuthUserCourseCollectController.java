package com.roncoo.education.course.service.auth;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.course.service.auth.biz.AuthUserCourseCollectBiz;
import com.roncoo.education.course.service.auth.req.AuthUserCourseCollectPageReq;
import com.roncoo.education.course.service.auth.req.AuthUserCourseCollectReq;
import com.roncoo.education.course.service.auth.resp.AuthUserCourseCollectResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * AUTH-课程收藏
 *
 * @author wujing
 * @date 2023-03-24
 */
@Api(tags = "auth-课程收藏")
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/auth/user/course/collect")
public class AuthUserCourseCollectController {

    @NotNull
    private final AuthUserCourseCollectBiz biz;

    /**
     * 课程收藏列表
     *
     * @author fengyw
     */
    @ApiOperation(value = "课程收藏列出", notes = "根据条件进行课程收藏分页")
    @RequestMapping(value = "/page", method = RequestMethod.POST)
    public Result<Page<AuthUserCourseCollectResp>> listForPage(@RequestBody AuthUserCourseCollectPageReq req) {
        return biz.listForPage(req);
    }


    /**
     * 课程评论添加
     *
     * @param req
     * @return
     */
    @ApiOperation(value = "课程收藏添加", notes = "用户进行课程收藏")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result<String> add(@RequestBody AuthUserCourseCollectReq req) {
        return biz.add(req);
    }

}
