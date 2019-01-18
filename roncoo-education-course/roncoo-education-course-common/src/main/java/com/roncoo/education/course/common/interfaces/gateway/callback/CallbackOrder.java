/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.course.common.interfaces.gateway.callback;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.course.service.common.bo.callback.CallbackOrderBO;

import io.swagger.annotations.ApiOperation;

/**
 * 回调接口
 *
 * @author wujing
 */
public interface CallbackOrder {

	/**
	 * 龙果支付回调
	 * 
	 * @param CallbackOrderBO
	 * @return
	 */
	@ApiOperation(value = "龙果支付回调接口", notes = "龙果支付回调接口")
	@RequestMapping(value = "/callback/order/roncoo", method = { RequestMethod.POST, RequestMethod.GET })
	String roncooPayNotify(@ModelAttribute CallbackOrderBO callbackOrderBO);

}
