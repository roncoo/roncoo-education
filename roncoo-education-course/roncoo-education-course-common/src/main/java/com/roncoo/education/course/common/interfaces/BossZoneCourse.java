package com.roncoo.education.course.common.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.course.common.bean.qo.ZoneCourseQO;
import com.roncoo.education.course.common.bean.vo.ZoneCourseVO;
import com.roncoo.education.util.base.Page;

/**
 * 专区课程关联表 
 *
 * @author wujing
 */
public interface BossZoneCourse {
	
	@RequestMapping(value = "/boss/course/zoneCourse/listForPage", method = RequestMethod.POST)
	Page<ZoneCourseVO> listForPage(@RequestBody ZoneCourseQO qo);
	
	@RequestMapping(value = "/boss/course/zoneCourse/save", method = RequestMethod.POST)
	int save(@RequestBody ZoneCourseQO qo);
	
	@RequestMapping(value = "/boss/course/zoneCourse/delete/{id}", method = RequestMethod.DELETE)
	int deleteById(@PathVariable(value = "id") Long id);
	
	@RequestMapping(value = "/boss/course/zoneCourse/update", method = RequestMethod.PUT)
	int updateById(@RequestBody ZoneCourseQO qo);
	
	@RequestMapping(value = "/boss/course/zoneCourse/get/{id}", method = RequestMethod.GET)
	ZoneCourseVO getById(@PathVariable(value = "id") Long id);
	
}
