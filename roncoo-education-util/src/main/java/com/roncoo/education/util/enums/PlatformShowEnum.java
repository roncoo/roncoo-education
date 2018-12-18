/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 平台展示枚举类
 * 
 * @author Administrator
 *
 */
@Getter
@AllArgsConstructor
public enum PlatformShowEnum {

	PC(1, "PC端", ""), EDIT(0, "微信端","green");

	private Integer code;

	private String desc;
	
	private String color;

}
