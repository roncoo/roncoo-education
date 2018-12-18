/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.web.boss.common;

import org.apache.shiro.SecurityUtils;

import com.roncoo.education.util.tools.Constants;

public final class SessionUtil {

	private SessionUtil() {
	}
	
	public static Long getUserNo() {
		return Long.valueOf(SecurityUtils.getSubject().getSession().getAttribute(Constants.Session.USER_NO).toString());
	}

	public static String getRealName() {
		return SecurityUtils.getSubject().getSession().getAttribute(Constants.Session.REAL_NAME).toString();
	}

}
