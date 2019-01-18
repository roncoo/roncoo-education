package com.roncoo.education.course.common.interfaces.gateway.auth;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.course.service.common.bo.auth.AuthCourseAuditDeleteBO;
import com.roncoo.education.course.service.common.bo.auth.AuthCourseAuditListBO;
import com.roncoo.education.course.service.common.bo.auth.AuthCourseAuditSaveBO;
import com.roncoo.education.course.service.common.bo.auth.AuthCourseAuditStandBO;
import com.roncoo.education.course.service.common.bo.auth.AuthCourseAuditUpdateBO;
import com.roncoo.education.course.service.common.bo.auth.AuthCourseAuditViewBO;
import com.roncoo.education.course.service.common.dto.auth.AuthCourseAuditListDTO;
import com.roncoo.education.course.service.common.dto.auth.AuthCourseAuditSaveDTO;
import com.roncoo.education.course.service.common.dto.auth.AuthCourseAuditViewDTO;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 课程信息-审核
 *
 * @author wujing
 */
public interface AuthApiCourseAudit {

	/**
	 * 讲师课程详情接口
	 */
	@ApiOperation(value = "课程详情接口", notes = "根据课程ID返回课程详情信息")
	@RequestMapping(value = "/auth/course/api/course/audit/view", method = RequestMethod.POST)
	Result<AuthCourseAuditViewDTO> view(@RequestBody AuthCourseAuditViewBO authCourseAuditViewBO);

	/**
	 * 讲师课程分页接口
	 */
	@ApiOperation(value = "课程分页接口", notes = "根据条件分页列出课程信息")
	@RequestMapping(value = "/auth/course/api/course/audit/list", method = RequestMethod.POST)
	Result<Page<AuthCourseAuditListDTO>> listForPage(@RequestBody AuthCourseAuditListBO authCourseAuditListBO);

	/**
	 * 讲师课程保存接口
	 */
	@ApiOperation(value = "课程保存接口", notes = "课程保存接口")
	@RequestMapping(value = "/auth/course/api/course/audit/save", method = RequestMethod.POST)
	Result<AuthCourseAuditSaveDTO> save(@RequestBody AuthCourseAuditSaveBO authCourseAuditSaveBO);

	/**
	 * 讲师课程更新接口
	 */
	@ApiOperation(value = "课程更新接口", notes = "课程更新接口")
	@RequestMapping(value = "/auth/course/api/course/audit/update", method = RequestMethod.POST)
	Result<Integer> update(@RequestBody AuthCourseAuditUpdateBO authCourseAuditUpdateBO);

	/**
	 * 讲师课程信息设置上下架接口
	 */
	@ApiOperation(value = "课程上下架接口", notes = "课程上下架接口")
	@RequestMapping(value = "/auth/course/api/course/audit/stand", method = RequestMethod.POST)
	Result<Integer> stand(@RequestBody AuthCourseAuditStandBO authCourseAuditStandBO);

	/**
	 * 讲师课程信息删除接口
	 * 
	 * @author wuyun
	 */
	@ApiOperation(value = "删除接口", notes = "讲师课程信息删除接口")
	@RequestMapping(value = "/auth/course/api/course/audit/delete", method = RequestMethod.POST)
	Result<Integer> delete(@RequestBody AuthCourseAuditDeleteBO authCourseAuditDeleteBO);
}
