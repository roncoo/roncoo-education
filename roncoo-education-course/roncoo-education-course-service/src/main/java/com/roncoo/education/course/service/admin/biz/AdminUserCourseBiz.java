package com.roncoo.education.course.service.admin.biz;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.DesensitizedUtil;
import cn.hutool.core.util.ObjectUtil;
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
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourseExample.Criteria;
import com.roncoo.education.course.service.admin.req.AdminUserCourseEditReq;
import com.roncoo.education.course.service.admin.req.AdminUserCoursePageReq;
import com.roncoo.education.course.service.admin.req.AdminUserCourseRecordReq;
import com.roncoo.education.course.service.admin.req.AdminUserCourseSaveReq;
import com.roncoo.education.course.service.admin.resp.AdminUserCoursePageResp;
import com.roncoo.education.course.service.admin.resp.AdminUserCourseRecordResp;
import com.roncoo.education.course.service.admin.resp.AdminUserCourseViewResp;
import com.roncoo.education.user.feign.interfaces.IFeignUsers;
import com.roncoo.education.user.feign.interfaces.vo.UsersVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ADMIN-课程用户关联表
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AdminUserCourseBiz extends BaseBiz {

    @NotNull
    private final IFeignUsers feignUsers;

    @NotNull
    private final UserCourseDao dao;
    @NotNull
    private final CourseDao courseDao;
    @NotNull
    private final CourseChapterPeriodDao courseChapterPeriodDao;
    @NotNull
    private final UserStudyDao userStudyDao;


    public Result<Page<AdminUserCourseRecordResp>> record(AdminUserCourseRecordReq req) {
        UserCourseExample example = new UserCourseExample();
        Criteria c = example.createCriteria();
        if (req.getCourseId() != null) {
            c.andCourseIdEqualTo(req.getCourseId());
        }
        example.setOrderByClause("id desc");
        Page<UserCourse> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminUserCourseRecordResp> respPage = PageUtil.transform(page, AdminUserCourseRecordResp.class);
        if (CollUtil.isNotEmpty(respPage.getList())) {
            CourseChapterPeriodExample courseChapterPeriodExample = new CourseChapterPeriodExample();
            courseChapterPeriodExample.createCriteria().andCourseIdEqualTo(req.getCourseId());
            int periods = courseChapterPeriodDao.countByExample(courseChapterPeriodExample);

            List<Long> userIdList = respPage.getList().stream().map(item -> item.getUserId()).collect(Collectors.toList());
            Map<Long, UsersVO> usersVOMap = feignUsers.listByIds(userIdList);

            List<UserStudy> userStudyList = userStudyDao.listByCourseIdAndUserIdsForSumProgress(req.getCourseId(), userIdList);
            Map<Long, BigDecimal> userStudySumMap = new HashMap<>();
            if (CollUtil.isNotEmpty(userStudyList)) {
                userStudySumMap = userStudyList.stream().collect(Collectors.toMap(item -> item.getUserId(), item -> item.getProgress()));
            }

            for (AdminUserCourseRecordResp auc : respPage.getList()) {

                UsersVO usersVO = usersVOMap.get(auc.getUserId());
                if (ObjectUtil.isNotEmpty(usersVO)) {
                    auc.setMobile(DesensitizedUtil.mobilePhone(usersVO.getMobile()));
                    auc.setNickname(usersVO.getNickname());
                }

                BigDecimal progress = userStudySumMap.get(auc.getUserId());
                if (ObjectUtil.isNotEmpty(progress)) {
                    // 课程进度
                    auc.setCourseProgress(progress.divide(BigDecimal.valueOf(periods), BigDecimal.ROUND_UP));
                }
            }
        }
        return Result.success(respPage);
    }

    /**
     * 课程用户关联表分页
     *
     * @param req 课程用户关联表分页查询参数
     * @return 课程用户关联表分页查询结果
     */
    public Result<Page<AdminUserCoursePageResp>> page(AdminUserCoursePageReq req) {
        UserCourseExample example = new UserCourseExample();
        Criteria c = example.createCriteria();
        if (req.getUserId() != null) {
            c.andUserIdEqualTo(req.getUserId());
        }
        example.setOrderByClause("id desc");
        Page<UserCourse> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminUserCoursePageResp> respPage = PageUtil.transform(page, AdminUserCoursePageResp.class);
        if (CollUtil.isNotEmpty(respPage.getList())) {
            List<Long> courseIdList = respPage.getList().stream().map(item -> item.getCourseId()).collect(Collectors.toList());
            Map<Long, Course> courseMap = courseDao.listByIds(courseIdList).stream().collect(Collectors.toMap(Course::getId, item -> item));

            List<UserStudy> userStudyList = userStudyDao.listByUserIdAndCourseIdsForSumProgress(req.getUserId(), courseIdList);
            Map<Long, BigDecimal> userStudySumMap = new HashMap<>();
            if (CollUtil.isNotEmpty(userStudyList)) {
                userStudySumMap = userStudyList.stream().collect(Collectors.toMap(item -> item.getCourseId(), item -> item.getProgress()));
            }

            // 每个课程的课时数
            Map<Long, Long> periodSumMap = new HashMap<>();
            List<CourseChapterPeriod> courseChapterPeriodList = courseChapterPeriodDao.listByCourseIds(courseIdList);
            if (CollUtil.isNotEmpty(courseChapterPeriodList)) {
                periodSumMap = courseChapterPeriodList.stream().collect(Collectors.groupingBy(item -> item.getCourseId(), Collectors.counting()));
            }

            for (AdminUserCoursePageResp auc : respPage.getList()) {
                Course course = courseMap.get(auc.getCourseId());
                if (ObjectUtil.isNotEmpty(course)) {
                    auc.setCourseName(course.getCourseName());
                    auc.setCourseLogo(course.getCourseLogo());
                }

                BigDecimal progress = userStudySumMap.get(auc.getCourseId());
                if (ObjectUtil.isNotEmpty(progress)) {
                    // 课程进度
                    auc.setCourseProgress(progress.divide(BigDecimal.valueOf(periodSumMap.get(auc.getCourseId())), BigDecimal.ROUND_UP));
                }
            }
        }
        return Result.success(respPage);
    }

    /**
     * 课程用户关联表添加
     *
     * @param req 课程用户关联表
     * @return 添加结果
     */
    public Result<String> save(AdminUserCourseSaveReq req) {
        UserCourse record = BeanUtil.copyProperties(req, UserCourse.class);
        if (dao.save(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 课程用户关联表查看
     *
     * @param id 主键ID
     * @return 课程用户关联表
     */
    public Result<AdminUserCourseViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), AdminUserCourseViewResp.class));
    }

    /**
     * 课程用户关联表修改
     *
     * @param req 课程用户关联表修改对象
     * @return 修改结果
     */
    public Result<String> edit(AdminUserCourseEditReq req) {
        UserCourse record = BeanUtil.copyProperties(req, UserCourse.class);
        if (dao.updateById(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 课程用户关联表删除
     *
     * @param id ID主键
     * @return 删除结果
     */
    public Result<String> delete(Long id) {
        if (dao.deleteById(id) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }
}
