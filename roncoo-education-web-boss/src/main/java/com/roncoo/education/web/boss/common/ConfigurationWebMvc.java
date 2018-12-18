/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.web.boss.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.roncoo.education.util.base.Base;

/**
 * 拦截器
 */
@Configuration
public class ConfigurationWebMvc implements WebMvcConfigurer{

	@Bean
	ShiroInterceptor shiroInterceptor() {
		return new ShiroInterceptor();
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// registry.addInterceptor(shiroInterceptor()).addPathPatterns("/admin/**");
	}
}

/**
 * 权限拦截器
 */
class ShiroInterceptor extends Base implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
	}

}
