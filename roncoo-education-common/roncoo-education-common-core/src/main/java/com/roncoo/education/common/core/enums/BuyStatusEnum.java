/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author LHR
 */
@Getter
@AllArgsConstructor
public enum BuyStatusEnum {

    BUY(1, "购买", ""), FREE(0, "免费", "red");

    private Integer code;

    private String desc;

    private String color;
}
