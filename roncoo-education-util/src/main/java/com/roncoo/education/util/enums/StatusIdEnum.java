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
public enum StatusIdEnum {

	NO(0, "禁用", "red"), YES(1, "正常", "");

	private Integer code;

	private String desc;

	private String color;

}
