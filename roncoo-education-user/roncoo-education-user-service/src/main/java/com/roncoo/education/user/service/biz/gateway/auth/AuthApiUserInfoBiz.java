package com.roncoo.education.user.service.biz.gateway.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.roncoo.education.user.common.bean.bo.auth.AuthUserUpdateBO;
import com.roncoo.education.user.service.dao.UserDao;
import com.roncoo.education.user.service.dao.impl.mapper.entity.User;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.ResultEnum;
import com.roncoo.education.util.tools.StrUtil;
import com.xiaoleilu.hutool.crypto.DigestUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 用户基本信息
 *
 * @author wuyun
 */
@Component
public class AuthApiUserInfoBiz {

	@Autowired
	private UserDao userDao;

	/**
	 * 用户修改密码接口
	 * 
	 * @author wuyun
	 */
	public Result<Integer> updatePassword(AuthUserUpdateBO authUserUpdateBO) {
		if (StringUtils.isEmpty(authUserUpdateBO.getMobile())) {
			return Result.error("手机号为空,请重试");
		}

		// 手机号去空处理
		String mobile = authUserUpdateBO.getMobile().replaceAll(" +", "");

		if (StringUtils.isEmpty(authUserUpdateBO.getConfirmPassword())) {
			return Result.error("新登录密码为空,请重试");
		}
		if (!authUserUpdateBO.getNewPassword().equals(authUserUpdateBO.getConfirmPassword())) {
			return Result.error("密码输入不一致，请重试");
		}

		User user = userDao.getByMobile(mobile);
		if (ObjectUtil.isNull(user)) {
			return Result.error("没找到用户信息,请重试");
		}
		if (DigestUtil.sha1Hex(user.getMobileSalt() + authUserUpdateBO.getNewPassword()).equals(user.getMobilePsw())) {
			return Result.error("输入的密码与原密码一致,请重试");
		}

		// 更新密码
		User bean = new User();
		bean.setId(user.getId());
		bean.setMobileSalt(StrUtil.get32UUID());
		bean.setMobilePsw(DigestUtil.sha1Hex(bean.getMobileSalt() + authUserUpdateBO.getNewPassword()));
		int result = userDao.updateById(bean);
		return result == 1 ? Result.success(result) : Result.error(ResultEnum.USER_UPDATE_FAIL.getDesc());
	}

}
