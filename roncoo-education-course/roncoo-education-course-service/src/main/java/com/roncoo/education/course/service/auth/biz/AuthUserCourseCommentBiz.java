package com.roncoo.education.course.service.auth.biz;

import cn.hutool.core.collection.CollUtil;
import com.roncoo.education.common.config.ThreadContext;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.CourseDao;
import com.roncoo.education.course.dao.UserCourseCommentDao;
import com.roncoo.education.course.dao.impl.mapper.entity.Course;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourseComment;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourseCommentExample;
import com.roncoo.education.course.service.auth.req.AuthUserCourseCommentPageReq;
import com.roncoo.education.course.service.auth.req.AuthUserCourseCommentReq;
import com.roncoo.education.course.service.auth.resp.AuthUserCourseCommentResp;
import com.roncoo.education.course.service.biz.resp.CourseResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @NotNull
    private final CourseDao courseDao;

    public Result<Page<AuthUserCourseCommentResp>> listForPage(AuthUserCourseCommentPageReq req) {
        UserCourseCommentExample example = new UserCourseCommentExample();
        example.createCriteria().andUserIdEqualTo(ThreadContext.userId());
        example.setOrderByClause("id desc");

        Page<AuthUserCourseCommentResp> resp = PageUtil.transform(dao.page(req.getPageCurrent(), req.getPageSize(), example), AuthUserCourseCommentResp.class);
        if (CollUtil.isNotEmpty(resp.getList())) {
            List<Long> courseIds = resp.getList().stream().map(AuthUserCourseCommentResp::getCourseId).collect(Collectors.toList());
            Map<Long, Course> courseMap = courseDao.listByIds(courseIds).stream().collect(Collectors.toMap(Course::getId, item -> item));
            for (AuthUserCourseCommentResp res : resp.getList()) {
                res.setCourseResp(BeanUtil.copyProperties(courseMap.get(res.getCourseId()), CourseResp.class));
            }
        }
        return Result.success(resp);
    }

    public Result<String> add(AuthUserCourseCommentReq req) {
        UserCourseComment userCourseComment = BeanUtil.copyProperties(req, UserCourseComment.class);
        userCourseComment.setUserId(ThreadContext.userId());
        dao.save(userCourseComment);
        return Result.success("评论成功");
    }

}
