/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 是否置顶
 * 
 */
@Getter
@AllArgsConstructor
public enum IsTopEnum {

	YES(1, "是"), NO(0, "否");

	private Integer code;

	private String desc;

}
