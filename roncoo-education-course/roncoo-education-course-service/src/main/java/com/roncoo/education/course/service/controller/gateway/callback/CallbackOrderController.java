/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.course.service.controller.gateway.callback;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.course.common.bean.bo.OrderInfoPayNotifyBO;
import com.roncoo.education.course.common.bean.bo.callback.CallbackOrderBO;
import com.roncoo.education.course.common.interfaces.gateway.callback.CallbackOrder;
import com.roncoo.education.course.service.biz.gateway.callback.CallbackOrderBiz;
import com.roncoo.education.system.common.bean.vo.SysVO;
import com.roncoo.education.system.feign.web.IBossSys;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.enums.OrderStatusEnum;
import com.roncoo.education.util.enums.TradeStatusEnum;
import com.roncoo.education.util.pay.MerchantApiUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 订单信息表
 *
 * @author wujing123
 */
@RestController
public class CallbackOrderController extends BaseController implements CallbackOrder {

	@Autowired
	private CallbackOrderBiz biz;

	@Autowired
	private IBossSys bossSys;

	@Override
	public String roncooPayNotify(@ModelAttribute CallbackOrderBO bo) {
		logger.info("龙果支付回调结果：{}", bo);
		// sign校验
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("payKey", bo.getPayKey());
		paramMap.put("productName", bo.getProductName());
		paramMap.put("outTradeNo", bo.getOutTradeNo());
		paramMap.put("orderPrice", bo.getOrderPrice());
		paramMap.put("productType", bo.getProductType());
		paramMap.put("tradeStatus", bo.getTradeStatus());
		paramMap.put("successTime", bo.getSuccessTime());// 商品名称
		paramMap.put("orderTime", bo.getOrderTime());
		paramMap.put("trxNo", bo.getTrxNo());
		paramMap.put("remark", bo.getRemark());

		SysVO sys = bossSys.getSys();
		if (ObjectUtil.isNull(sys)) {
			return "找不到系统配置信息";
		}
		if (StringUtils.isEmpty(sys.getPaySecret())) {
			return "paySecret未配置";
		}

		// 注意：这里返回的备注信息=机构号
		// 根据机构编号查找机构的扩展信息
		String sign = MerchantApiUtil.getSign(paramMap, sys.getPaySecret());
		if (!sign.equals(bo.getSign())) {
			logger.error("签名前参数={}，签名sign={}，签名secret={}", paramMap, sign, sys.getPaySecret());
			return "fail";
		}

		// 转换支付回调返回的实体数据
		OrderInfoPayNotifyBO payNotifyBO = new OrderInfoPayNotifyBO();
		// 注意：这里返回的交易号=订单的流水号
		payNotifyBO.setSerialNumber(Long.valueOf(bo.getOutTradeNo()));
		// 状态转换
		if (TradeStatusEnum.SUCCESS.getCode().equals(bo.getTradeStatus())) {
			payNotifyBO.setOrderStatus(OrderStatusEnum.SUCCESS.getCode());
		} else if (TradeStatusEnum.FAILED.getCode().equals(bo.getTradeStatus())) {
			payNotifyBO.setOrderStatus(OrderStatusEnum.FAIL.getCode());
		}

		return biz.payNotify(payNotifyBO).getData();
	}
}
