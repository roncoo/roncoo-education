package com.roncoo.education.course.common.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.course.common.bean.qo.CourseUserStudyQO;
import com.roncoo.education.course.common.bean.vo.CourseUserStudyVO;
import com.roncoo.education.util.base.Page;

/**
 * 课程用户关联表 
 *
 * @author wujing
 */
public interface BossCourseUserStudy {
	
	@RequestMapping(value = "/boss/course/courseUserStudy/listForPage", method = RequestMethod.POST)
	Page<CourseUserStudyVO> listForPage(@RequestBody CourseUserStudyQO qo);
	
	@RequestMapping(value = "/boss/course/courseUserStudy/save", method = RequestMethod.POST)
	int save(@RequestBody CourseUserStudyQO qo);
	
	@RequestMapping(value = "/boss/course/courseUserStudy/delete/{id}", method = RequestMethod.DELETE)
	int deleteById(@PathVariable(value = "id") Long id);
	
	@RequestMapping(value = "/boss/course/courseUserStudy/update", method = RequestMethod.PUT)
	int updateById(@RequestBody CourseUserStudyQO qo);
	
	@RequestMapping(value = "/boss/course/courseUserStudy/get/{id}", method = RequestMethod.GET)
	CourseUserStudyVO getById(@PathVariable(value = "id") Long id);
	
}
