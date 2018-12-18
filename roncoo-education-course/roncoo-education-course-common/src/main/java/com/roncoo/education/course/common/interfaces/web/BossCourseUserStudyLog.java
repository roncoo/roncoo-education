package com.roncoo.education.course.common.interfaces.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.course.common.bean.qo.CourseUserStudyLogQO;
import com.roncoo.education.course.common.bean.vo.CourseUserStudyLogVO;
import com.roncoo.education.util.base.Page;

/**
 * 课程用户学习日志 
 *
 * @author wujing
 */
public interface BossCourseUserStudyLog {
	
	@RequestMapping(value = "/boss/course/courseUserStudyLog/listForPage", method = RequestMethod.POST)
	Page<CourseUserStudyLogVO> listForPage(@RequestBody CourseUserStudyLogQO qo);
	
	@RequestMapping(value = "/boss/course/courseUserStudyLog/save", method = RequestMethod.POST)
	int save(@RequestBody CourseUserStudyLogQO qo);
	
	@RequestMapping(value = "/boss/course/courseUserStudyLog/delete/{id}", method = RequestMethod.DELETE)
	int deleteById(@PathVariable(value = "id") Long id);
	
	@RequestMapping(value = "/boss/course/courseUserStudyLog/update", method = RequestMethod.PUT)
	int updateById(@RequestBody CourseUserStudyLogQO qo);
	
	@RequestMapping(value = "/boss/course/courseUserStudyLog/get/{id}", method = RequestMethod.GET)
	CourseUserStudyLogVO getById(@PathVariable(value = "id") Long id);
	
}
