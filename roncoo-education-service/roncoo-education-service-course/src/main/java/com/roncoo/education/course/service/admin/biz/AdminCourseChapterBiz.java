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
import com.roncoo.education.course.dao.ResourceDao;
import com.roncoo.education.course.dao.UserStudyDao;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapter;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterExample;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterExample.Criteria;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterPeriod;
import com.roncoo.education.course.dao.impl.mapper.entity.Resource;
import com.roncoo.education.course.service.admin.req.AdminCourseChapterEditReq;
import com.roncoo.education.course.service.admin.req.AdminCourseChapterPageReq;
import com.roncoo.education.course.service.admin.req.AdminCourseChapterSaveReq;
import com.roncoo.education.course.service.admin.resp.AdminCourseChapterPageResp;
import com.roncoo.education.course.service.admin.resp.AdminCourseChapterPeriodViewResp;
import com.roncoo.education.course.service.admin.resp.AdminCourseChapterViewResp;
import com.roncoo.education.course.service.admin.resp.AdminResourceViewResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ADMIN-章节信息
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AdminCourseChapterBiz extends BaseBiz {

    @NotNull
    private final CourseChapterDao dao;
    @NotNull
    private final CourseChapterPeriodDao courseChapterPeriodDao;
    @NotNull
    private final ResourceDao resourceDao;
    @NotNull
    private final UserStudyDao userStudyDao;

    /**
     * 章节信息分页
     *
     * @param req 章节信息分页查询参数
     * @return 章节信息分页查询结果
     */
    public Result<Page<AdminCourseChapterPageResp>> page(AdminCourseChapterPageReq req) {
        CourseChapterExample example = new CourseChapterExample();
        Criteria c = example.createCriteria();
        if (ObjectUtil.isNotEmpty(req.getCourseId())) {
            c.andCourseIdEqualTo(req.getCourseId());
        }
        example.setOrderByClause("sort asc, id desc");
        Page<CourseChapter> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminCourseChapterPageResp> respPage = PageUtil.transform(page, AdminCourseChapterPageResp.class);
        if (CollUtil.isNotEmpty(respPage.getList())) {
            List<Long> chapterIds = respPage.getList().stream().map(AdminCourseChapterPageResp::getId).collect(Collectors.toList());
            // 课时
            List<CourseChapterPeriod> periodList = courseChapterPeriodDao.listByChapterIds(chapterIds);
            if (CollUtil.isNotEmpty(periodList)) {
                // 资源
                List<Long> resourceIdList = periodList.stream().map(courseChapterPeriod -> courseChapterPeriod.getResourceId()).collect(Collectors.toList());
                Map<Long, Resource> resourceMap = resourceDao.listByIds(resourceIdList).stream().collect(Collectors.toMap(Resource::getId, item -> item));
                Map<Long, List<CourseChapterPeriod>> periodMap = periodList.stream().collect(Collectors.groupingBy(CourseChapterPeriod::getChapterId, Collectors.toList()));
                for (AdminCourseChapterPageResp resp : respPage.getList()) {
                    resp.setPeriodViewRespList(BeanUtil.copyProperties(periodMap.get(resp.getId()), AdminCourseChapterPeriodViewResp.class));
                    for (AdminCourseChapterPeriodViewResp period : resp.getPeriodViewRespList()) {
                        period.setResourceViewResp(BeanUtil.copyProperties(resourceMap.get(period.getResourceId()), AdminResourceViewResp.class));
                    }
                }
            }
        }
        return Result.success(respPage);
    }

    /**
     * 章节信息添加
     *
     * @param req 章节信息
     * @return 添加结果
     */
    public Result<String> save(AdminCourseChapterSaveReq req) {
        CourseChapter record = BeanUtil.copyProperties(req, CourseChapter.class);
        if (dao.save(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 章节信息查看
     *
     * @param id 主键ID
     * @return 章节信息
     */
    public Result<AdminCourseChapterViewResp> view(Long id) {
        return Result.success(BeanUtil.copyProperties(dao.getById(id), AdminCourseChapterViewResp.class));
    }

    /**
     * 章节信息修改
     *
     * @param req 章节信息修改对象
     * @return 修改结果
     */
    public Result<String> edit(AdminCourseChapterEditReq req) {
        CourseChapter record = BeanUtil.copyProperties(req, CourseChapter.class);
        if (dao.updateById(record) > 0) {
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }

    /**
     * 章节信息删除
     *
     * @param id ID主键
     * @return 删除结果
     */
    @Transactional(rollbackFor = Exception.class)
    public Result<String> delete(Long id) {
        List<CourseChapterPeriod> periodList = courseChapterPeriodDao.listByChapterId(id);
        if (CollUtil.isNotEmpty(periodList) && periodList.size() > 0) {
            return Result.error("请先删除节，才能删除章");
        }
        if (dao.deleteById(id) > 0) {
            return Result.success("操作成功");
        }
        if (courseChapterPeriodDao.deleteById(id) > 0) {
            // 删除课时，也需要删除对应的学习记录，否则统计进度出现数据异常
            userStudyDao.deleteByPeriodId(id);
            return Result.success("操作成功");
        }
        return Result.error("操作失败");
    }
}
