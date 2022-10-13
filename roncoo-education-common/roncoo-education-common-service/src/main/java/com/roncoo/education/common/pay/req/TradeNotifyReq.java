package com.roncoo.education.common.pay.req;

import com.roncoo.education.common.pay.util.AliPayConfig;
import com.roncoo.education.common.pay.util.WxPayConfig;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Map;

/**
 * 交易下单通知
 *
 * @author LYQ
 */
@Data
@Accessors(chain = true)
public class TradeNotifyReq implements Serializable {

    private static final long serialVersionUID = 7068895000045138326L;
    /**
     * 请求头参数
     */
    private Map<String, String> headerMap;

    /**
     * query参数
     */
    private Map<String, String> queryParamMap;

    /**
     * body参数
     */
    private String bodyParam;

    /**
     * 支付模式
     */
    private Integer payModel;

    /**
     * 支付宝支付配置
     */
    private AliPayConfig aliPayConfig;

    /**
     * 微信支付配置
     */
    private WxPayConfig wxPayConfig;

    /**
     * 微信平台证书
     */
    private String wxCert;
}
