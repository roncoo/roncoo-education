/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.tools;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * @author wujing
 */
public final class CookieUtil {

	private CookieUtil() {
	}

	public static String getCookieByName(HttpServletRequest request, String cookieName) {
		Cookie[] cookies = request.getCookies();
		if (null == cookies) {
			return "";
		} else {
			for (Cookie cookie : cookies) {
				if (cookieName.equals(cookie.getName())) {
					return cookie.getValue();
				}
			}
		}
		return "";
	}

}
