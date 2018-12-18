/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否启用水印
 * 
 * @author wuyun
 */
@Getter
@AllArgsConstructor
public enum IsWatermarkEnum {

	ENABLE(1, "启用", ""), CLOSED(0, "关闭", "red");

	private Integer code;

	private String desc;

	private String color;
}
