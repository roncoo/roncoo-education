/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wujing
 */
@Getter
@AllArgsConstructor
public enum PayTypeEnum {

	WEIXIN(1, "微信支付"), ALIPAY(2, "支付宝支付"), INTEGRAL(3, "积分支付"), MANUAL(4, "手工录单");

	private Integer code;

	private String desc;

}
