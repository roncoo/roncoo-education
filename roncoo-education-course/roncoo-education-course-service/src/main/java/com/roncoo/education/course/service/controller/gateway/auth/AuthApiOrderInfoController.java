package com.roncoo.education.course.service.controller.gateway.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
import com.roncoo.education.course.common.interfaces.gateway.auth.AuthApiOrderInfo;
import com.roncoo.education.course.service.biz.gateway.auth.AuthApiOrderInfoBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;

/**
 * 订单信息表
 *
 * @author YZJ
 */
@RestController
public class AuthApiOrderInfoController extends BaseController implements AuthApiOrderInfo {

	@Autowired
	private AuthApiOrderInfoBiz biz;

	/**
	 * 订单列表接口
	 */
	@Override
	public Result<Page<AuthOrderInfoListDTO>> listForPage(@RequestBody AuthOrderInfoListBO authOrderInfoListBO) {
		return biz.listForPage(authOrderInfoListBO);
	}

	/**
	 * 下单接口
	 */
	@Override
	public Result<AuthOrderPayDTO> pay(@RequestBody AuthOrderPayBO authOrderPayBO) {
		return biz.pay(authOrderPayBO);
	}

	/**
	 * 订单继续支付接口
	 */
	@Override
	public Result<AuthOrderPayDTO> continuePay(@RequestBody AuthOrderInfoContinuePayBO orderInfoContinuePayBO) {
		return biz.continuePay(orderInfoContinuePayBO);
	}

	/**
	 * 关闭订单信息接口
	 */
	@Override
	public Result<String> close(@RequestBody OrderInfoCloseBO orderInfoCloseBO) {
		return biz.close(orderInfoCloseBO);
	}

	/**
	 * 订单详情接口
	 */
	@Override
	public Result<AuthOrderInfoDTO> view(@RequestBody AuthOrderInfoViewBO orderInfoBO) {
		return biz.view(orderInfoBO);
	}

	/**
	 * 查找订单信息列表信息
	 */
	@Override
	public Result<Page<AuthOrderInfoListForLecturerDTO>> list(@RequestBody AuthOrderInfoListBO authOrderInfoListBO) {
		return biz.list(authOrderInfoListBO);
	}

	/**
	 * 讲师收益折线图
	 */
	@Override
	public Result<Option> charts(@RequestBody AuthOrderInfoForChartsBO authOrderInfoForChartsBO) {
		return biz.charts(authOrderInfoForChartsBO);
	}

}
