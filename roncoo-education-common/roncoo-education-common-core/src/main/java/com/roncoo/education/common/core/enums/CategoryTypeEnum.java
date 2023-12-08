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
public enum CategoryTypeEnum {

    COURSE(1, "课程"), RESOURCE(2, "资源");

    private Integer code;

    private String desc;

    /**
     * 根据编码获取枚举信息
     *
     * @param code 编码
     * @return 枚举信息
     */
    public static CategoryTypeEnum byCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (CategoryTypeEnum val : CategoryTypeEnum.values()) {
            if (val.getCode().equals(code)) {
                return val;
            }
        }
        return null;
    }

}
