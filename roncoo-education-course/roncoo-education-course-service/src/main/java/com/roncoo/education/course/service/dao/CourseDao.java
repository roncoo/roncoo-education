package com.roncoo.education.course.service.dao;

import java.util.List;

import com.roncoo.education.course.service.dao.impl.mapper.entity.Course;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseExample;
import com.roncoo.education.util.base.Page;

public interface CourseDao {
    int save(Course record);

    int deleteById(Long id);

    int updateById(Course record);

    Course getById(Long id);

    Page<Course> listForPage(int pageCurrent, int pageSize, CourseExample example);

    /**
	 * 根据课程ID获取课程信息列表
	 * 
	 * @param courseId
	 * @return
	 */
	List<Course> listByCategoryId(Long courseId);

	/**
	 * 根据课程编号和状态获取课程信息
	 * 
	 * @param courseId
	 * @param StatusId
	 * @return
	 */
	Course getByCourseIdAndStatusId(Long courseId, Integer StatusId);

	/**
	 * 根据课程名称获取课程信息
	 * 
	 * @param courseName
	 * @return
	 */
	Course getByCourseName(String courseName);

	/**
	 * 根据二级分类ID和状态获取课程信息
	 * 
	 * @param categoryId2
	 * @param statusId
	 * @return
	 */
	List<Course> listBycategoryId2AndStatusId(Long categoryId2, Integer statusId);
}