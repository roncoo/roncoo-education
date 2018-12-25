package com.roncoo.education.user.common.interfaces.gateway.auth;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.user.common.bean.bo.auth.AuthUserUpdateBO;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 用户基本信息
 *
 * @author wujing
 */
public interface AuthApiUserInfo {

	/**
	 * 用户修改密码接口
	 * 
	 * @author wuyun
	 */
	@ApiOperation(value = "用户修改密码接口", notes = "用户修改密码接口")
	@RequestMapping(value = "/auth/user/api/user/update/password", method = RequestMethod.POST)
	Result<Integer> updatePassword(@RequestBody AuthUserUpdateBO authUserUpdateBO);
}
