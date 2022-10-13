package com.roncoo.education.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 菜单类型(1目录 2菜单 3权限)
 *
 * @author fengyw
 */
@Getter
@AllArgsConstructor
public enum MenuTypeEnum {
    DIRECTORY(1, "目录"), MENU(2, "菜单"), PERMISSION(3, "权限");

    private Integer code;

    private String desc;
}
