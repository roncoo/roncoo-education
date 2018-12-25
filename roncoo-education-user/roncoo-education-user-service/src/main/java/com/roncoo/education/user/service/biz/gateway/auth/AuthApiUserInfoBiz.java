package com.roncoo.education.user.service.biz.gateway.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.user.service.dao.UserDao;

/**
 * 用户基本信息
 *
 * @author wuyun
 */
@Component
public class AuthApiUserInfoBiz {

	@Autowired
	private UserDao userDao;

}
