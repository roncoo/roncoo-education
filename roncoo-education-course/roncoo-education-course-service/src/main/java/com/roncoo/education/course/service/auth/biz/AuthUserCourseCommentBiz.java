package com.roncoo.education.course.service.auth.biz;

import com.roncoo.education.common.config.ThreadContext;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.UserCourseCommentDao;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourseComment;
import com.roncoo.education.course.service.auth.req.AuthUserCourseCommentPageReq;
import com.roncoo.education.course.service.auth.req.AuthUserCourseCommentReq;
import com.roncoo.education.course.service.auth.resp.AuthUserCourseCommentResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * AUTH-课程评论
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AuthUserCourseCommentBiz extends BaseBiz {

    @NotNull
    private final UserCourseCommentDao dao;

    public Result<Page<AuthUserCourseCommentResp>> listForPage(AuthUserCourseCommentPageReq req) {
        return null;
    }

    public Result<String> add(AuthUserCourseCommentReq req) {
        UserCourseComment userCourseComment = BeanUtil.copyProperties(req, UserCourseComment.class);
        userCourseComment.setUserId(ThreadContext.userId());
        dao.save(userCourseComment);
        return Result.success("评论成功");
    }


}
