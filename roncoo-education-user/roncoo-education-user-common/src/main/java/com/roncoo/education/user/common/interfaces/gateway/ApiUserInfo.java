package com.roncoo.education.user.common.interfaces.gateway;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.user.common.bean.bo.UserAuthBO;
import com.roncoo.education.user.common.bean.bo.UserLoginCodeBO;
import com.roncoo.education.user.common.bean.bo.UserLoginPasswordBO;
import com.roncoo.education.user.common.bean.bo.UserRegisterBO;
import com.roncoo.education.user.common.bean.bo.UserSendCodeBO;
import com.roncoo.education.user.common.bean.bo.auth.UserUpdateBO;
import com.roncoo.education.user.common.bean.dto.UserLoginDTO;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 用户基本信息
 *
 * @author wujing
 */
public interface ApiUserInfo {

	/**
	 * 注册接口
	 */
	@ApiOperation(value = "注册接口", notes = "注册成功返回登录信息")
	@RequestMapping(value = "/user/api/user/register", method = RequestMethod.POST)
	Result<UserLoginDTO> register(@RequestBody UserRegisterBO userRegisterBO);

	/**
	 * 用户密码登录接口
	 */
	@ApiOperation(value = "用户密码登录接口", notes = "用户密码登录")
	@RequestMapping(value = "/user/api/user/login/password", method = RequestMethod.POST)
	Result<UserLoginDTO> loginPassword(@RequestBody UserLoginPasswordBO userLoginPasswordBO);

	/**
	 * 用户验证码登录接口
	 */
	@ApiOperation(value = "用户验证码登录接口", notes = "用户验证码登录")
	@RequestMapping(value = "/user/api/user/login/code", method = RequestMethod.POST)
	Result<UserLoginDTO> loginCode(@RequestBody UserLoginCodeBO userLoginCodeBO);

	/**
	 * 验证码发送接口
	 */
	@ApiOperation(value = "验证码发送接口", notes = "发送手机验证码")
	@RequestMapping(value = "/user/api/user/send/code", method = RequestMethod.POST)
	Result<String> sendCode(@RequestBody UserSendCodeBO userSendCodeBO);

	/**
	 * 授权登录接口
	 */
	@ApiOperation(value = "授权登录接口", notes = "后台登录到讲师中心时使用")
	@RequestMapping(value = "/user/api/user/auth", method = RequestMethod.POST)
	Result<String> auth(@RequestBody UserAuthBO userAuthBO);
	
	/**
	 * 用户修改密码接口
	 * 
	 * @author wuyun
	 */
	@ApiOperation(value = "用户修改密码接口", notes = "用户修改密码接口")
	@RequestMapping(value = "/user/api/user/update/password", method = RequestMethod.POST)
	Result<Integer> updatePassword(@RequestBody UserUpdateBO userUpdateBO);

}
