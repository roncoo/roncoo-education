package com.roncoo.education.course.service.auth.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.config.ThreadContext;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.UserCourseCollectDao;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourseCollect;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourseCollectExample;
import com.roncoo.education.course.service.auth.req.AuthUserCourseCollectPageReq;
import com.roncoo.education.course.service.auth.req.AuthUserCourseCollectReq;
import com.roncoo.education.course.service.auth.resp.AuthUserCourseCollectResp;
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

    public Result<Page<AuthUserCourseCollectResp>> listForPage(AuthUserCourseCollectPageReq req) {
        UserCourseCollectExample example = new UserCourseCollectExample();
        example.setOrderByClause("id desc");
        return Result.success(PageUtil.transform(dao.page(req.getPageCurrent(), req.getPageSize(), example), AuthUserCourseCollectResp.class));
    }

    public Result<String> add(AuthUserCourseCollectReq req) {
        UserCourseCollect userCourseCollect = dao.getByCouserIdAndUserId(req.getCourseId(), ThreadContext.userId());
        if (ObjectUtil.isNotEmpty(userCourseCollect)) {
            dao.deleteById(userCourseCollect.getId());
            return Result.success("取消收藏成功");
        }

        userCourseCollect = BeanUtil.copyProperties(req, UserCourseCollect.class);
        userCourseCollect.setUserId(ThreadContext.userId());
        dao.save(userCourseCollect);
        return Result.success("添加收藏成功");
    }
}
