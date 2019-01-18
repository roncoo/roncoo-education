package com.roncoo.education.course.common.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.course.common.bean.qo.CourseChapterPeriodAuditQO;
import com.roncoo.education.course.common.bean.vo.CourseChapterPeriodAuditVO;
import com.roncoo.education.util.base.Page;

/**
 * 课时信息-审核
 *
 * @author wujing
 */
public interface BossCourseChapterPeriodAudit {

	@RequestMapping(value = "/boss/course/courseChapterPeriodAudit/listForPage", method = RequestMethod.POST)
	Page<CourseChapterPeriodAuditVO> listForPage(@RequestBody CourseChapterPeriodAuditQO qo);

	@RequestMapping(value = "/boss/course/courseChapterPeriodAudit/save", method = RequestMethod.POST)
	int save(@RequestBody CourseChapterPeriodAuditQO qo);

	@RequestMapping(value = "/boss/course/courseChapterPeriodAudit/delete/{id}", method = RequestMethod.DELETE)
	int deleteById(@PathVariable(value = "id") Long id);

	@RequestMapping(value = "/boss/course/courseChapterPeriodAudit/update", method = RequestMethod.PUT)
	int updateById(@RequestBody CourseChapterPeriodAuditQO qo);

	@RequestMapping(value = "/boss/course/courseChapterPeriodAudit/get/{id}", method = RequestMethod.GET)
	CourseChapterPeriodAuditVO getById(@PathVariable(value = "id") Long id);

	/**
	 * 根据视频编号查询课时信息
	 * 
	 * @param videoNo
	 * @return
	 * @author wuyun
	 */
	@RequestMapping(value = "/boss/course/periodVideoInfoAudit/getByVideoNo", method = RequestMethod.GET)
	CourseChapterPeriodAuditVO getByVideoNo(@RequestBody Long videoNo);
}
