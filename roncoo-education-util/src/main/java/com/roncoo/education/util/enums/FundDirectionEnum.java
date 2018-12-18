/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 
 * @author forest
 */
@Getter
@AllArgsConstructor
public enum FundDirectionEnum {

    ADD(1, "增加"), SUBSTRACT(2, "减少");

    private Integer code;

    private String desc;

}
