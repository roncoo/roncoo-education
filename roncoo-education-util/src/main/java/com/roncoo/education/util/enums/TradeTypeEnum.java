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
public enum TradeTypeEnum {

	ONLINE(1, "线上支付"), OFFLINE(2, "线下支付");

	private Integer code;

	private String desc;

}
