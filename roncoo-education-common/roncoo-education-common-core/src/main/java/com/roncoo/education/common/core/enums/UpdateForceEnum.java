/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author fengyw
 */
@Getter
@AllArgsConstructor
public enum UpdateForceEnum {

    TIP(1, "提醒升级"), FORCE(2, "强制升级");

    private Integer code;

    private String desc;
}
