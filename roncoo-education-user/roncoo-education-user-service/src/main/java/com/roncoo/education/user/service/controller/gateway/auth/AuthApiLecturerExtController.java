package com.roncoo.education.user.service.controller.gateway.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.user.common.bean.bo.auth.AuthLecturerExtViewBO;
import com.roncoo.education.user.common.bean.bo.auth.AuthUserExtBankBO;
import com.roncoo.education.user.common.bean.dto.auth.AuthLecturerExtViewDTO;
import com.roncoo.education.user.common.interfaces.gateway.auth.AuthApiLecturerExt;
import com.roncoo.education.user.service.biz.gateway.auth.AuthApiLecturerExtBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;

/**
 * 讲师账户信息表
 *
 * @author wujing
 */
@RestController
public class AuthApiLecturerExtController extends BaseController implements AuthApiLecturerExt {

	@Autowired
	private AuthApiLecturerExtBiz biz;

	@Override
	public Result<AuthLecturerExtViewDTO> view(@RequestBody AuthLecturerExtViewBO authLecturerExtViewBO) {
		return biz.view(authLecturerExtViewBO);
	}

	@Override
	public Result<Integer> updateBackInfo(@RequestBody AuthUserExtBankBO authUserExtBankBO) {
		return biz.updateBackInfo(authUserExtBankBO);
	}

}
