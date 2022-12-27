package com.roncoo.education.course.service.admin.biz;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.CourseChapterDao;
import com.roncoo.education.course.dao.CourseChapterPeriodDao;
import com.roncoo.education.course.dao.UserStudyDao;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapter;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterExample;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterPeriod;
import com.roncoo.education.course.dao.impl.mapper.entity.UserStudy;
import com.roncoo.education.course.service.admin.req.AdminUserStudyEditReq;
import com.roncoo.education.course.service.admin.req.AdminUserStudyPageReq;
import com.roncoo.education.course.service.admin.req.AdminUserStudySaveReq;
import com.roncoo.education.course.service.admin.resp.AdminUserStudyPageResp;
import com.roncoo.education.course.service.admin.resp.AdminUserStudyPeriodPageResp;
import com.roncoo.education.course.service.admin.resp.AdminUserStudyViewResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ADMIN-课程用户学习日志
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AdminUserStudyBiz extends BaseBiz {

    @NotNull
    private final UserStudyDao dao;

    @NotNull
    private final CourseChapterDao courseChapterDao;
    @NotNull
    private final CourseChapterPeriodDao courseChapterPeriodDao;

    /**
     * 课程用户学习日志分页
     *
     * @param req 课程用户学习日志分页查询参数
     * @return 课程用户学习日志分页查询结果
     */
    public Result<Page<AdminUserStudyPageResp>> page(AdminUserStudyPageReq req) {
        CourseChapterExample example = new CourseChapterExample();
        CourseChapterExample.Criteria c = example.createCriteria();
        if (ObjectUtil.isNotEmpty(req.getCourseId())) {
            c.andCourseIdEqualTo(req.getCourseId());
        }
        example.setOrderByClause("sort asc, id desc");
        Page<CourseChapter> page = courseChapterDao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminUserStudyPageResp> respPage = PageUtil.transform(page, AdminUserStudyPageResp.class);
        if (CollUtil.isNotEmpty(respPage.getList())) {
            List<Long> chapterIds = respPage.getList().stream().map(AdminUserStudyPageResp::getId).collect(Collectors.toList());
            // 课时
            List<CourseChapterPeriod> periodList = courseChapterPeriodDao.listByChapterIds(chapterIds);
            if (CollUtil.isNotEmpty(periodList)) {
                // 记录
                List<UserStudy> userStudyList = dao.listByUserIdAndCourseId(req.getUserId(), req.getCourseId());
                Map<Long, UserStudy> userStudyMap = userStudyList.stream().collect(Collectors.toMap(item -> item.getPeriodId(), item -> item));
                Map<Long, List<CourseChapterPeriod>> periodMap = periodList.stream().collect(Collectors.groupingBy(CourseChapterPeriod::getChapterId, Collectors.toList()));

                for (AdminUserStudyPageResp resp : respPage.getList()) {
                    resp.setUserStudyPeriodPageRespList(BeanUtil.copyProperties(periodMap.get(resp.getId()), AdminUserStudyPeriodPageResp.class));
                    for (AdminUserStudyPeriodPageResp period : resp.getUserStudyPeriodPageRespList()) {
                        UserStudy userStudy = userStudyMap.get(period.getId());
                        if (ObjectUtil.isNotEmpty(userStudy)) {
                            period.setProgress(userStudy.getProgress());
                            period.setGmtCreate(userStudy.getGmtCreate());
                            period.setGmtModified(userStudy.getGmtModified());
                        }
                    }
                }
            }
        }
        return Result.success(respPage);
    }

    /**
     * 课程用户学习日志添加
     *
     * @param req 课程用户学习日志
     * @return 添加结果
     */
    public Result<String> save(AdminUserStudySaveReq req) {
        UserStudy record = BeanUtil.copyProperties(req, UserStudy.class);
        if (dao.save(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 课程用户学习日志查看
     *
     * @param id 主键ID
     * @return 课程用户学习日志
     */
    public Result<AdminUserStudyViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), AdminUserStudyViewResp.class));
    }

    /**
     * 课程用户学习日志修改
     *
     * @param req 课程用户学习日志修改对象
     * @return 修改结果
     */
    public Result<String> edit(AdminUserStudyEditReq req) {
        UserStudy record = BeanUtil.copyProperties(req, UserStudy.class);
        if (dao.updateById(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 课程用户学习日志删除
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
