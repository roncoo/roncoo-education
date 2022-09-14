package com.roncoo.education.common.pay.biz;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.github.binarywang.wxpay.bean.ecommerce.PartnerTransactionsCloseRequest;
import com.github.binarywang.wxpay.bean.ecommerce.PartnerTransactionsQueryRequest;
import com.github.binarywang.wxpay.bean.ecommerce.PartnerTransactionsResult;
import com.github.binarywang.wxpay.bean.request.WxPayRefundV3Request;
import com.github.binarywang.wxpay.bean.result.WxPayOrderQueryV3Result;
import com.github.binarywang.wxpay.bean.result.WxPayRefundQueryV3Result;
import com.github.binarywang.wxpay.bean.result.WxPayRefundV3Result;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.roncoo.education.common.core.tools.JSUtil;
import com.roncoo.education.common.pay.req.*;
import com.roncoo.education.common.pay.resp.RefundQueryResp;
import com.roncoo.education.common.pay.resp.RefundResp;
import com.roncoo.education.common.pay.resp.TradeNotifyResp;
import com.roncoo.education.common.pay.resp.TradeQueryResp;
import com.roncoo.education.common.pay.util.*;
import com.wechat.pay.contrib.apache.httpclient.auth.CertificatesVerifier;
import com.wechat.pay.contrib.apache.httpclient.exception.ParseException;
import com.wechat.pay.contrib.apache.httpclient.exception.ValidationException;
import com.wechat.pay.contrib.apache.httpclient.notification.Notification;
import com.wechat.pay.contrib.apache.httpclient.notification.NotificationHandler;
import com.wechat.pay.contrib.apache.httpclient.notification.NotificationRequest;
import com.wechat.pay.contrib.apache.httpclient.util.PemUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.security.cert.X509Certificate;
import java.util.Collections;
import java.util.Map;

/**
 * 微信支付公共业务
 *
 * @author LYQ
 */
@Slf4j
@Component
public class WxPayCommonBiz {

    private static final String WECHAT_PAY_NONCE = "wechatpay-nonce";
    private static final String WECHAT_PAY_SIGNATURE = "wechatpay-signature";
    private static final String WECHAT_PAY_SERIAL = "wechatpay-serial";
    private static final String WECHAT_PAY_TIMESTAMP = "wechatpay-timestamp";

    /**
     * 交易查询
     *
     * @param req 交易查询参数
     * @return 响应结果
     */
    public TradeQueryResp tradeQuery(TradeQueryReq req) {
        if (PayModelEnum.SERVICE_PROVIDER.getCode().equals(req.getPayModel())) {
            return serviceProviderTradeQuery(req);
        } else {
            return directTradeQuery(req);
        }
    }

    /**
     * 服务商交易查询
     *
     * @param req 查询参数
     * @return 交易支付结果
     */
    private TradeQueryResp serviceProviderTradeQuery(TradeQueryReq req) {
        // 服务商
        log.info("微信服务商--交易查询，请求参数：{}", JSUtil.toJsonString(req));
        WxPayConfig wxPayConfig = req.getWxPayConfig();

        // step1：创建返回对象
        TradeQueryResp resp = new TradeQueryResp();
        resp.setOrderNo(req.getOrderNo());
        resp.setTradeStatus(TradeStatusEnum.WAIT_PAY.getCode());
        resp.setSuccess(false);

        // step2：处理请求参数
        PartnerTransactionsQueryRequest request = new PartnerTransactionsQueryRequest();
        request.setOutTradeNo(req.getOrderNo());
        request.setSpMchid(wxPayConfig.getWxPayMchId());
        request.setSubMchid(wxPayConfig.getSubMchId());

        // step2：发起查询请求
        WxPayService wxPayService = WxPayUtil.initService(wxPayConfig);
        try {
            PartnerTransactionsResult result = wxPayService.getEcommerceService().queryPartnerTransactions(request);
            // step4：处理响应信息
            if (ObjectUtil.isNull(result)) {
                resp.setMsg("支付订单查询失败，响应结果为空！");
                return resp;
            }

            // 订单查询成功
            resp.setSuccess(true);
            resp.setTradeNo(result.getTransactionId());
            resp.setAmount(new BigDecimal(result.getAmount().getTotal()).divide(BigDecimal.valueOf(100L), 2, RoundingMode.HALF_UP));
            resp.setSuccessTime(WxPayUtil.dateParse(result.getSuccessTime()));

            if (TradeStateEnum.SUCCESS.getCode().equals(result.getTradeState()) || TradeStateEnum.REFUND.getCode().equals(result.getTradeState())) {
                resp.setTradeStatus(TradeStatusEnum.SUCCESS.getCode());
            } else if (TradeStateEnum.PAY_ERROR.getCode().equals(result.getTradeState()) || TradeStateEnum.CLOSED.getCode().equals(result.getTradeState())) {
                resp.setTradeStatus(TradeStatusEnum.FAILURE.getCode());
            } else {
                resp.setTradeStatus(TradeStatusEnum.WAIT_PAY.getCode());
            }
        } catch (WxPayException e) {
            log.error("微信服务商--交易查询，请求异常！", e);
        }
        return resp;
    }

