package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 
 * 菜单类型(1：目录 2：菜单 3：按钮)
 *
 */
@Getter
@AllArgsConstructor
public enum MenuTypeEnum {
	DIRECTORY(1, "目录"), MENU(2, "菜单"), BUTTON(3, "按钮");

	private Integer code;

	private String desc;
}
