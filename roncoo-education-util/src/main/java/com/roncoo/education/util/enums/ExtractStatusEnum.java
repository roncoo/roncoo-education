/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author LHR
 */
@Getter
@AllArgsConstructor
public enum ExtractStatusEnum {

	APPLICATION(1, "审核中"), PAYING(2, "支付中"), CONFIRMING(3, "确认中"), SUCCESS(4, "成功"), FAIL(5, "失败");

	private Integer code;

	private String desc;
}
