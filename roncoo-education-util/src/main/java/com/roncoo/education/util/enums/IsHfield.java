/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否高亮(1高亮;0不高亮)
 * 
 * @author wuyun
 */
@Getter
@AllArgsConstructor
public enum IsHfield {

	NO(0, "不高亮"), YES(1, "高亮");

	private Integer code;

	private String desc;

}
