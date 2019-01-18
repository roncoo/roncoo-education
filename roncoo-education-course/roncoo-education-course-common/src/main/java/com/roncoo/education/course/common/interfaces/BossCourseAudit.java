package com.roncoo.education.course.common.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.course.common.bean.qo.CourseAuditQO;
import com.roncoo.education.course.common.bean.vo.CourseAuditVO;
import com.roncoo.education.util.base.Page;

/**
 * 课程信息-审核
 *
 * @author wujing
 */
public interface BossCourseAudit {

	@RequestMapping(value = "/boss/course/courseAudit/listForPage", method = RequestMethod.POST)
	Page<CourseAuditVO> listForPage(@RequestBody CourseAuditQO qo);

	@RequestMapping(value = "/boss/course/courseAudit/save", method = RequestMethod.POST)
	int save(@RequestBody CourseAuditQO qo);

	@RequestMapping(value = "/boss/course/courseAudit/delete/{id}", method = RequestMethod.DELETE)
	int deleteById(@PathVariable(value = "id") Long id);

	@RequestMapping(value = "/boss/course/courseAudit/update", method = RequestMethod.PUT)
	int updateById(@RequestBody CourseAuditQO qo);

	@RequestMapping(value = "/boss/course/courseAudit/get/{id}", method = RequestMethod.GET)
	CourseAuditVO getById(@PathVariable(value = "id") Long id);

	@RequestMapping(value = "/boss/course/courseAudit/audit", method = RequestMethod.PUT)
	int audit(@RequestBody CourseAuditQO qo);

	/**
	 * 更新状态信息
	 * 
	 * @param qo
	 * @return
	 * @author wuyun
	 */
	@RequestMapping(value = "/boss/course/courseAudit/updateStatusId", method = RequestMethod.PUT)
	int updateStatusId(CourseAuditQO qo);
}
