package com.roncoo.education.common.pay.req;

import com.roncoo.education.common.pay.util.AliPayConfig;
import com.roncoo.education.common.pay.util.WxPayConfig;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 交易下单参数
 *
 * @author LYQ
 */
@Data
@Accessors(chain = true)
public class TradeOrderReq implements Serializable {

    private static final long serialVersionUID = -6015260277215043534L;


    /**
     * 交易流水号（用于上送支付渠道--对应平台交易记录ID）
     */
    private String tradeSerialNo;

    /**
     * 交易金额（单位：元）
     */
    private BigDecimal amount;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 附加信息
     */
    private String attach;

    /**
     * 用户终端IP
     */
    private String userClientIp;

    /**
     * 用户标识（直连模式-jsapi支付必填）
     */
    private String openId;

    /**
     * 用户服务标识（服务商模式-jsapi支付，spOpenId和subOpenId二选一）
     * <p>
     * 用户在服务商appid下的唯一标识。 下单前需获取到用户的Openid
     */
    private String spOpenId;

    /**
     * 用户子标识（服务商模式-jsapi支付，spOpenId和subOpenId二选一）
     * <p>
     * 用户在子商户appid下的唯一标识。若传sub_openid，那sub_appid必填。下单前需获取到用户的Openid
     */
    private String subOpenId;

    /**
     * 订单绝对超时时间。
     */
    private Date timeExpire;

    /**
     * 异步通知地址
     */
    private String notifyUrl;

    /**
     * 用户付款中途退出返回商户网站的地址
     */
    private String quitUrl;

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
}
