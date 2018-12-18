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
public enum AdvTargetEnum {

	BLANK("_blank", "新窗口打开"), SELF("_self", "同窗口打开");

	private String code;

	private String desc;

}
