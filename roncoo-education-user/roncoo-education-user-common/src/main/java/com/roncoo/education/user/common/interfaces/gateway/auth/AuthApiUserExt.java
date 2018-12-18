package com.roncoo.education.user.common.interfaces.gateway.auth;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.user.common.bean.bo.auth.AuthUserExtBO;
import com.roncoo.education.user.common.bean.bo.auth.AuthUserExtViewBO;
import com.roncoo.education.user.common.bean.dto.auth.AuthUserExtDTO;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 用户教育信息
 *
 * @author wujing
 */
public interface AuthApiUserExt {

	/**
	 * 用户信息查看接口
	 */
	@ApiOperation(value = "用户信息查看接口", notes = "根据userNo获取用户信息接口")
	@RequestMapping(value = "/auth/user/api/user/ext/view", method = RequestMethod.POST)
	Result<AuthUserExtDTO> view(@RequestBody AuthUserExtViewBO authUserExtUserNoBO);

	/**
	 * 用户信息更新接口
	 */
	@ApiOperation(value = "用户信息更新接口", notes = "用户信息更新接口")
	@RequestMapping(value = "/auth/user/api/user/ext/update", method = RequestMethod.POST)
	Result<AuthUserExtDTO> update(@RequestBody AuthUserExtBO authUserExtBO);

}
