/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.gateway.api.common;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.tools.JSONUtil;

/**
 * 出现错误时执行
 */
public class FilterError extends ZuulFilter {

	private static final Logger logger = LoggerFactory.getLogger(FilterError.class);

	@Override
	public String filterType() {
		return FilterConstants.ERROR_TYPE;
	}

	@Override
	public int filterOrder() {
		return -1;
	}

	@Override
	public boolean shouldFilter() {
		return true;
	}

	@Override
	public Object run() {
		RequestContext ctx = RequestContext.getCurrentContext();
		Throwable throwable = ctx.getThrowable();
		if (throwable != null) {
			logger.warn(ReflectionToStringBuilder.toString(throwable, ToStringStyle.MULTI_LINE_STYLE));
			
			ctx.getResponse().setCharacterEncoding("UTF-8");
			ctx.setResponseBody(JSONUtil.toJSONString(Result.error("系统异常")));
		}
		return null;
	}

}
