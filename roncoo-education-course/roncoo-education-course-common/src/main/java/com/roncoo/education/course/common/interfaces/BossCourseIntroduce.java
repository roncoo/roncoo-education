package com.roncoo.education.course.common.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.course.common.bean.qo.CourseIntroduceQO;
import com.roncoo.education.course.common.bean.vo.CourseIntroduceVO;
import com.roncoo.education.util.base.Page;

/**
 * 课程介绍信息 
 *
 * @author wujing
 */
public interface BossCourseIntroduce {
	
	@RequestMapping(value = "/boss/course/courseIntroduce/listForPage", method = RequestMethod.POST)
	Page<CourseIntroduceVO> listForPage(@RequestBody CourseIntroduceQO qo);
	
	@RequestMapping(value = "/boss/course/courseIntroduce/save", method = RequestMethod.POST)
	int save(@RequestBody CourseIntroduceQO qo);
	
	@RequestMapping(value = "/boss/course/courseIntroduce/delete/{id}", method = RequestMethod.DELETE)
	int deleteById(@PathVariable(value = "id") Long id);
	
	@RequestMapping(value = "/boss/course/courseIntroduce/update", method = RequestMethod.PUT)
	int updateById(@RequestBody CourseIntroduceQO qo);
	
	@RequestMapping(value = "/boss/course/courseIntroduce/get/{id}", method = RequestMethod.GET)
	CourseIntroduceVO getById(@PathVariable(value = "id") Long id);
	
}
