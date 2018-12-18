/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.tools;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.util.WebUtils;

/**
 * @author wujing
 */
public final class WebUtil {

	private static final String PREFIX = "s_";

	private WebUtil() {
	}

	/**
	 * 获取s_开头的所有参数
	 */
	public static Map<String, Object> getParametersStartingWith(ServletRequest request) {
		return WebUtils.getParametersStartingWith(request, PREFIX);
	}

	/**
	 * 返回param，以s_开头
	 */
	public static String encodeParameterStringWithPrefix(Map<String, Object> map) {
		StringBuilder queryStringBuilder = new StringBuilder();
		Iterator<Entry<String, Object>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Object> entry = it.next();
			queryStringBuilder.append('&').append(PREFIX).append(entry.getKey()).append('=').append(entry.getValue());
		}
		return queryStringBuilder.toString();
	}

	/**
	 * 判断是否为ajax请求
	 * 
	 * @param req
	 * @return
	 */
	public static boolean isAjax(HttpServletRequest req) {
		return req.getHeader("x-requested-with") != null && req.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest");
	}

	/**
	 * 封装请求参数，转换成map
	 * 
	 * @param webRequest
	 * @return
	 */
	public static Map<String, Object> getMap(WebRequest webRequest) {
		Map<String, Object> mp = new HashMap<>();
		Map<String, String[]> map = webRequest.getParameterMap();
		for (Entry<String, String[]> m : map.entrySet()) {
			for (String s : m.getValue()) {
				if (StringUtils.hasText(s)) {
					mp.put(m.getKey().trim(), s.trim());
				}
			}
		}
		return mp;
	}

	/**
	 * 返回param
	 */
	public static String encodeParameterString(Map<String, Object> map) {
		StringBuilder queryStringBuilder = new StringBuilder();
		Iterator<Entry<String, Object>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Entry<String, Object> entry = it.next();
			queryStringBuilder.append('&').append(entry.getKey()).append('=').append(entry.getValue());
		}
		return queryStringBuilder.toString();
	}

}
