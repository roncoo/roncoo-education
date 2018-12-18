/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.web.auth.common;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.tools.JSONUtil;

import feign.FeignException;

/**
 * 异常拦截
 */
@ControllerAdvice
public class HandlerWebAuthException extends BaseController {

	@ExceptionHandler({ FeignException.class })
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView processException(FeignException e) {
		String msg = e.getMessage().split("\n|\r\n|\r")[1];
		logger.error(msg);
		ModelAndView mv = new ModelAndView();
		@SuppressWarnings("unchecked")
		Map<String, Object> m = JSONUtil.parseObject(msg, Map.class);
		mv.addObject("errorMsg", m);
		mv.setViewName("error/5xx");
		return mv;
	}

	@ExceptionHandler({ Exception.class })
	@ResponseStatus(HttpStatus.OK)
	public ModelAndView processException(Exception e) {
		logger.error(e.getMessage());
		ModelAndView mv = new ModelAndView();
		mv.addObject("errorMsg", e.getMessage());
		mv.setViewName("error/5xx");
		return mv;
	}
}
