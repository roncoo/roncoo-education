/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum {
    // 成功
    SUCCESS(200, "成功"),

    // 网关异常
    TOKEN_PAST(301, "token过期"), TOKEN_ERROR(302, "token异常"),
    LOGIN_ERROR(303, "登录异常"), REMOTE_ERROR(304, "异地登录"),
    MENU_PAST(305, "菜单过期"), MENU_NO(306, "没此权限，请联系管理员！"),

    // 课程异常，4开头

    // 用户异常，5开头

    // 系統异常，6开头
    SYSTEM_SAVE_FAIL(601, "添加失败"), SYSTEM_UPDATE_FAIL(602, "更新失败"), SYSTEM_DELETE_FAIL(603, "删除失败"),

    // 错误
    ERROR(999, "错误");

    private Integer code;

    private String desc;

}
