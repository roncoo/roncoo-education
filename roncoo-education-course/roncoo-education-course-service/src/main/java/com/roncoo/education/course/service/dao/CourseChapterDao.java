package com.roncoo.education.course.service.dao;

import java.util.List;

import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseChapter;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseChapterExample;
import com.roncoo.education.util.base.Page;

public interface CourseChapterDao {
    int save(CourseChapter record);

    int deleteById(Long id);

    int updateById(CourseChapter record);

    CourseChapter getById(Long id);

    Page<CourseChapter> listForPage(int pageCurrent, int pageSize, CourseChapterExample example);

	/**
	 * 根据章节编号、状态ID查找章节信息
	 */
	List<CourseChapter> listByCourseIdAndStatusId(Long courseId, Integer statusId);
}