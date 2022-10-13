package com.roncoo.education.common.pay.util;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 支付宝支付配置
 *
 * @author LYQ
 */
@Data
@Accessors(chain = true)
public class AliPayConfig implements Serializable {

    private static final long serialVersionUID = 5134756139632063346L;

    /**
     * 应用ID（服务商模式时：为服务商配置）
     */
    private String aliPayAppId;

    /**
     * 应用私钥（服务商模式时：为服务商配置）
     */
    private String aliPayAppPrivateKey;

    /**
     * 支付宝公钥（服务商模式时：为服务商配置）
     */
    private String aliPayPublicKey;

    /**
     * 签名类型；目前支持RSA2和RSA，推荐使用RSA2（服务商模式时：为服务商配置）
     */
    private String signType = "RSA2";

    /**
     * 子商户-卖家支付宝用户ID（服务商模式时：必填）
     */
    private String subSellerId;
}
