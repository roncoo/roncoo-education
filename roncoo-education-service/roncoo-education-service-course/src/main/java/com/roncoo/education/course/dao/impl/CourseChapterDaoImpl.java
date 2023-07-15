package com.roncoo.education.course.dao.impl;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.course.dao.CourseChapterDao;
import com.roncoo.education.course.dao.impl.mapper.CourseChapterMapper;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapter;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterExample;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 章节信息 服务实现类
 *
 * @author wujing
 * @date 2022-08-25
 */
@Repository
@RequiredArgsConstructor
public class CourseChapterDaoImpl implements CourseChapterDao {

    @NotNull
    private final CourseChapterMapper mapper;

    @Override
    public int save(CourseChapter record) {
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
    public int updateById(CourseChapter record) {
        record.setGmtCreate(null);
        record.setGmtModified(null);
        return this.mapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public CourseChapter getById(Long id) {
        return this.mapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<CourseChapter> page(int pageCurrent, int pageSize, CourseChapterExample example) {
        int count = this.mapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<>(count, totalPage, pageCurrent, pageSize, this.mapper.selectByExample(example));
    }

    @Override
    public List<CourseChapter> listByExample(CourseChapterExample example) {
        return this.mapper.selectByExample(example);
    }

    @Override
    public int countByExample(CourseChapterExample example) {
        return this.mapper.countByExample(example);
    }

    @Override
    public List<CourseChapter> listByCourseId(Long courseId) {
        CourseChapterExample example = new CourseChapterExample();
        example.createCriteria().andCourseIdEqualTo(courseId);
        example.setOrderByClause(" sort asc, id desc ");
        return this.mapper.selectByExample(example);
    }

    @Override
    public List<CourseChapter> listByCourseIdAndStatusId(Long courseId, Integer statusId) {
        CourseChapterExample example = new CourseChapterExample();
        example.createCriteria().andCourseIdEqualTo(courseId).andStatusIdEqualTo(statusId);
        example.setOrderByClause(" sort asc, id desc ");
        return this.mapper.selectByExample(example);
    }
}
