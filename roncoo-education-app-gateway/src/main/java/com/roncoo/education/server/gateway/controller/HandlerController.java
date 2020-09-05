package com.roncoo.education.server.gateway.controller;

import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.ResultEnum;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@RestController
public class HandlerController implements ErrorController {

	/**
	 * 出异常后进入该方法，交由下面的方法处理
	 */
	@Override
	public String getErrorPath() {
		return "/error";
	}

	@RequestMapping("/error")
	@ResponseStatus(HttpStatus.OK)
	public Result<String> error() {
		RequestContext ctx = RequestContext.getCurrentContext();
		Throwable throwable = ctx.getThrowable();
		if(null != throwable && throwable instanceof  ZuulException ){
			ZuulException e = (ZuulException) ctx.getThrowable();
			return Result.error(e.nStatusCode, e.errorCause);
		}
		return Result.error(ResultEnum.ERROR);
	}

}
