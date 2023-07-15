package com.roncoo.education.course.service.biz;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.FreeEnum;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.*;
import com.roncoo.education.course.dao.impl.mapper.entity.*;
import com.roncoo.education.course.service.biz.req.CourseCommentPageReq;
import com.roncoo.education.course.service.biz.req.CourseReq;
import com.roncoo.education.course.service.biz.resp.*;
import com.roncoo.education.user.feign.interfaces.IFeignLecturer;
import com.roncoo.education.user.feign.interfaces.IFeignUsers;
import com.roncoo.education.user.feign.interfaces.vo.LecturerViewVO;
import com.roncoo.education.user.feign.interfaces.vo.UsersVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * API-课程信息
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class CourseBiz extends BaseBiz {

    @NotNull
    private final CourseDao dao;
    @NotNull
    private final UserCourseDao userCourseDao;
    @NotNull
    private final UserStudyDao userStudyDao;
    @NotNull
    private final CourseChapterDao chapterDao;
    @NotNull
    private final CourseChapterPeriodDao periodDao;
    @NotNull
    private final UserCourseCommentDao userCourseCommentDao;
    @NotNull
    private final UserCourseCollectDao userCourseCollectDao;
    @NotNull
    private final ResourceDao resourceDao;
    @NotNull
    private final IFeignLecturer feignLecturer;
    @NotNull
    private final IFeignUsers feignUsers;

    /**
     * 课程查看接口
     *
     * @param req
     * @param userId
     * @return
     */
    public Result<CourseResp> view(CourseReq req, Long userId) {
        Course course = dao.getById(req.getCourseId());
        if (course == null) {
            return Result.error("找不到该课程信息");
        }
        if (!course.getStatusId().equals(StatusIdEnum.YES.getCode())) {
            return Result.error("该课程已被禁用");
        }
        CourseResp courseResp = BeanUtil.copyProperties(course, CourseResp.class);

        Map<Long, BigDecimal> userStudyProgressMap = new HashMap<>();
        if (ObjectUtil.isNotEmpty(userId)) {
            // userId存在，即为登录
            if (courseResp.getIsFree().equals(FreeEnum.FREE.getCode()) || courseResp.getCoursePrice().compareTo(BigDecimal.ZERO) <= 0) {
                // 免费课程，可以学习
                courseResp.setAllowStudy(1);
            } else {
                // 收费课程
                UserCourse userCourse = userCourseDao.getByCourseIdAndUserId(req.getCourseId(), userId);
                if (ObjectUtil.isNotEmpty(userCourse)) {
                    courseResp.setAllowStudy(1);
                }
            }

            // 课时进度
            List<UserStudy> userStudyList = userStudyDao.listByUserIdAndCourseId(userId, course.getId());
            if (CollUtil.isNotEmpty(userStudyList)) {
                userStudyProgressMap = userStudyList.stream().collect(Collectors.toMap(UserStudy::getPeriodId, UserStudy::getProgress));
            }

            // 课程收藏状态
            UserCourseCollect userCourseCollect = userCourseCollectDao.getByCouserIdAndUserId(req.getCourseId(), userId);
            if (ObjectUtil.isNotEmpty(userCourseCollect)) {
                courseResp.setCourseCollect(Boolean.TRUE);
            }
        }
        // 获取讲师信息
        LecturerViewVO lecturerViewVO = feignLecturer.getById(course.getLecturerId());
        if (ObjectUtil.isNotEmpty(lecturerViewVO)) {
            courseResp.setLecturerResp(BeanUtil.copyProperties(lecturerViewVO, CourseLecturerResp.class));
        }
        // 章节信息
        List<CourseChapter> chapterList = chapterDao.listByCourseIdAndStatusId(course.getId(), StatusIdEnum.YES.getCode());
        if (CollUtil.isNotEmpty(chapterList)) {
            courseResp.setChapterRespList(BeanUtil.copyProperties(chapterList, CourseChapterResp.class));
            // 课时信息
            List<CourseChapterPeriod> periodList = periodDao.listByCourseIdAndStatusId(course.getId(), StatusIdEnum.YES.getCode());

            if (CollUtil.isNotEmpty(periodList)) {
                Map<Long, List<CourseChapterPeriod>> map = periodList.stream().collect(Collectors.groupingBy(CourseChapterPeriod::getChapterId, Collectors.toList()));
                List<Long> resourceIdList = periodList.stream().map(courseChapterPeriod -> courseChapterPeriod.getResourceId()).collect(Collectors.toList());
                // 资源信息
                List<Resource> resourceList = resourceDao.listByIds(resourceIdList);
                for (CourseChapterResp chapterResp : courseResp.getChapterRespList()) {
                    chapterResp.setPeriodRespList(BeanUtil.copyProperties(map.get(chapterResp.getId()), CourseChapterPeriodResp.class));
                    if (CollUtil.isNotEmpty(resourceList)) {
                        Map<Long, Resource> resourceMap = resourceList.stream().collect(Collectors.toMap(Resource::getId, item -> item));
                        for (CourseChapterPeriodResp periodResp : chapterResp.getPeriodRespList()) {
                            periodResp.setResourceResp(BeanUtil.copyProperties(resourceMap.get(periodResp.getResourceId()), ResourceResp.class));
                            periodResp.setPeriodProgress(userStudyProgressMap.get(periodResp.getId()));
                        }
                    }
                }
            }
        }
        return Result.success(courseResp);
    }

    public Result<Page<CourseCommentResp>> comment(CourseCommentPageReq req) {
        UserCourseCommentExample example = new UserCourseCommentExample();
        example.createCriteria().andCourseIdEqualTo(req.getCourseId());
        example.setOrderByClause("id desc");
        Page<UserCourseComment> userCourseCommentPage = userCourseCommentDao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<CourseCommentResp> resp = PageUtil.transform(userCourseCommentPage, CourseCommentResp.class);
        if (CollUtil.isNotEmpty(userCourseCommentPage.getList())) {
            resp.setList(filter(userCourseCommentPage.getList(), 0L));
            // 用户信息
            List<Long> userIds = userCourseCommentPage.getList().stream().map(UserCourseComment::getUserId).collect(Collectors.toList());
            Map<Long, UsersVO> usersVOMap = feignUsers.listByIds(userIds);
            for (CourseCommentResp commentResp : resp.getList()) {
                commentResp.setUsersVO(usersVOMap.get(commentResp.getUserId()));
            }
        }
        return Result.success(resp);
    }

    private List<CourseCommentResp> filter(List<UserCourseComment> userCourseComments, Long commentId) {
        List<UserCourseComment> list = userCourseComments.stream().filter(item -> item.getCommentId().equals(commentId)).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(list)) {
            List<CourseCommentResp> resps = BeanUtil.copyProperties(list, CourseCommentResp.class);
            for (CourseCommentResp resp : resps) {
                resp.setCourseCommentRespList(filter(userCourseComments, resp.getId()));
            }
            return resps;
        }
        return new ArrayList<>();
    }
}
