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
public enum PayTypeEnum {

    WEIXIN_SCAN(11, "微信扫码支付", "wxScanPay"),
    WEIXIN_H5(12, "微信H5支付", "wxH5Pay"),
    WEIXIN_APP(13, "微信APP支付", "wxAppPay"),
    WEIXIN_MP(14, "微信公众号支付", "wxMpPay"),
    WEIXIN_MA(15, "微信小程序支付", "wxMaPay"),
    ALIPAY_SCAN(21, "支付宝扫码支付", "aliScanPay"),
    ALIPAY_H5(22, "支付宝H5支付", "aliH5Pay"),
    ALIPAY_APP(23, "支付宝App支付", "aliAppPay"),
    BALANCE_PAY(100, "余额支付", "balancePay");

    private Integer code;

    private String desc;

    /**
     * 实现
     */
    private String impl;

    /**
     * 根据编码获取枚举信息
     *
     * @param code 编码
     * @return 枚举信息
     */
    public static PayTypeEnum byCode(Integer code) {
        if (code == null) {
            return null;
        }
        for (PayTypeEnum val : PayTypeEnum.values()) {
            if (val.getCode().equals(code)) {
                return val;
            }
        }
        return null;
    }

}
