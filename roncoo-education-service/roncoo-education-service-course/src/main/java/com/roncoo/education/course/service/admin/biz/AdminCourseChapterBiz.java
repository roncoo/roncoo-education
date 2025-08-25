package com.roncoo.education.course.service.admin.biz;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.base.page.Page;
import com.roncoo.education.common.base.page.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.tools.BeanUtil;
import com.roncoo.education.common.base.BaseBiz;
import com.roncoo.education.course.dao.CourseChapterDao;
import com.roncoo.education.course.dao.CourseChapterPeriodDao;
import com.roncoo.education.course.dao.ResourceDao;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapter;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterExample;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterExample.Criteria;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterPeriod;
import com.roncoo.education.course.dao.impl.mapper.entity.Resource;
import com.roncoo.education.course.service.admin.req.*;
import com.roncoo.education.course.service.admin.resp.AdminCourseChapterPageResp;
import com.roncoo.education.course.service.admin.resp.AdminCourseChapterPeriodViewResp;
import com.roncoo.education.course.service.admin.resp.AdminCourseChapterViewResp;
import com.roncoo.education.course.service.admin.resp.AdminResourceViewResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.constraints.NotNull;

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
     * 章节信息，根据课程ID列出
     *
     * @param req
     * @return
     */
    public Result<List<AdminCourseChapterViewResp>> list(AdminCourseChapterListReq req) {
        CourseChapterExample example = new CourseChapterExample();
        Criteria c = example.createCriteria();
        c.andCourseIdEqualTo(req.getCourseId());
        example.setOrderByClause("sort asc, id desc");
        return Result.success(BeanUtil.copyProperties(dao.listByExample(example), AdminCourseChapterViewResp.class));
    }

    /**
     * 章节信息添加
     *
     * @param req 章节信息
     * @return 添加结果
     */
    public Result<String> save(AdminCourseChapterSaveReq req) {
        int maxSort = 0;
        List<CourseChapter> chapterList = dao.listByCourseId(req.getCourseId());
        if (CollUtil.isNotEmpty(chapterList)) {
            for (CourseChapter chapter : chapterList) {
                // 重新排序
                chapter.setSort(maxSort += 1);
            }
            dao.updateSortForBatch(chapterList);
        }
        CourseChapter record = BeanUtil.copyProperties(req, CourseChapter.class);
        record.setSort(maxSort + 1);
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
        return Result.error("操作失败");
    }

    public Result<String> sort(List<AdminCourseChapterSortReq> req) {
        dao.updateSortForBatch(BeanUtil.copyProperties(req, CourseChapter.class));
        return Result.success("操作成功");
    }
}
