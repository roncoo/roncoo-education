package com.roncoo.education.course.common.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.course.common.bean.qo.CourseChapterAuditQO;
import com.roncoo.education.course.common.bean.vo.CourseChapterAuditVO;
import com.roncoo.education.util.base.Page;

/**
 * 章节信息-审核 
 *
 * @author wujing
 */
public interface BossCourseChapterAudit {
	
	@RequestMapping(value = "/boss/course/courseChapterAudit/listForPage", method = RequestMethod.POST)
	Page<CourseChapterAuditVO> listForPage(@RequestBody CourseChapterAuditQO qo);
	
	@RequestMapping(value = "/boss/course/courseChapterAudit/save", method = RequestMethod.POST)
	int save(@RequestBody CourseChapterAuditQO qo);
	
	@RequestMapping(value = "/boss/course/courseChapterAudit/delete/{id}", method = RequestMethod.DELETE)
	int deleteById(@PathVariable(value = "id") Long id);
	
	@RequestMapping(value = "/boss/course/courseChapterAudit/update", method = RequestMethod.PUT)
	int updateById(@RequestBody CourseChapterAuditQO qo);
	
	@RequestMapping(value = "/boss/course/courseChapterAudit/get/{id}", method = RequestMethod.GET)
	CourseChapterAuditVO getById(@PathVariable(value = "id") Long id);
	
}
