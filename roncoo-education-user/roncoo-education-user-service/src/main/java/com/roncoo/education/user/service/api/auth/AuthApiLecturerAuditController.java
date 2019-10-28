package com.roncoo.education.user.service.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.user.service.api.auth.biz.AuthApiLecturerAuditBiz;
import com.roncoo.education.user.common.bo.auth.AuthLecturerAuditBO;
import com.roncoo.education.user.common.bo.auth.AuthLecturerAuditSaveBO;
import com.roncoo.education.user.common.bo.auth.AuthLecturerAuditViewBO;
import com.roncoo.education.user.common.dto.auth.AuthLecturerAuditViewDTO;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 讲师信息-审核
 *
 * @author wujing
 */
@RestController
@RequestMapping(value = "/user/auth/lecturer/audit")
public class AuthApiLecturerAuditController extends BaseController {

	@Autowired
	private AuthApiLecturerAuditBiz biz;

	/**
	 * 讲师信息修改接口
	 */
	@ApiOperation(value = "讲师修改接口", notes = "修改讲师信息")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody AuthLecturerAuditBO authLecturerAuditBO) {
		return biz.update(authLecturerAuditBO);
	}

	/**
	 * 讲师信息查看接口
	 */
	@ApiOperation(value = "讲师查看接口", notes = "根据讲师用户编号查看讲师信息")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<AuthLecturerAuditViewDTO> view(@RequestBody AuthLecturerAuditViewBO authLecturerAuditViewBO) {
		return biz.view(authLecturerAuditViewBO);
	}

	/**
	 * 讲师申请接口
	 *
	 * @param authLecturerAuditSaveBO
	 * @author wuyun
	 */
	@ApiOperation(value = "讲师申请接口", notes = "保存讲师信息接口,需要审核")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<Integer> save(@RequestBody AuthLecturerAuditSaveBO authLecturerAuditSaveBO) {
		return biz.save(authLecturerAuditSaveBO);
	}

}
