package com.roncoo.education.user.service.biz.gateway.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.roncoo.education.user.common.bean.bo.auth.AuthUserExtBO;
import com.roncoo.education.user.common.bean.bo.auth.AuthUserExtViewBO;
import com.roncoo.education.user.common.bean.dto.auth.AuthUserExtDTO;
import com.roncoo.education.user.service.dao.UserExtDao;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserExt;
import com.roncoo.education.util.aliyun.AliyunOssUtil;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.ResultEnum;
import com.roncoo.education.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 用户教育信息
 *
 * @author wujing
 */
@Component
public class AuthApiUserExtBiz {

	@Autowired
	private UserExtDao userExtDao;

	/**
	 * 用户信息查看接口
	 * 
	 * @param userNo
	 * @author wuyun
	 */
	public Result<AuthUserExtDTO> view(AuthUserExtViewBO bo) {
		if (ObjectUtil.isNull(bo)) {
			return Result.error("authUserExtUserNoBO不能为空");
		}
		if (null == bo.getUserNo()) {
			return Result.error("userNo不能为空");
		}
		UserExt userExt = userExtDao.getByUserNo(bo.getUserNo());
		if (userExt == null) {
			return Result.error("找不到该用户信息");
		}
		return Result.success(BeanUtil.copyProperties(userExt, AuthUserExtDTO.class));
	}

	/**
	 * 用户信息更新接口
	 * 
	 * @param authUserExtBO
	 * @author wuyun
	 */
	public Result<AuthUserExtDTO> update(AuthUserExtBO authUserExtBO) {
		if (authUserExtBO.getId() == null) {
			return Result.error("Id不能为空");
		}
		if (authUserExtBO.getUserNo() == null) {
			return Result.error("userNo不能为空");
		}

		// 根据传入userNo查找到对应的用户信息
		UserExt userExt = userExtDao.getByUserNo(authUserExtBO.getUserNo());
		if (ObjectUtil.isNull(userExt)) {
			return Result.error("没找到对应的用户信息");
		}
		// 如果修改图片删除阿里云oss上的原图片
		if (!StringUtils.isEmpty(authUserExtBO.getHeadImgUrl())) {
			if (!StringUtils.isEmpty(userExt.getHeadImgUrl()) && !authUserExtBO.getHeadImgUrl().equals(userExt.getHeadImgUrl())) {
				AliyunOssUtil.delete(userExt.getHeadImgUrl());
			}
		}
		userExt = BeanUtil.copyProperties(authUserExtBO, UserExt.class);
		int resultNum = userExtDao.updateByUserNo(userExt);
		if (resultNum > 0) {
			// 修改成功返回用户信息
			UserExt education = userExtDao.getByUserNo(authUserExtBO.getUserNo());
			AuthUserExtDTO dto = BeanUtil.copyProperties(education, AuthUserExtDTO.class);
			return Result.success(dto);
		}
		return Result.error(ResultEnum.USER_UPDATE_FAIL.getDesc());

	}

}