    /**
     * 直营商户交易查询
     *
     * @param req 查询参数
     * @return 交易支付结果
     */
    private TradeQueryResp directTradeQuery(TradeQueryReq req) {
        // 直营商户
        log.info("微信直营商户--交易查询，请求参数：{}", JSUtil.toJsonString(req));

        // step1：创建返回对象
        TradeQueryResp resp = new TradeQueryResp();
        resp.setOrderNo(req.getOrderNo());
        resp.setTradeStatus(TradeStatusEnum.WAIT_PAY.getCode());
        resp.setSuccess(false);

        // step2：发起查询请求
        WxPayService wxPayService = WxPayUtil.initService(req.getWxPayConfig());
        try {
            WxPayOrderQueryV3Result result = wxPayService.queryOrderV3(null, req.getOrderNo());
            // step4：处理响应信息
            if (ObjectUtil.isNull(result)) {
                resp.setMsg("支付订单查询失败，响应结果为空！");
                return resp;
            }

            // 订单查询成功
            resp.setSuccess(true);
            resp.setTradeNo(result.getTransactionId());
            resp.setAmount(new BigDecimal(result.getAmount().getTotal()).divide(BigDecimal.valueOf(100L), 2, RoundingMode.HALF_UP));
            resp.setSuccessTime(WxPayUtil.dateParse(result.getSuccessTime()));

            if (TradeStateEnum.SUCCESS.getCode().equals(result.getTradeState()) || TradeStateEnum.REFUND.getCode().equals(result.getTradeState())) {
                resp.setTradeStatus(TradeStatusEnum.SUCCESS.getCode());
            } else if (TradeStateEnum.PAY_ERROR.getCode().equals(result.getTradeState()) || TradeStateEnum.CLOSED.getCode().equals(result.getTradeState())) {
                resp.setTradeStatus(TradeStatusEnum.FAILURE.getCode());
            } else {
                resp.setTradeStatus(TradeStatusEnum.WAIT_PAY.getCode());
            }
        } catch (WxPayException e) {
            log.error("微信直营商户--交易查询，请求异常！", e);
        }
        return resp;
    }

    /**
     * 关闭交易订单
     *
     * @param req 交易关闭参数
     * @return 关闭结果
     */
    public Boolean tradeClose(TradeCloseReq req) {
        if (PayModelEnum.SERVICE_PROVIDER.getCode().equals(req.getPayModel())) {
            // 服务商
            return serviceProviderTradeClose(req);
        } else {
            // 直营商户
            return directTradeClose(req);
        }
    }

