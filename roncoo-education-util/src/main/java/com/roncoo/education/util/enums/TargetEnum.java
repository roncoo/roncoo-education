package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 跳转方式
 */
@Getter
@AllArgsConstructor
public enum TargetEnum {

	_BLANK("_blank", "新窗口打开"), _SELF("_self", "同窗口打开");

	private String code;

	private String desc;

}
