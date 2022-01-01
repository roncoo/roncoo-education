package com.roncoo.education.course.dao.impl;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.course.dao.CourseCategoryDao;
import com.roncoo.education.course.dao.impl.mapper.CourseCategoryMapper;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseCategory;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseCategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CourseCategoryDaoImpl implements CourseCategoryDao {
    @Autowired
    private CourseCategoryMapper courseCategoryMapper;

    @Override
    public int save(CourseCategory record) {
        record.setId(IdWorker.getId());
        return this.courseCategoryMapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
        return this.courseCategoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(CourseCategory record) {
        return this.courseCategoryMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public CourseCategory getById(Long id) {
        return this.courseCategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<CourseCategory> listForPage(int pageCurrent, int pageSize, CourseCategoryExample example) {
        int count = this.courseCategoryMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<CourseCategory>(count, totalPage, pageCurrent, pageSize, this.courseCategoryMapper.selectByExample(example));
    }

    @Override
    public List<CourseCategory> listByParentId(Long parentId) {
        CourseCategoryExample example = new CourseCategoryExample();
        CourseCategoryExample.Criteria c = example.createCriteria();
        c.andParentIdEqualTo(parentId);
        example.setOrderByClause("status_id desc, sort desc, id desc");
        return this.courseCategoryMapper.selectByExample(example);
    }

    @Override
    public List<CourseCategory> listByFloor(Integer floor) {
        CourseCategoryExample example = new CourseCategoryExample();
        CourseCategoryExample.Criteria c = example.createCriteria();
        c.andFloorEqualTo(floor);
        example.setOrderByClause("id desc");
        return this.courseCategoryMapper.selectByExample(example);
    }

    @Override
    public List<CourseCategory> listByFloorAndCategoryId(Integer floor, Long parentId) {
        CourseCategoryExample example = new CourseCategoryExample();
        CourseCategoryExample.Criteria c = example.createCriteria();
        c.andFloorEqualTo(floor);
        c.andParentIdEqualTo(parentId);
        example.setOrderByClause("id desc");
        return this.courseCategoryMapper.selectByExample(example);
    }

    @Override
    public List<CourseCategory> listByCategoryTypeAndFloorAndStatusId(Integer categoryType, Integer floor, Integer statusId) {
        CourseCategoryExample example = new CourseCategoryExample();
        CourseCategoryExample.Criteria c = example.createCriteria();
        c.andCategoryTypeEqualTo(categoryType);
        c.andFloorEqualTo(floor);
        c.andStatusIdEqualTo(statusId);
        example.setOrderByClause(" status_id desc, sort desc, id desc ");
        return this.courseCategoryMapper.selectByExample(example);
    }

}