    /**
     * 服务商关闭交易订单
     *
     * @param req 关闭交易参数
     * @return 关闭结果
     */
    private Boolean serviceProviderTradeClose(TradeCloseReq req) {
        log.info("微信服务商--交易关闭，请求参数：{}", JSUtil.toJsonString(req));
        // step1：获取支付配置
        WxPayConfig wxPayConfig = req.getWxPayConfig();

        // step2：处理关闭交易订单参数
        PartnerTransactionsCloseRequest request = new PartnerTransactionsCloseRequest();
        request.setOutTradeNo(req.getOrderNo());
        request.setSpMchid(wxPayConfig.getWxPayMchId());
        request.setSubMchid(wxPayConfig.getSubMchId());

        // step3：关闭交易订单
        WxPayService wxPayService = WxPayUtil.initService(wxPayConfig);
        try {
            wxPayService.getEcommerceService().closePartnerTransactions(request);
            return true;
        } catch (WxPayException e) {
            log.error("微信服务商--交易关闭，请求异常!", e);
            return false;
        }
    }

    /**
     * 直营商户关闭交易订单
     *
     * @param req 关闭交易参数
     * @return 关闭结果
     */
    private Boolean directTradeClose(TradeCloseReq req) {
        log.info("微信直营商户--交易关闭，请求参数：{}", JSUtil.toJsonString(req));
        WxPayService wxPayService = WxPayUtil.initService(req.getWxPayConfig());
        try {
            wxPayService.closeOrderV3(req.getOrderNo());
            return true;
        } catch (WxPayException e) {
            log.error("微信直营商户--交易关闭，请求异常!", e);
            return false;
        }
    }

    /**
     * 交易通知
     *
     * @param req 交易通知参数
     * @return 通知处理结果
     */
    public TradeNotifyResp tradeNotify(TradeNotifyReq req) {
        log.info("微信支付--交易通知，请求参数：{}", JSUtil.toJsonString(req));

        // step1：创建返回对象
        TradeNotifyResp resp = new TradeNotifyResp();
        resp.setTradeStatus(TradeStatusEnum.WAIT_PAY.getCode());
        resp.setSuccess(false);
        resp.setReturnMsg("failure");

        // step2：加载平台证书
        X509Certificate x509Certificate = PemUtil.loadCertificate(new ByteArrayInputStream(req.getWxCert().getBytes(StandardCharsets.UTF_8)));
        CertificatesVerifier verifier = new CertificatesVerifier(Collections.singletonList(x509Certificate));

        // step3：获取头部参数
        Map<String, String> headerMap = req.getHeaderMap();
        String timestamp = headerMap.get(WECHAT_PAY_TIMESTAMP);
        String nonce = headerMap.get(WECHAT_PAY_NONCE);
        String serialNumber = headerMap.get(WECHAT_PAY_SERIAL);
        String signature = headerMap.get(WECHAT_PAY_SIGNATURE);

        try {
            // step4：构建request，传入必要参数
            NotificationRequest request = new NotificationRequest.Builder().withSerialNumber(serialNumber).withNonce(nonce).withTimestamp(timestamp).withSignature(signature).withBody(req.getBodyParam()).build();
            NotificationHandler handler = new NotificationHandler(verifier, req.getWxPayConfig().getWxPayApiV3Key().getBytes(StandardCharsets.UTF_8));

            // step5：验签和解析请求体
            Notification notification = handler.parse(request);
            String decryptData = notification.getDecryptData();

            // step6：处理通知参数
            JSONObject notifyJson = JSONUtil.parseObj(decryptData);
            String tradeState = notifyJson.getStr("trade_state");
            if (TradeStateEnum.SUCCESS.getCode().equals(tradeState) || TradeStateEnum.REFUND.getCode().equals(tradeState)) {
                resp.setTradeStatus(TradeStatusEnum.SUCCESS.getCode());
            } else if (TradeStateEnum.PAY_ERROR.getCode().equals(tradeState) || TradeStateEnum.CLOSED.getCode().equals(tradeState)) {
                resp.setTradeStatus(TradeStatusEnum.FAILURE.getCode());
            } else {
                resp.setTradeStatus(TradeStatusEnum.WAIT_PAY.getCode());
            }

            resp.setSuccess(true);
            resp.setTradeOrderNo(notifyJson.getStr("out_trade_no"));
            resp.setTradeNo(notifyJson.getStr("transaction_id"));
            resp.setAttach(notifyJson.getStr("attach"));
            BigDecimal amount = notifyJson.getJSONObject("amount").getBigDecimal("total");
            resp.setAmount(amount.divide(BigDecimal.valueOf(100L), 2, RoundingMode.HALF_UP));
            resp.setSuccessTime(notifyJson.getDate("success_time"));
            resp.setReturnMsg("success");
        } catch (ValidationException e) {
            log.error("微信支付--交易回调通知,验签失败！");
            resp.setMsg("验签失败");
            return resp;
        } catch (ParseException e) {
            log.error("微信支付--交易回调通知,解密失败！", e);
            resp.setMsg("解密失败");
            return resp;
        }
        return resp;
    }

