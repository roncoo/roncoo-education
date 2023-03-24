package com.roncoo.education.course.service.auth.biz;

import com.roncoo.education.common.config.ThreadContext;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.UserCourseCollectDao;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourseCollect;
import com.roncoo.education.course.service.auth.req.AuthUserCourseCollectReq;
import com.roncoo.education.course.service.auth.req.AuthUserCourseCommentPageReq;
import com.roncoo.education.course.service.auth.resp.AuthUserCourseCommentResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * AUTH-课程收藏
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AuthUserCourseCollectBiz extends BaseBiz {

    @NotNull
    private final UserCourseCollectDao dao;

    public Result<Page<AuthUserCourseCommentResp>> listForPage(AuthUserCourseCommentPageReq req) {
        return null;
    }

    public Result<String> add(AuthUserCourseCollectReq req) {
        UserCourseCollect userCourseCollect = BeanUtil.copyProperties(req, UserCourseCollect.class);
        userCourseCollect.setUserId(ThreadContext.userId());
        dao.save(userCourseCollect);
        return Result.success("课程收藏成功");
    }
}
