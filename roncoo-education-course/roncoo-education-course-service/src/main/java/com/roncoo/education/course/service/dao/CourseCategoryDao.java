package com.roncoo.education.course.service.dao;

import java.util.List;

import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseCategory;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseCategoryExample;
import com.roncoo.education.util.base.Page;

public interface CourseCategoryDao {
	int save(CourseCategory record);

	int deleteById(Long id);

	int updateById(CourseCategory record);

	CourseCategory getById(Long id);

	Page<CourseCategory> listForPage(int pageCurrent, int pageSize, CourseCategoryExample example);

	/**
	 * 根据父类编号查找课程分类信息
	 * 
	 * @param parentId
	 * @author WY
	 */
	List<CourseCategory> listByParentId(Long parentId);

	/**
	 * 根据层级列出分类信息
	 * 
	 * @param floor
	 * @author WY
	 */
	List<CourseCategory> listByFloor(Integer floor);

	/**
	 * 根据层级、父类ID列出分类信息
	 * 
	 * @param floor
	 * @param parentId
	 * @author WY
	 */
	List<CourseCategory> listByFloorAndCategoryId(Integer floor, Long parentId);

	/**
	 * 根据分类类型、层级查询可用状态的课程分类集合
	 * 
	 * @param categoryType
	 * @param floor
	 * @param statusId
	 * @author wuyun
	 */
	List<CourseCategory> listByCategoryTypeAndFloorAndStatusId(Integer categoryType, Integer floor, Integer statusId);
}