    /**
     * 交易退款
     *
     * @param req 退款请求参数
     * @return 响应结果
     */
    public RefundResp refund(RefundReq req) {
        log.info("微信支付--申请退款，请求参数：{}", JSUtil.toJsonString(req));
        WxPayConfig wxPayConfig = req.getWxPayConfig();

        // step1：创建返回对象
        RefundResp resp = new RefundResp();
        resp.setSuccess(false);
        resp.setRefundStatus(RefundStatusEnum.PROCESSING.getCode());

        // step2：处理请求参数
        WxPayRefundV3Request.Amount amount = new WxPayRefundV3Request.Amount()
                .setRefund(WxPayUtil.yuanToCent(req.getRefundAmount()))
                .setTotal(WxPayUtil.yuanToCent(req.getAmount()))
                .setCurrency("CNY");

        WxPayRefundV3Request request = new WxPayRefundV3Request()
                .setOutRefundNo(req.getRefundOrderNo())
                .setOutTradeNo(req.getOrderNo())
                .setReason(req.getRefundReason())
                .setAmount(amount);

        // 服务商模式
        if (PayModelEnum.SERVICE_PROVIDER.getCode().equals(req.getPayModel())) {
            request.setSubMchid(wxPayConfig.getSubMchId());
        }

        // step3：发起退款请求
        WxPayService wxPayService = WxPayUtil.initService(wxPayConfig);

        try {
            WxPayRefundV3Result result = wxPayService.refundV3(request);
            if (ObjectUtil.isNull(result)) {
                return resp;
            }
            log.info("微信支付--申请退款，请求响应结果：{}", JSUtil.toJsonString(result));

            // step5：处理响应信息
            if (!RefundStateEnum.ABNORMAL.getCode().equals(result.getStatus()) && !RefundStateEnum.CLOSED.getCode().equals(result.getStatus())) {
                resp.setSuccess(true);
            }
        } catch (WxPayException e) {
            log.error("微信支付--申请退款，请求异常", e);
        }
        return resp;
    }

    /**
     * 交易退款查询
     *
     * @param req 退款查询请求参数
     * @return 响应结果
     */
    public RefundQueryResp refundQuery(RefundQueryReq req) {
        log.info("微信支付--交易退款查询，请求参数：{}", JSUtil.toJsonString(req));
        // step1：创建返回对象
        RefundQueryResp resp = new RefundQueryResp();
        resp.setSuccess(false);
        resp.setRefundStatus(RefundStatusEnum.PROCESSING.getCode());

        // step2：发起退款查询请求
        WxPayService wxPayService = WxPayUtil.initService(req.getWxPayConfig());
        try {
            WxPayRefundQueryV3Result result = wxPayService.refundQueryV3(req.getRefundOrderNo());

            // step3：处理响应信息
            if (result == null) {
                resp.setMsg("微信支付--退款查询，响应结果为空！");
                return resp;
            }

            resp.setSuccess(true);

            if (RefundStateEnum.SUCCESS.getCode().equals(result.getStatus())) {
                resp.setRefundStatus(RefundStatusEnum.SUCCESS.getCode());
            } else if (RefundStateEnum.ABNORMAL.getCode().equals(result.getStatus())) {
                resp.setRefundStatus(RefundStatusEnum.FAILURE.getCode());
            }
        } catch (WxPayException e) {
            log.error("微信支付--申请退款，请求异常", e);
        }
        return resp;
    }
}
