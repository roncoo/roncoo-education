package com.roncoo.education.user.common.interfaces.gateway.auth;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.user.common.bean.bo.auth.AuthLecturerAuditBO;
import com.roncoo.education.user.common.bean.bo.auth.AuthLecturerAuditSaveBO;
import com.roncoo.education.user.common.bean.bo.auth.AuthLecturerAuditViewBO;
import com.roncoo.education.user.common.bean.dto.auth.AuthLecturerAuditViewDTO;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 讲师信息-审核
 *
 * @author wujing
 */
public interface AuthApiLecturerAudit {

	/**
	 * 讲师信息修改接口
	 */
	@ApiOperation(value = "讲师修改接口", notes = "修改讲师信息")
	@RequestMapping(value = "/auth/user/api/lecturer/audit/update", method = RequestMethod.POST)
	Result<Integer> update(@RequestBody AuthLecturerAuditBO authLecturerAuditBO);

	/**
	 * 讲师信息查看接口
	 */
	@ApiOperation(value = "讲师查看接口", notes = "根据讲师用户编号查看讲师信息")
	@RequestMapping(value = "/auth/user/api/lecturer/audit/view", method = RequestMethod.POST)
	Result<AuthLecturerAuditViewDTO> view(@RequestBody AuthLecturerAuditViewBO authLecturerAuditViewBO);

	/**
	 * 讲师申请接口
	 *
	 * @param authLecturerAuditSaveBO
	 * @author wuyun
	 */
	@ApiOperation(value = "讲师申请接口", notes = "保存讲师信息接口,需要审核")
	@RequestMapping(value = "/auth/user/api/lecturer/audit/save", method = RequestMethod.POST)
	Result<Integer> save(@RequestBody AuthLecturerAuditSaveBO authLecturerAuditSaveBO);
}
