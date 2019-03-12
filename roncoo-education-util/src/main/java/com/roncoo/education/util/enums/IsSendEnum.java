/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否是最新
 * 
 * @author Administrator
 *
 */
@Getter
@AllArgsConstructor
public enum IsSendEnum {

	YES(1, "已发送"), NO(0, "待发送");

	private Integer code;

	private String desc;

}
