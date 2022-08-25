package com.roncoo.education.course.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.dao.impl.mapper.entity.Category;
import com.roncoo.education.course.dao.impl.mapper.entity.CategoryExample;

import java.util.List;

public interface CategoryDao {
    int save(Category record);

    int deleteById(Long id);

    int updateById(Category record);

    Category getById(Long id);

    Page<Category> listForPage(int pageCurrent, int pageSize, CategoryExample example);

    /**
     * 根据父类编号查找课程分类信息
     *
     * @param parentId
     * @author WY
     */
    List<Category> listByParentId(Long parentId);

    /**
     * 根据层级列出分类信息
     *
     * @param floor
     * @author WY
     */
    List<Category> listByFloor(Integer floor);

    /**
     * 根据层级、父类ID列出分类信息
     *
     * @param floor
     * @param parentId
     * @author WY
     */
    List<Category> listByFloorAndCategoryId(Integer floor, Long parentId);

    /**
     * 根据分类类型、层级查询可用状态的课程分类集合
     *
     * @param categoryType
     * @param floor
     * @param statusId
     * @author wuyun
     */
    List<Category> listByCategoryTypeAndFloorAndStatusId(Integer categoryType, Integer floor, Integer statusId);
}
