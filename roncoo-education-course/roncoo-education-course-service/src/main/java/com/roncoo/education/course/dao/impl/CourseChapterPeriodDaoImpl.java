package com.roncoo.education.course.dao.impl;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.course.dao.CourseChapterPeriodDao;
import com.roncoo.education.course.dao.impl.mapper.CourseChapterPeriodMapper;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterPeriod;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterPeriodExample;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 课时信息 服务实现类
 *
 * @author wujing
 * @date 2022-08-25
 */
@Repository
@RequiredArgsConstructor
public class CourseChapterPeriodDaoImpl implements CourseChapterPeriodDao {

    @NotNull
    private final CourseChapterPeriodMapper mapper;

    @Override
    public int save(CourseChapterPeriod record) {
        if (record.getId() == null) {
            record.setId(IdWorker.getId());
        }
        return this.mapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
        return this.mapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(CourseChapterPeriod record) {
        record.setGmtCreate(null);
        record.setGmtModified(null);
        return this.mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public CourseChapterPeriod getById(Long id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<CourseChapterPeriod> page(int pageCurrent, int pageSize, CourseChapterPeriodExample example) {
        int count = this.mapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<>(count, totalPage, pageCurrent, pageSize, this.mapper.selectByExample(example));
    }

    @Override
    public List<CourseChapterPeriod> listByExample(CourseChapterPeriodExample example) {
        return this.mapper.selectByExample(example);
    }

    @Override
    public int countByExample(CourseChapterPeriodExample example) {
        return this.mapper.countByExample(example);
    }

    @Override
    public List<CourseChapterPeriod> listByCourseIdAndStatusId(Long courseId, Integer statusId) {
        CourseChapterPeriodExample example = new CourseChapterPeriodExample();
        CourseChapterPeriodExample.Criteria c = example.createCriteria();
        c.andCourseIdEqualTo(courseId);
        if (null != statusId) {
            c.andStatusIdEqualTo(statusId);
        }
        example.setOrderByClause("sort asc, id desc");
        return this.mapper.selectByExample(example);
    }

    @Override
    public List<CourseChapterPeriod> listByChapterIds(List<Long> chapterIds) {
        CourseChapterPeriodExample example = new CourseChapterPeriodExample();
        CourseChapterPeriodExample.Criteria c = example.createCriteria();
        c.andChapterIdIn(chapterIds);
        example.setOrderByClause("sort asc, id desc");
        return this.mapper.selectByExample(example);
    }

    @Override
    public List<CourseChapterPeriod> listByCourseIds(List<Long> courseIdList) {
        CourseChapterPeriodExample example = new CourseChapterPeriodExample();
        CourseChapterPeriodExample.Criteria c = example.createCriteria();
        c.andCourseIdIn(courseIdList);
        example.setOrderByClause("sort asc, id desc");
        return this.mapper.selectByExample(example);
    }

    @Override
    public List<CourseChapterPeriod> listByResourceId(Long resourceId) {
        CourseChapterPeriodExample example = new CourseChapterPeriodExample();
        CourseChapterPeriodExample.Criteria c = example.createCriteria();
        c.andResourceIdEqualTo(resourceId);
        example.setOrderByClause("sort asc, id desc");
        return this.mapper.selectByExample(example);
    }

    @Override
    public List<CourseChapterPeriod> listByChapterId(Long id) {
        CourseChapterPeriodExample example = new CourseChapterPeriodExample();
        CourseChapterPeriodExample.Criteria c = example.createCriteria();
        c.andChapterIdEqualTo(id);
        example.setOrderByClause("sort asc, id desc");
        return this.mapper.selectByExample(example);
    }
}
