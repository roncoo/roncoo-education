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
public enum LoginStatusEnum {

	SUCCESS(1, "登录成功", ""),
	//
	FAIL(0, "登录失败", "red");

	private Integer code;

	private String desc;

	private String color;
}
