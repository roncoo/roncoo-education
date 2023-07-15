package com.roncoo.education.course.service.auth.biz;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.config.ThreadContext;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.CourseChapterPeriodDao;
import com.roncoo.education.course.dao.CourseDao;
import com.roncoo.education.course.dao.UserCourseDao;
import com.roncoo.education.course.dao.UserStudyDao;
import com.roncoo.education.course.dao.impl.mapper.entity.*;
import com.roncoo.education.course.service.auth.req.AuthUserCourseReq;
import com.roncoo.education.course.service.auth.resp.AuthCourseResp;
import com.roncoo.education.course.service.auth.resp.AuthUserCourseResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * AUTH-课程用户关联表
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AuthUserCourseBiz extends BaseBiz {

    @NotNull
    private final UserCourseDao dao;
    @NotNull
    private final UserStudyDao userStudyDao;
    @NotNull
    private final CourseDao courseDao;
    @NotNull
    private final CourseChapterPeriodDao courseChapterPeriodDao;

    public Result<Page<AuthUserCourseResp>> listForPage(AuthUserCourseReq req) {
        UserCourseExample example = new UserCourseExample();
        example.createCriteria().andUserIdEqualTo(ThreadContext.userId());
        example.setOrderByClause("id desc");
        Page<UserCourse> userCoursePage = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AuthUserCourseResp> respPage = PageUtil.transform(userCoursePage, AuthUserCourseResp.class);
        if (CollUtil.isNotEmpty(respPage.getList())) {
            List<Long> courseIdList = respPage.getList().stream().map(AuthUserCourseResp::getCourseId).collect(Collectors.toList());
            // 用户学习记录，获取每个课程里面最新学习的课时
            Map<Long, UserStudy> userStudyMap = new HashMap<>();
            List<UserStudy> userStudyList = userStudyDao.listByUserIdAndCourseIdsForMax(ThreadContext.userId(), courseIdList);
            if (CollUtil.isNotEmpty(userStudyList)) {
                userStudyMap = userStudyList.stream().collect(Collectors.toMap(item -> item.getCourseId(), item -> item));
            }

            // 课时名称
            Map<Long, String> periodNameMap = new HashMap<>();
            // 每个课程的课时数
            Map<Long, Long> periodSumMap = new HashMap<>();
            List<CourseChapterPeriod> courseChapterPeriodList = courseChapterPeriodDao.listByCourseIds(courseIdList);
            if (CollUtil.isNotEmpty(courseChapterPeriodList)) {
                periodNameMap = courseChapterPeriodList.stream().collect(Collectors.toMap(item -> item.getId(), item -> item.getPeriodName()));
                periodSumMap = courseChapterPeriodList.stream().collect(Collectors.groupingBy(item -> item.getCourseId(), Collectors.counting()));
            }

            // 每个课程的学习进度汇总
            Map<Long, BigDecimal> userStudySumMap = new HashMap<>();
            List<UserStudy> userStudySumList = userStudyDao.listByUserIdAndCourseIdsForSumProgress(ThreadContext.userId(), courseIdList);
            if (CollUtil.isNotEmpty(userStudySumList)) {
                userStudySumMap = userStudySumList.stream().collect(Collectors.toMap(item -> item.getCourseId(), item -> item.getProgress()));
            }

            // 课程信息
            List<Course> courseList = courseDao.listByIds(courseIdList);
            Map<Long, Course> courseMap = courseList.stream().collect(Collectors.toMap(item -> item.getId(), item -> item));

            for (AuthUserCourseResp resp : respPage.getList()) {
                UserStudy userStudy = userStudyMap.get(resp.getCourseId());
                if (ObjectUtil.isNotEmpty(userStudy)) {
                    resp.setPeriodProgress(userStudy.getProgress());
                    resp.setPeriodTime(userStudy.getGmtModified());
                    resp.setPeriodName(periodNameMap.get(userStudy.getPeriodId()));
                    resp.setCourseProgress(userStudySumMap.get(resp.getCourseId()).divide(BigDecimal.valueOf(periodSumMap.get(resp.getCourseId())), BigDecimal.ROUND_UP));
                }
                resp.setCourseResp(BeanUtil.copyProperties(courseMap.get(resp.getCourseId()), AuthCourseResp.class));
            }
        }
        return Result.success(respPage);
    }

}
