/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.web.boss.common;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import com.roncoo.education.user.common.bean.vo.UserVO;
import com.roncoo.education.user.feign.IBossUser;
import com.roncoo.education.util.tools.Constants;
import com.roncoo.education.util.tools.JSONUtil;
import com.roncoo.education.web.boss.common.bean.vo.SysMenuVO;
import com.roncoo.education.web.boss.service.dao.SysUserDao;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysUser;
import com.roncoo.spring.boot.autoconfigure.shiro.ShiroRealm;
import com.xiaoleilu.hutool.crypto.DigestUtil;
import com.xiaoleilu.hutool.util.CollectionUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * shiro配置类
 */
@Configuration
public class ConfigurationShiro {

	/**
	 * ShiroRealm
	 */
	@Bean(name = "shiroRealm")
	public ShiroRealm shiroRealm() {
		ShiroCustomRealm realm = new ShiroCustomRealm();
		return realm;
	}

}

class ShiroCustomRealm extends ShiroRealm {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private IBossUser bossUser;
	@Autowired
	private SysUserDao sysUserDao;

	/**
	 * 授权认证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {
		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		List<SysMenuVO> menuVOList = JSONUtil.parseArray(SecurityUtils.getSubject().getSession().getAttribute(Constants.Session.BOSS_MENU).toString(), SysMenuVO.class);
		Set<String> menuSet = new HashSet<>();
		// 处理菜单权限
		listMenu(menuSet, menuVOList);
		simpleAuthorizationInfo.setStringPermissions(menuSet);
		return simpleAuthorizationInfo;
	}

	/**
	 * 登录认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken arg0) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken) arg0;
		SysUser sysUser = sysUserDao.getByMobile(token.getUsername());
		if (ObjectUtil.isNull(sysUser)) {
			throw new AuthenticationException("账号不存在");
		}
		UserVO user = bossUser.getByMobile(token.getUsername());
		if (ObjectUtil.isNull(sysUser)) {
			throw new AuthenticationException("账号或者密码不正确");
		}
		if (!DigestUtil.sha1Hex(user.getMobileSalt() + String.valueOf(token.getPassword())).equals(user.getMobilePsw())) {
			throw new AuthenticationException("账号或者密码不正确");
		}
		SecurityUtils.getSubject().getSession().setAttribute(Constants.Session.USER_NO, sysUser.getUserNo());
		SecurityUtils.getSubject().getSession().setAttribute(Constants.Session.REAL_NAME, sysUser.getRealName());
		SecurityUtils.getSubject().getSession().setAttribute(Constants.Session.USER_VO, JSONUtil.toJSONString(sysUser));
		return new SimpleAuthenticationInfo(token, token.getPassword(), token.getUsername());
	}

	/**
	 * @param list
	 * @return
	 */
	private static void listMenu(Set<String> menuSet, List<SysMenuVO> menuVOList) {
		if (CollectionUtil.isNotEmpty(menuVOList)) {
			for (SysMenuVO sm : menuVOList) {
				if (StringUtils.hasText(sm.getMenuUrl())) {
					menuSet.add(sm.getMenuUrl());
				}
				listMenu(menuSet, sm.getList());
			}
		}
	}

}
