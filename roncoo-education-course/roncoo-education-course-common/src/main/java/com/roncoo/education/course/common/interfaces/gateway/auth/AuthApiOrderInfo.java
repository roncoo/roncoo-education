package com.roncoo.education.course.common.interfaces.gateway.auth;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.github.abel533.echarts.Option;
import com.roncoo.education.course.common.bean.bo.OrderInfoCloseBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthOrderInfoContinuePayBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthOrderInfoForChartsBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthOrderInfoListBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthOrderInfoViewBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthOrderPayBO;
import com.roncoo.education.course.common.bean.dto.auth.AuthOrderInfoDTO;
import com.roncoo.education.course.common.bean.dto.auth.AuthOrderInfoListDTO;
import com.roncoo.education.course.common.bean.dto.auth.AuthOrderInfoListForLecturerDTO;
import com.roncoo.education.course.common.bean.dto.auth.AuthOrderPayDTO;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 订单信息表
 *
 * @author YZJ
 */
public interface AuthApiOrderInfo {

	/**
	 * 订单列表接口
	 */
	@ApiOperation(value = "订单列表接口", notes = "根据条件分页列出订单信息")
	@RequestMapping(value = "/auth/course/api/order/info/list", method = RequestMethod.POST)
	Result<Page<AuthOrderInfoListDTO>> listForPage(@RequestBody AuthOrderInfoListBO authOrderInfoListBO);

	/**
	 * 课程下单接口
	 */
	@ApiOperation(value = "课程下单接口", notes = "用户购买课程下单接口")
	@RequestMapping(value = "/auth/course/api/order/info/pay", method = RequestMethod.POST)
	Result<AuthOrderPayDTO> pay(@RequestBody AuthOrderPayBO authOrderPayBO);

	/**
	 * 订单继续支付接口
	 */
	@ApiOperation(value = "订单继续支付接口", notes = "订单继续支付接口")
	@RequestMapping(value = "/auth/course/api/order/info/continue/pay", method = RequestMethod.POST)
	Result<AuthOrderPayDTO> continuePay(@RequestBody AuthOrderInfoContinuePayBO orderInfoContinuePayBO);

	/**
	 * 关闭订单接口
	 */
	@ApiOperation(value = "关闭订单接口", notes = "关闭订单接口")
	@RequestMapping(value = "/auth/course/api/order/info/close", method = RequestMethod.POST)
	Result<String> close(@RequestBody OrderInfoCloseBO orderInfoCloseBO);
	
	/**
	 * 订单详情信息接口
	 * 
	 * @param infoBO
	 * @return
	 */
	@ApiOperation(value = "订单详情信息接口", notes = "订单详情信息接口")
	@RequestMapping(value = "/auth/course/api/order/info/view", method = RequestMethod.POST)
	Result<AuthOrderInfoDTO> view(@RequestBody AuthOrderInfoViewBO orderInfoBO);

	/**
	 * 讲师订单收益列表
	 *
	 * @param infoBO
	 * @return
	 */
	@ApiOperation(value = "讲师订单收益列表接口", notes = "讲师订单收益列表接口")
	@RequestMapping(value = "/auth/course/api/order/info/lecturer", method = RequestMethod.POST)
	Result<Page<AuthOrderInfoListForLecturerDTO>> list(@RequestBody AuthOrderInfoListBO authOrderInfoListBO);
	
	/**
	 * 讲师收益折线图
	 * @param bo
	 * @return
	 */
	@ApiOperation(value = "讲师收益折线图", notes = "讲师收益折线图")
	@RequestMapping(value = "/auth/course/api/order/info/charts", method = RequestMethod.POST)
	Result<Option> charts(@RequestBody AuthOrderInfoForChartsBO authOrderInfoForChartsBO);
}
