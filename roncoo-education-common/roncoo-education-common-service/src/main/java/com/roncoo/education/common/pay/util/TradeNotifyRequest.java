package com.roncoo.education.common.pay.util;

import cn.hutool.core.annotation.Alias;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * 交易通知参数
 *
 * @author LYQ
 * <p>
 * 签名工具：AlipaySignature
 * @see com.alipay.api.internal.util.AlipaySignature#verifyV1(Map, String, String, String)
 * @see <a href="https://opendocs.alipay.com/open/194/103296#%E5%BC%82%E6%AD%A5%E8%BF%94%E5%9B%9E%E7%BB%93%E6%9E%9C%E9%AA%8C%E7%AD%BE">异步通知参数说明</a>
 */
@Data
public class TradeNotifyRequest implements Serializable {

    private static final long serialVersionUID = -2237538770818980501L;

    /**
     * 版本
     */
    private String version;

    /**
     * 编码字符
     */
    private String charset;

    /**
     * 第三方应用APPID
     */
    @Alias(value = "auth_app_id")
    private String authAppId;

    /**
     * 必填--通知时间。通知的发送时间。格式为 yyyy-MM-dd HH:mm:ss。
     */
    @Alias(value = "notify_time")
    private Date notifyTime;

    /**
     * 必填--通知类型。
     */
    @Alias(value = "notify_type")
    private String notifyType;

    /**
     * 必填--通知校验 ID。
     */
    @Alias(value = "notify_id")
    private String notifyId;

    /**
     * 必填--签名类型。商户生成签名字符串所使用的签名算法类型，目前支持 RSA2 和 RSA，推荐使用 RSA2（如果开发者手动验签，不使用 SDK 验签，可以不传此参数）
     */
    @Alias(value = "sign_type")
    private String signType;

    /**
     * 必填--签名。请参考异步返回结果的验签（如果开发者手动验签，不使用 SDK 验签，可以不传此参数）。
     */
    private String sign;

    /**
     * 必填--支付宝交易号。支付宝交易凭证号。
     */
    @Alias(value = "trade_no")
    private String tradeNo;

    /**
     * 必填--开发者的 app_id。支付宝分配给开发者的应用 APPID。
     */
    @Alias(value = "app_id")
    private String appId;

    /**
     * 必填--商户订单号。
     */
    @Alias(value = "out_trade_no")
    private String outTradeNo;

    /**
     * 可选--商户业务号。商户业务 ID，主要是退款通知中返回退款申请的流水号。
     */
    @Alias(value = "out_biz_no")
    private String outBizNo;

    /**
     * 可选--买家支付宝用户号。买家支付宝账户对应的支付宝唯一用户号。
     */
    @Alias(value = "buyer_id")
    private String buyerId;

    /**
     * 可选--买家支付宝账号。
     */
    @Alias(value = "buyer_logon_id")
    private String buyerLogonId;

    /**
     * 可选--卖家支付宝用户号。
     */
    @Alias(value = "seller_id")
    private String sellerId;

    /**
     * 可选--卖家支付宝账号。
     */
    @Alias(value = "seller_email")
    private String sellerEmail;

    /**
     * 必填--交易状态。咨询目前所处的状态。
     */
    @Alias(value = "trade_status")
    private String tradeStatus;

    /**
     * 必填--订单金额。本次交易支付的订单金额，单位为人民币（元）。支持小数点后两位。
     */
    @Alias(value = "total_amount")
    private BigDecimal totalAmount;

    /**
     * 必填--实收金额。商户在交易中实际收到的款项，单位为人民币（元）。支持小数点后两位。
     */
    @Alias(value = "receipt_amount")
    private BigDecimal receiptAmount;

    /**
     * 必填--开票金额。用户在交易中支付的可开发票的金额。支持小数点后两位。
     */
    @Alias(value = "invoice_amount")
    private BigDecimal invoiceAmount;

    /**
     * 可选--付款金额。用户在咨询中支付的金额。支持小数点后两位。
     */
    @Alias(value = "buyer_pay_amount")
    private BigDecimal buyerPayAmount;

    /**
     * 可选--集分宝金额。使用集分宝支付的金额。支持小数点后两位。
     */
    @Alias(value = "point_amount")
    private BigDecimal pointAmount;

    /**
     * 可选--总退款金额。退款通知中，返回总退款金额，单位为元，支持两位小数。支持小数点后两位。
     */
    @Alias(value = "refund_fee")
    private BigDecimal refundFee;

    /**
     * 可选--实际退款金额。商户实际退款给用户的金额，单位为元，支持两位小数。支持小数点后两位。
     */
    @Alias(value = "send_back_fee")
    private BigDecimal sendBackFee;

    /**
     * 可选--订单标题。商品的标题/交易标题/订单标题/订单关键字等，是请求时对应的参数，原样通知回来。
     */
    private String subject;

    /**
     * 可选--商品描述。该订单的备注、描述、明细等。对应请求时的 body 参数，原样通知回来。
     */
    private String body;

    /**
     * 可选--交易创建时间。该笔交易创建的时间。格式 为 yyyy-MM-dd HH:mm:ss。
     */
    @Alias(value = "gmt_create")
    private Date gmtCreate;

    /**
     * 可选--交易 付款时间。该笔交易的买家付款时间。格式为 yyyy-MM-dd HH:mm:ss。
     */
    @Alias(value = "gmt_payment")
    private Date gmtPayment;

    /**
     * 可选--交易退款时间。该笔交易的退款时间。格式 为 yyyy-MM-dd HH:mm:ss.S。
     */
    @Alias(value = "gmt_refund")
    private Date gmtRefund;

    /**
     * 可选--交易结束时间。该笔交易结束时间。格式为 yyyy-MM-dd HH:mm:ss。
     */
    @Alias(value = "gmt_close")
    private Date gmtClose;

    /**
     * 可选--支付金额信息。支付成功的各个渠道金额信息，详见下表 资金明细信息说明。
     */
    @Alias(value = "fund_bill_list")
    private String fundBillList;

    /**
     * 可选--优惠券信息。本交易支付时所使用的所有优惠券信息，详见下表 优惠券信息说明
     */
    @Alias(value = "voucher_detail_list")
    private String voucherDetailList;
}
