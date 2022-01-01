package com.roncoo.education.course.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapter;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterExample;

import java.util.List;

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
