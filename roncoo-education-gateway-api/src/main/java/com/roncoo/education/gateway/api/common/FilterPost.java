/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.gateway.api.common;

import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.tools.JSONUtil;

/**
 * 请求处理完成后执行
 */
public class FilterPost extends ZuulFilter {

	@Override
	public String filterType() {
		return FilterConstants.POST_TYPE;
	}

	@Override
	public int filterOrder() {
		return FilterConstants.SEND_RESPONSE_FILTER_ORDER - 1;
	}

	@Override
	public boolean shouldFilter() {
		RequestContext ctx = RequestContext.getCurrentContext();
		// 异常处理
		if (ctx.getResponseStatusCode() != 200) {
			ctx.getResponse().setCharacterEncoding("UTF-8");
			ctx.setResponseBody(JSONUtil.toJSONString(Result.error(99, "服务异常，请重试")));
		}
		return false;
	}

	@Override
	public Object run() {
		return null;
	}

}
