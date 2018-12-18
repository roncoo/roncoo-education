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
public enum ZoneLocationEnum {

	PC(0, "PC端", ""),
	//
	WX(1, "微信端", "green");

	private Integer code;

	private String desc;

	private String color;
}
