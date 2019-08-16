/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.web.boss.common;

import java.util.Map;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.BaseException;
import com.roncoo.education.util.tools.JSONUtil;

import feign.FeignException;

/**
 * 异常拦截
 */
@RestControllerAdvice
public class HandlerWebBossException extends BaseController {

	@ExceptionHandler({ UnauthorizedException.class })
	@ResponseStatus(HttpStatus.OK)
	public String processException(UnauthorizedException e) {
		logger.error(e.getMessage(), e);
		return error("没此权限，请联系管理员");
	}
	
	@ExceptionHandler({ FeignException.class })
	@ResponseStatus(HttpStatus.OK)
	public String processException(FeignException e) {
		logger.error(e.getCause().toString());
		String msg = e.getCause().toString().split("\n|\r\n|\r")[1];
		@SuppressWarnings("unchecked")
		Map<String, Object> m = JSONUtil.parseObject(msg, Map.class);
		return error(m.get("message").toString());
	}
	
	@ExceptionHandler({ HystrixRuntimeException.class })
	@ResponseStatus(HttpStatus.OK)
	public String processException(HystrixRuntimeException e) {
		logger.error(e.getCause().toString());
		String msg = e.getCause().toString().split("\n|\r\n|\r")[1];
		@SuppressWarnings("unchecked")
		Map<String, Object> m = JSONUtil.parseObject(msg, Map.class);
		return error(m.get("message").toString());
	}

	@ExceptionHandler({ BaseException.class })
	@ResponseStatus(HttpStatus.OK)
	public String processException(BaseException e) {
		logger.error(e.getMessage(), e);
		return error(e.getMessage());
	}

	@ExceptionHandler({ Exception.class })
	@ResponseStatus(HttpStatus.OK)
	public String processException(Exception e) {
		logger.error(e.getMessage(), e);
		return error("系统异常");
	}
}
