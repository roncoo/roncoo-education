/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wujing
 */
@Getter
@AllArgsConstructor
public enum ConfigTypeEnum {

    SYSTEM(1, "系统信息"), PAY(6, "支付信息");

    private Integer code;

    private String desc;

    /**
     * 根据编码获取枚举信息
     *
     * @param code 编码
     * @return 枚举信息
     */
    public static ConfigTypeEnum byCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (ConfigTypeEnum val : ConfigTypeEnum.values()) {
            if (val.getCode().equals(code)) {
                return val;
            }
        }
        return null;
    }

}
