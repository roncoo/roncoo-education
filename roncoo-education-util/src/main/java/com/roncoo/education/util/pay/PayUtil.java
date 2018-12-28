/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.pay;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.roncoo.education.util.enums.DateFormatEnum;
import com.roncoo.education.util.enums.PayProductTypeEnum;
import com.roncoo.education.util.enums.PayTypeEnum;
import com.roncoo.education.util.tools.HttpUtil;
import com.roncoo.education.util.tools.JSONUtil;
import com.roncoo.education.util.tools.MD5Util;

public final class PayUtil {

	private PayUtil() {
	}

	private static final Logger logger = LoggerFactory.getLogger(PayUtil.class);

	/**
	 * roncoo支付
	 */
	public static String roncooPay(String serialNumber, String productName, BigDecimal productPrice, Integer payType, String payKey, String paySecret, String payUrl, String notifyUrl) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("payKey", payKey);
		paramMap.put("orderPrice", productPrice);
		paramMap.put("outTradeNo", serialNumber);

		// 判断是什么支付方式-支付方式编码 支付宝: ALIPAY 微信:WEIXIN
		if (payType.equals(PayTypeEnum.WEIXIN.getCode())) {
			paramMap.put("productType", PayProductTypeEnum.WEIXIN_T1.getCode());
		} else if (payType.equals(PayTypeEnum.ALIPAY.getCode())) {
			paramMap.put("productType", PayProductTypeEnum.ZHIFUBAO_T1.getCode());
		} else {
			// 匹配不上，设置为支付宝支付
			paramMap.put("productType", PayProductTypeEnum.ZHIFUBAO_T1.getCode());
		}

		paramMap.put("orderTime", new SimpleDateFormat(DateFormatEnum.YYYYMMDDHHMMSS.getDateFormat()).format(new Date()));

		// 把商品名中的特殊字符过滤掉
		String regEx = "[`~!@#$%^&*()+=|{}':;',_\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
		productName = Pattern.compile(regEx).matcher(productName).replaceAll("").trim();
		if (productName.length() > 15) {
			paramMap.put("productName", productName.substring(0, 15));
		} else {
			paramMap.put("productName", productName);
		}

		paramMap.put("orderIp", "127.0.0.1");// 下单IP

		paramMap.put("returnUrl", notifyUrl); // 页面通知返回url
		paramMap.put("notifyUrl", notifyUrl);
		paramMap.put("remark", serialNumber);
		String sign = getSign(paramMap, paySecret);
		paramMap.put("sign", sign);
		String resp = HttpUtil.postForPay(payUrl, paramMap);
		logger.debug("roncoo支付返回报文={}", resp);
		try {
			@SuppressWarnings("unchecked")
			Map<String, String> res = JSONUtil.parseObject(resp, HashMap.class);
			if (res.get("resultCode").equals("0000")) {
				return res.get("payMessage");
			}
		} catch (Exception e) {
			logger.error("roncoo支付接口请求失败，resp={}", resp);
		}
		return "";
	}

	private static String getSign(Map<String, Object> paramMap, String paySecret) {
		if (paramMap.isEmpty()) {
			return "";
		}
		SortedMap<String, Object> smap = new TreeMap<String, Object>(paramMap);
		StringBuffer stringBuffer = new StringBuffer();
		for (Map.Entry<String, Object> m : smap.entrySet()) {
			String value = String.valueOf(m.getValue());
			if (StringUtils.hasText(value)) {
				stringBuffer.append(m.getKey()).append("=").append(value).append("&");
			}
		}
		stringBuffer.delete(stringBuffer.length() - 1, stringBuffer.length());
		String argPreSign = stringBuffer.append("&paySecret=").append(paySecret).toString();
		return MD5Util.MD5(argPreSign).toUpperCase();
	}
}
