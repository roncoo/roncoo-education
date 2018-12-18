package com.roncoo.education.course.service.dao;

import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseUserStudyLog;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseUserStudyLogExample;
import com.roncoo.education.util.base.Page;

public interface CourseUserStudyLogDao {
	int save(CourseUserStudyLog record);

	int deleteById(Long id);

	int updateById(CourseUserStudyLog record);

	CourseUserStudyLog getById(Long id);

	Page<CourseUserStudyLog> listForPage(int pageCurrent, int pageSize, CourseUserStudyLogExample example);

	CourseUserStudyLog getByUserNoAndPeriodId(Long userNo, Long periodId);
}