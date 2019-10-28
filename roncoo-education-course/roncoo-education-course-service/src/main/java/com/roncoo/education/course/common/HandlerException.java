package com.roncoo.education.course.common;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.netflix.hystrix.exception.HystrixRuntimeException;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.BaseException;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.tools.JSONUtil;

import feign.FeignException;

/**
 *
 * @author wujing
 */
@RestControllerAdvice(basePackages = "com.roncoo.education.course.service.feign.gateway")
public class HandlerException extends BaseController {

	@ExceptionHandler({ FeignException.class })
	@ResponseStatus(HttpStatus.OK)
	public Result<String> processBizException(FeignException e) {
		String msg = e.getCause().toString().split("\n|\r\n|\r")[1];
		logger.error(msg);
		@SuppressWarnings("unchecked")
		Map<String, Object> m = JSONUtil.parseObject(msg, Map.class);
		if (null != m.get("message")) {
			return Result.error(m.get("message").toString());
		}
		return Result.error("系统错误");
	}

	@ExceptionHandler({ HystrixRuntimeException.class })
	@ResponseStatus(HttpStatus.OK)
	public Result<String> processException(HystrixRuntimeException e) {
		String msg = e.getCause().toString().split("\n|\r\n|\r")[1];
		logger.error(msg);
		@SuppressWarnings("unchecked")
		Map<String, Object> m = JSONUtil.parseObject(msg, Map.class);
		if (null != m.get("message")) {
			return Result.error(m.get("message").toString());
		}
		return Result.error("系统错误");
	}

	@ExceptionHandler({ BaseException.class })
	@ResponseStatus(HttpStatus.OK)
	public Result<String> processException(BaseException e) {
		logger.error("BaseException", e);
		return Result.error(e.getMessage());
	}

	@ExceptionHandler({ Exception.class })
	@ResponseStatus(HttpStatus.OK)
	public Result<String> processException(Exception e) {
		logger.error("Exception", e);
		return Result.error("系统错误");
	}

}
