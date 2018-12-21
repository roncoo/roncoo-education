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
public enum SysTypeEnum {

	RONCOOPAY(1, "龙果支付"), OTHERPAYMENT(2, "其他");

	private Integer code;

	private String desc;

}
