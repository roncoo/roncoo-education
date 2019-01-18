package com.roncoo.education.course.common.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.course.common.bean.qo.CourseQO;
import com.roncoo.education.course.common.bean.vo.CourseVO;
import com.roncoo.education.util.base.Page;

/**
 * 课程信息 
 *
 * @author wujing
 */
public interface BossCourse {
	
	@RequestMapping(value = "/boss/course/course/listForPage", method = RequestMethod.POST)
	Page<CourseVO> listForPage(@RequestBody CourseQO qo);
	
	@RequestMapping(value = "/boss/course/course/save", method = RequestMethod.POST)
	int save(@RequestBody CourseQO qo);
	
	@RequestMapping(value = "/boss/course/course/delete/{id}", method = RequestMethod.DELETE)
	int deleteById(@PathVariable(value = "id") Long id);
	
	@RequestMapping(value = "/boss/course/course/update", method = RequestMethod.PUT)
	int updateById(@RequestBody CourseQO qo);
	
	@RequestMapping(value = "/boss/course/course/get/{id}", method = RequestMethod.GET)
	CourseVO getById(@PathVariable(value = "id") Long id);
	
	@RequestMapping(value = "/boss/course/course/getByCourseId/{id}", method = RequestMethod.GET)
	CourseVO getByCourseId(@PathVariable(value = "id") Long id);
	
}
