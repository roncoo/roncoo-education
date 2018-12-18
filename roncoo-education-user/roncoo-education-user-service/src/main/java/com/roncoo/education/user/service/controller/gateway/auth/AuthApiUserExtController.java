package com.roncoo.education.user.service.controller.gateway.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.user.common.bean.bo.auth.AuthUserExtBO;
import com.roncoo.education.user.common.bean.bo.auth.AuthUserExtViewBO;
import com.roncoo.education.user.common.bean.dto.auth.AuthUserExtDTO;
import com.roncoo.education.user.common.interfaces.gateway.auth.AuthApiUserExt;
import com.roncoo.education.user.service.biz.gateway.auth.AuthApiUserExtBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;

/**
 * 用户教育信息
 *
 * @author wujing
 */
@RestController
public class AuthApiUserExtController extends BaseController implements AuthApiUserExt {

	@Autowired
	private AuthApiUserExtBiz biz;

	@Override
	public Result<AuthUserExtDTO> view(@RequestBody AuthUserExtViewBO bo) {
		return biz.view(bo);
	}

	@Override
	public Result<AuthUserExtDTO> update(@RequestBody AuthUserExtBO authUserExtBO) {
		return biz.update(authUserExtBO);
	}

}
