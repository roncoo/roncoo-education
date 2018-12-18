/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author wujing
 */
@Getter
@AllArgsConstructor
public enum OrderStatusEnum {

    WAIT(1, "待支付"), SUCCESS(2, "支付成功"), FAIL(3, "支付失败"), CLOSE(4, "已关闭"), REFUND(5, "已退款"),UNBIND(6,"已解绑");

    private Integer code;

    private String desc;

}
