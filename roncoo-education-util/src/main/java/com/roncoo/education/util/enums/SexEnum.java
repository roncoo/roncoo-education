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
public enum SexEnum {

	MALE(1, "男", "green"), FEMALE(2, "女", "red"), SECRET(3, "保密", "orange");

	private Integer code;

	private String desc;

	private String color;
}
