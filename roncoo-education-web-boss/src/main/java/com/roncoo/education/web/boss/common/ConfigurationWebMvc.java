/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.web.boss.common;

import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.roncoo.education.util.base.Base;
import com.roncoo.education.util.base.Bjui;
import com.roncoo.education.util.tools.Constants;
import com.roncoo.education.util.tools.JSONUtil;
import com.roncoo.education.web.boss.common.bean.vo.SysMenuVO;
import com.xiaoleilu.hutool.util.CollectionUtil;

/**
 * 拦截器
 */
@Configuration
public class ConfigurationWebMvc implements WebMvcConfigurer {

	private static final String[] PATH = new String[] { "/admin/**", "/course/**", "/system/**", "/user/**" };

	@Bean
	ShiroInterceptor shiroInterceptor() {
		return new ShiroInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(shiroInterceptor()).addPathPatterns(PATH);
	}
}

/**
 * 权限拦截器
 */
class ShiroInterceptor extends Base implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String uri = request.getServletPath();
		if (checkUri(uri)) {
			return true;
		}

		logger.error("没此权限，当前访问路径为：{}", uri);
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		Bjui bj = new Bjui();
		bj.setStatusCode(300);
		bj.setMessage("没此权限，请联系管理员！");
		out.print(JSONUtil.toJSONString(bj));
		out.flush();
		out.close();
		return false;
	}

	private static Boolean checkUri(String uri) {
		List<SysMenuVO> menuVOList = JSONUtil.parseArray(SecurityUtils.getSubject().getSession().getAttribute(Constants.Session.BOSS_MENU).toString(), SysMenuVO.class);
		Set<String> menuSet = new HashSet<>();
		listMenu(menuSet, menuVOList);
		if (StringUtils.hasText(uri) && uri.endsWith("/")) {
			uri = uri.substring(0, uri.length() - 1);
		}
		for (String s : menuSet) {
			if (s.contains(uri)) {
				return true;
			}
		}
		return false;
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

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}

}
