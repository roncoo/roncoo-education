package com.roncoo.education.course.service.auth.biz;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.config.ThreadContext;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.CourseDao;
import com.roncoo.education.course.dao.UserCourseCollectDao;
import com.roncoo.education.course.dao.impl.mapper.entity.Course;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourseCollect;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourseCollectExample;
import com.roncoo.education.course.service.auth.req.AuthUserCourseCollectPageReq;
import com.roncoo.education.course.service.auth.req.AuthUserCourseCollectReq;
import com.roncoo.education.course.service.auth.resp.AuthUserCourseCollectResp;
import com.roncoo.education.course.service.biz.resp.CourseResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @NotNull
    private final CourseDao courseDao;

    public Result<Page<AuthUserCourseCollectResp>> listForPage(AuthUserCourseCollectPageReq req) {
        UserCourseCollectExample example = new UserCourseCollectExample();
        example.createCriteria().andUserIdEqualTo(ThreadContext.userId());
        example.setOrderByClause("id desc");
        Page<AuthUserCourseCollectResp> resp = PageUtil.transform(dao.page(req.getPageCurrent(), req.getPageSize(), example), AuthUserCourseCollectResp.class);
        if (CollUtil.isNotEmpty(resp.getList())) {
            List<Long> courseIds = resp.getList().stream().map(AuthUserCourseCollectResp::getCourseId).collect(Collectors.toList());
            Map<Long, Course> courseMap = courseDao.listByIds(courseIds).stream().collect(Collectors.toMap(Course::getId, item -> item));
            for (AuthUserCourseCollectResp res : resp.getList()) {
                res.setCourseResp(BeanUtil.copyProperties(courseMap.get(res.getCourseId()), CourseResp.class));
            }
        }
        return Result.success(resp);
    }

    public Result<String> add(AuthUserCourseCollectReq req) {
        UserCourseCollect userCourseCollect = dao.getByCouserIdAndUserId(req.getCourseId(), ThreadContext.userId());
        if (ObjectUtil.isNotEmpty(userCourseCollect)) {
            dao.deleteById(userCourseCollect.getId());
            return Result.success("cancel");
        }

        userCourseCollect = BeanUtil.copyProperties(req, UserCourseCollect.class);
        userCourseCollect.setUserId(ThreadContext.userId());
        dao.save(userCourseCollect);
        return Result.success("add");
    }
}
