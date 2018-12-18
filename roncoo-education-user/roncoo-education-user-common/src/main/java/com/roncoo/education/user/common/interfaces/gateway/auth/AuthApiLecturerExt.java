package com.roncoo.education.user.common.interfaces.gateway.auth;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.user.common.bean.bo.auth.AuthLecturerExtViewBO;
import com.roncoo.education.user.common.bean.bo.auth.AuthUserExtBankBO;
import com.roncoo.education.user.common.bean.dto.auth.AuthLecturerExtViewDTO;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 讲师账户信息表
 *
 * @author wuyun
 */
public interface AuthApiLecturerExt {

	/**
	 * 账户信息详情接口
	 * 
	 * @param authLecturerExtViewBO
	 * @author wuyun
	 */
	@ApiOperation(value = "账户信息查看接口", notes = "账户信息详情接口")
	@RequestMapping(value = "/auth/user/api/lecturer/ext/view", method = RequestMethod.POST)
	Result<AuthLecturerExtViewDTO> view(@RequestBody AuthLecturerExtViewBO authLecturerExtViewBO);

	/**
	 * 更新用户银行卡信息接口
	 * 
	 * @param authUserExtBankBO
	 * @author wuyun
	 */
	@ApiOperation(value = "更新用户银行卡信息", notes = "更新用户银行卡信息接口")
	@RequestMapping(value = "/auth/user/api/lecturer/ext/update", method = RequestMethod.POST)
	Result<Integer> updateBackInfo(@RequestBody AuthUserExtBankBO authUserExtBankBO);
}
