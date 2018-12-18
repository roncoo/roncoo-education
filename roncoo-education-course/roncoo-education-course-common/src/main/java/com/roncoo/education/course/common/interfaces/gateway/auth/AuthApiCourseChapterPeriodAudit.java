package com.roncoo.education.course.common.interfaces.gateway.auth;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.course.common.bean.bo.auth.AuthCourseChapterPeriodAuditDeleteBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthCourseChapterPeriodAuditSaveBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthCourseChapterPeriodAuditSortBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthCourseChapterPeriodAuditUpdateBO;
import com.roncoo.education.course.common.bean.dto.auth.AuthCourseChapterPeriodAuditListDTO;
import com.roncoo.education.course.common.bean.dto.auth.AuthCourseChapterPeriodAuditSaveDTO;
import com.roncoo.education.course.common.bean.dto.auth.AuthCourseChapterPeriodAuditViewDTO;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 课时信息-审核
 *
 * @author wujing
 */
public interface AuthApiCourseChapterPeriodAudit {

	/**
	 * 课时列出接口
	 */
	@ApiOperation(value = "课时列出接口", notes = "根据章节ID列出课时信息")
	@RequestMapping(value = "/auth/course/api/course/chapter/period/audit/list/{chapterId}", method = RequestMethod.POST)
	Result<AuthCourseChapterPeriodAuditListDTO> listByChapterId(@PathVariable(name = "chapterId") Long chapterId);

	/**
	 * 课时查看接口
	 */
	@ApiOperation(value = "课时查看接口", notes = "课时查看接口")
	@RequestMapping(value = "/auth/course/api/course/chapter/period/audit/view/{id}", method = RequestMethod.POST)
	Result<AuthCourseChapterPeriodAuditViewDTO> view(@PathVariable(name = "id") Long id);

	/**
	 * 课时删除接口
	 */
	@ApiOperation(value = "课时删除接口", notes = "课时删除接口")
	@RequestMapping(value = "/auth/course/api/course/chapter/period/audit/delete", method = RequestMethod.POST)
	Result<Integer> delete(@RequestBody AuthCourseChapterPeriodAuditDeleteBO authCourseChapterPeriodAuditDeleteBO);

	/**
	 * 课时添加接口
	 */
	@ApiOperation(value = "课时添加接口", notes = "课时添加接口")
	@RequestMapping(value = "/auth/course/api/course/chapter/period/audit/save", method = RequestMethod.POST)
	Result<AuthCourseChapterPeriodAuditSaveDTO> save(@RequestBody AuthCourseChapterPeriodAuditSaveBO bo);

	/**
	 * 课时更新接口
	 */
	@ApiOperation(value = "课时更新接口", notes = "课时更新接口")
	@RequestMapping(value = "/auth/course/api/course/chapter/period/audit/update", method = RequestMethod.POST)
	Result<Integer> update(@RequestBody AuthCourseChapterPeriodAuditUpdateBO bo);

	/**
	 * 课时排序接口
	 */
	@ApiOperation(value = "更新课时排序", notes = "更新课时排序")
	@RequestMapping(value = "/auth/course/api/course/chapter/period/audit/sort", method = RequestMethod.POST)
	Result<Integer> sort(@RequestBody AuthCourseChapterPeriodAuditSortBO bo);

}
