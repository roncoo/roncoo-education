package com.roncoo.education.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 跳转方式
 */
@Getter
@AllArgsConstructor
public enum TargetEnum {

    BLANK(1, "新窗口打开"), SELF(2, "同窗口打开");

    private Integer code;

    private String desc;

}
