package com.roncoo.education.common.pay.resp;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 交易下单返回
 *
 * @author LYQ
 */
@Data
@Accessors(chain = true)
public class TradeOrderResp implements Serializable {

    private static final long serialVersionUID = 3227148205585939454L;

    /**
     * 是否请求成功
     */
    private boolean success;

    /**
     * 请求响应信息
     */
    private String msg;

    /**
     * 支付请求信息，扫码支付返回支付链接
     */
    private String payMessage;
}
