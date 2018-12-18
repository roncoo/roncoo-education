/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 业务类型
 * @author forest
 */
@Getter
@AllArgsConstructor
public enum TrxTypeEnum {

    BUY_COURSE(1, "购买课程");

	private Integer code;

	private String desc;

}
