package com.roncoo.education.common.core.enums;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 防录屏模式
 *
 * @author LYQ
 */
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum MarqueeTypeEnum {

    /**
     * 用户名(如果存在真实姓名则直接使用，否则使用用户昵称)
     */
    USERNAME(1, "用户名"),

    /**
     * 用户名+手机号后四位
     */
    USERNAME_PHONE(2, "用户名+手机号后四位"),

    /**
     * 固定值
     */
    FIXED_VALUE(3, "固定值");


    /**
     * 编码
     */
    private final Integer code;

    /**
     * 描述
     */
    private final String desc;
}
