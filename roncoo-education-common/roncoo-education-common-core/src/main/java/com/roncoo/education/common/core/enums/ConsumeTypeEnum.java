/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.core.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 消费类型
 *
 * @author fengyw
 */
@Getter
@AllArgsConstructor
public enum ConsumeTypeEnum {

    Consume(1, "支出"), Income(2, "收入");

    private Integer code;

    private String desc;

}
