package com.roncoo.education.course.common.interfaces.gateway.auth;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.course.common.bean.bo.auth.AuthCourseChapterAuditDeleteBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthCourseChapterAuditSaveBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthCourseChapterAuditSortBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthCourseChapterAuditUpdateBO;
import com.roncoo.education.course.common.bean.dto.auth.AuthCourseChapterAuditListDTO;
import com.roncoo.education.course.common.bean.dto.auth.AuthCourseChapterAuditSaveDTO;
import com.roncoo.education.course.common.bean.dto.auth.AuthCourseChapterAuditUpdateDTO;
import com.roncoo.education.course.common.bean.dto.auth.AuthCourseChapterAuditViewDTO;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 章节信息-审核
 *
 * @author wujing
 */
public interface AuthApiCourseChapterAudit {

	/**
	 * 章节查看接口
	 */
	@ApiOperation(value = "章节查看接口", notes = "根据章节ID查看章节信息")
	@RequestMapping(value = "/auth/course/api/course/chapter/audit/view/{id}", method = RequestMethod.POST)
	Result<AuthCourseChapterAuditViewDTO> view(@PathVariable(name = "id") Long id);

	/**
	 * 章节删除接口
	 */
	@ApiOperation(value = "章节删除接口", notes = "章节删除接口")
	@RequestMapping(value = "/auth/course/api/course/chapter/audit/delete", method = RequestMethod.POST)
	Result<Integer> delete(@RequestBody AuthCourseChapterAuditDeleteBO authCourseChapterAuditDeleteBO);

	/**
	 * 章节列出接口
	 */
	@ApiOperation(value = "章节列出接口", notes = "根据课程ID列出章节信息")
	@RequestMapping(value = "/auth/course/api/course/chapter/audit/list/{courseId}", method = RequestMethod.POST)
	Result<AuthCourseChapterAuditListDTO> listByCourseNo(@PathVariable(name = "courseId") Long courseId);

	/**
	 * 章节添加接口
	 */
	@ApiOperation(value = "章节添加接口", notes = "章节添加接口")
	@RequestMapping(value = "/auth/course/api/course/chapter/audit/save", method = RequestMethod.POST)
	Result<AuthCourseChapterAuditSaveDTO> save(@RequestBody AuthCourseChapterAuditSaveBO bo);

	/**
	 * 章节更新接口
	 */
	@ApiOperation(value = "章节更新接口", notes = "章节更新接口")
	@RequestMapping(value = "/auth/course/api/course/chapter/audit/update", method = RequestMethod.POST)
	Result<AuthCourseChapterAuditUpdateDTO> update(@RequestBody AuthCourseChapterAuditUpdateBO bo);

	/**
	 * 更新章节排序接口
	 */
	@ApiOperation(value = "更新章节排序接口", notes = "更新章节排序接口")
	@RequestMapping(value = "/auth/course/api/course/chapter/audit/sort", method = RequestMethod.POST)
	Result<Integer> sort(@RequestBody AuthCourseChapterAuditSortBO bo);

}
