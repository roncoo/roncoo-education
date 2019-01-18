package com.roncoo.education.course.common.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.course.common.bean.qo.CourseIntroduceAuditQO;
import com.roncoo.education.course.common.bean.vo.CourseIntroduceAuditVO;
import com.roncoo.education.util.base.Page;

/**
 * 课程介绍信息 
 *
 * @author wujing
 */
public interface BossCourseIntroduceAudit {
	
	@RequestMapping(value = "/boss/course/courseIntroduceAudit/listForPage", method = RequestMethod.POST)
	Page<CourseIntroduceAuditVO> listForPage(@RequestBody CourseIntroduceAuditQO qo);
	
	@RequestMapping(value = "/boss/course/courseIntroduceAudit/save", method = RequestMethod.POST)
	int save(@RequestBody CourseIntroduceAuditQO qo);
	
	@RequestMapping(value = "/boss/course/courseIntroduceAudit/delete/{id}", method = RequestMethod.DELETE)
	int deleteById(@PathVariable(value = "id") Long id);
	
	@RequestMapping(value = "/boss/course/courseIntroduceAudit/update", method = RequestMethod.PUT)
	int updateById(@RequestBody CourseIntroduceAuditQO qo);
	
	@RequestMapping(value = "/boss/course/courseIntroduceAudit/get/{id}", method = RequestMethod.GET)
	CourseIntroduceAuditVO getById(@PathVariable(value = "id") Long id);
	
}
