package com.roncoo.education.common.pay.util;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 微信支付
 *
 * @author LYQ
 */
@Data
@Accessors(chain = true)
public class WxPayConfig {

    /**
     * 应用ID（服务商模式时：为服务商配置）
     */
    private String wxPayAppId;

    /**
     * 直连商户号（服务商模式时：为服务商配置）
     */
    private String wxPayMchId;

    /**
     * 商户证书（服务商模式时：为服务商配置）
     *
     * @see <a href="https://kf.qq.com/faq/161222NneAJf161222U7fARv.html">如何获取商户API证书</a>
     */
    private String wxPayMchPrivateCert;

    /**
     * 商户私钥（服务商模式时：为服务商配置）
     *
     * @see <a href="https://kf.qq.com/faq/161222NneAJf161222U7fARv.html">如何获取商户API证书</a>
     */
    private String wxPayWxMchPrivateKey;

    /**
     * API v3密钥（服务商模式时：为服务商配置）
     */
    private String wxPayApiV3Key;

    /**
     * 子商户应用ID（服务商模式时：必填）
     */
    private String subAppId;

    /**
     * 子商户号（服务商模式时：必填）
     */
    private String subMchId;
}
