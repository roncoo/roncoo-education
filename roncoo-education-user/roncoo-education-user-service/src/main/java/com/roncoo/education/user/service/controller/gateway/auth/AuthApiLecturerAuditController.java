package com.roncoo.education.user.service.controller.gateway.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.user.common.bean.bo.auth.AuthLecturerAuditBO;
import com.roncoo.education.user.common.bean.bo.auth.AuthLecturerAuditSaveBO;
import com.roncoo.education.user.common.bean.dto.auth.AuthLecturerAuditViewDTO;
import com.roncoo.education.user.common.interfaces.gateway.auth.AuthApiLecturerAudit;
import com.roncoo.education.user.service.biz.gateway.auth.AuthApiLecturerAuditBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;

/**
 * 讲师信息-审核
 *
 * @author wujing
 */
@RestController
public class AuthApiLecturerAuditController extends BaseController implements AuthApiLecturerAudit {

	@Autowired
	private AuthApiLecturerAuditBiz biz;

	@Override
	public Result<Integer> update(@RequestBody AuthLecturerAuditBO authLecturerAuditBO) {
		return biz.update(authLecturerAuditBO);
	}

	@Override
	public Result<AuthLecturerAuditViewDTO> view(@PathVariable(value = "lecturerUserNo") Long lecturerUserNo) {
		return biz.view(lecturerUserNo);
	}

	@Override
	public Result<Integer> save(@RequestBody AuthLecturerAuditSaveBO authLecturerAuditSaveBO) {
		return biz.save(authLecturerAuditSaveBO);
	}

}
