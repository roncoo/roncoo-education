package com.roncoo.education.util.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TradeStatusEnum {
    SUCCESS("SUCCESS", "交易成功", ""), //

    FINISH("FINISH", "交易完成", "green"), //

    FAILED("FAILED", "交易失败", "red"), //

    WAITING_PAYMENT("WAITING_PAYMENT", "等待支付", "orange");

    private String code;

    private String desc;
    
    private String color;

}
