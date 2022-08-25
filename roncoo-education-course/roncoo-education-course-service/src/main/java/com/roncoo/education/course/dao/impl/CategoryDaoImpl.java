package com.roncoo.education.course.dao.impl;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.IdWorker;
import com.roncoo.education.course.dao.CategoryDao;
import com.roncoo.education.course.dao.impl.mapper.CategoryMapper;
import com.roncoo.education.course.dao.impl.mapper.entity.Category;
import com.roncoo.education.course.dao.impl.mapper.entity.CategoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryDaoImpl implements CategoryDao {
    @Autowired
    private CategoryMapper CategoryMapper;

    @Override
    public int save(Category record) {
        record.setId(IdWorker.getId());
        return this.CategoryMapper.insertSelective(record);
    }

    @Override
    public int deleteById(Long id) {
        return this.CategoryMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int updateById(Category record) {
        return this.CategoryMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public Category getById(Long id) {
        return this.CategoryMapper.selectByPrimaryKey(id);
    }

    @Override
    public Page<Category> listForPage(int pageCurrent, int pageSize, CategoryExample example) {
        int count = this.CategoryMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<Category>(count, totalPage, pageCurrent, pageSize, this.CategoryMapper.selectByExample(example));
    }

    @Override
    public List<Category> listByParentId(Long parentId) {
        CategoryExample example = new CategoryExample();
        CategoryExample.Criteria c = example.createCriteria();
        c.andParentIdEqualTo(parentId);
        example.setOrderByClause("status_id desc, sort desc, id desc");
        return this.CategoryMapper.selectByExample(example);
    }

    @Override
    public List<Category> listByFloor(Integer floor) {
        CategoryExample example = new CategoryExample();
        CategoryExample.Criteria c = example.createCriteria();
        c.andFloorEqualTo(floor);
        example.setOrderByClause("id desc");
        return this.CategoryMapper.selectByExample(example);
    }

    @Override
    public List<Category> listByFloorAndCategoryId(Integer floor, Long parentId) {
        CategoryExample example = new CategoryExample();
        CategoryExample.Criteria c = example.createCriteria();
        c.andFloorEqualTo(floor);
        c.andParentIdEqualTo(parentId);
        example.setOrderByClause("id desc");
        return this.CategoryMapper.selectByExample(example);
    }

    @Override
    public List<Category> listByCategoryTypeAndFloorAndStatusId(Integer categoryType, Integer floor, Integer statusId) {
        CategoryExample example = new CategoryExample();
        CategoryExample.Criteria c = example.createCriteria();
        c.andCategoryTypeEqualTo(categoryType);
        c.andFloorEqualTo(floor);
        c.andStatusIdEqualTo(statusId);
        example.setOrderByClause(" status_id desc, sort desc, id desc ");
        return this.CategoryMapper.selectByExample(example);
    }

}
