package com.roncoo.education.user.service.controller.gateway;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.user.common.bean.bo.UserAuthBO;
import com.roncoo.education.user.common.bean.bo.UserLoginCodeBO;
import com.roncoo.education.user.common.bean.bo.UserLoginPasswordBO;
import com.roncoo.education.user.common.bean.bo.UserRegisterBO;
import com.roncoo.education.user.common.bean.bo.UserSendCodeBO;
import com.roncoo.education.user.common.bean.bo.auth.UserUpdateBO;
import com.roncoo.education.user.common.bean.dto.UserLoginDTO;
import com.roncoo.education.user.common.interfaces.gateway.ApiUserInfo;
import com.roncoo.education.user.service.biz.gateway.ApiUserInfoBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;

/**
 * 用户基本信息
 *
 * @author wujing
 */
@RestController
public class ApiUserInfoController extends BaseController implements ApiUserInfo {

	@Autowired
	private ApiUserInfoBiz biz;

	@Override
	public Result<UserLoginDTO> register(@RequestBody UserRegisterBO userRegisterBO) {
		return biz.register(userRegisterBO);
	}

	@Override
	public Result<UserLoginDTO> loginPassword(@RequestBody UserLoginPasswordBO userLoginPasswordBO) {
		return biz.loginPassword(userLoginPasswordBO);
	}

	@Override
	public Result<UserLoginDTO> loginCode(@RequestBody UserLoginCodeBO userLoginCodeBO) {
		return biz.loginCode(userLoginCodeBO);
	}

	@Override
	public Result<String> sendCode(@RequestBody UserSendCodeBO userSendCodeBO) {
		return biz.sendCode(userSendCodeBO);
	}

	@Override
	public Result<String> auth(@RequestBody UserAuthBO userAuthBO) {
		return null;
	}

	@Override
	public Result<Integer> updatePassword(@RequestBody UserUpdateBO userUpdateBO) {
		return biz.updatePassword(userUpdateBO);
	}

	@Override
	public String authRoncoo() throws UnsupportedEncodingException {
		return biz.authRoncoo();
	}
}
