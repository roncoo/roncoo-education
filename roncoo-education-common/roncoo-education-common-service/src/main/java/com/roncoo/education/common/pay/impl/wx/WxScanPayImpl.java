package com.roncoo.education.common.pay.impl.wx;

import cn.hutool.core.util.ObjectUtil;
import com.github.binarywang.wxpay.bean.ecommerce.PartnerTransactionsRequest;
import com.github.binarywang.wxpay.bean.ecommerce.TransactionsResult;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderV3Request;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderV3Result;
import com.github.binarywang.wxpay.bean.result.enums.TradeTypeEnum;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.roncoo.education.common.core.tools.JSUtil;
import com.roncoo.education.common.pay.PayFace;
import com.roncoo.education.common.pay.biz.WxPayCommonBiz;
import com.roncoo.education.common.pay.req.*;
import com.roncoo.education.common.pay.resp.*;
import com.roncoo.education.common.pay.util.PayModelEnum;
import com.roncoo.education.common.pay.util.WxPayUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 微信扫码支付
 *
 * @author LYQ
 */
@Slf4j
@Component(value = "wxScanPay")
@RequiredArgsConstructor
public class WxScanPayImpl implements PayFace {

    @NotNull
    private final WxPayCommonBiz wxPayCommonBiz;

    /**
     * 交易下单
     *
     * @param req 下单请求参数
     * @return 响应结果
     */
    @Override
    public TradeOrderResp tradeOrder(TradeOrderReq req) {
        if (PayModelEnum.SERVICE_PROVIDER.getCode().equals(req.getPayModel())) {
            // 服务商
            return serviceProviderPay(req);
        } else {
            // 直营商户
            return directPay(req);
        }
    }

    /**
     * 微信服务商扫码支付
     *
     * @param req 支付请求参数
     * @return 支付下单结果
     */
    private TradeOrderResp serviceProviderPay(TradeOrderReq req) {
        log.info("微信服务商--扫码支付--交易下单，请求参数：{}", JSUtil.toJsonString(req));

        // step1：创建交易返回对象
        TradeOrderResp resp = new TradeOrderResp();
        resp.setSuccess(false);

        // step2：处理请求参数
        PartnerTransactionsRequest.Amount amount = new PartnerTransactionsRequest.Amount();
        amount.setTotal(WxPayUtil.yuanToCent(req.getAmount()));

        PartnerTransactionsRequest request = new PartnerTransactionsRequest();
        request.setDescription(req.getGoodsName());
        request.setOutTradeNo(req.getTradeSerialNo());
        request.setTimeExpire(WxPayUtil.timeExpireFormat(req.getTimeExpire()));
        request.setAttach(req.getAttach());
        request.setNotifyUrl(req.getNotifyUrl());
        request.setAmount(amount);

        // step3：发起下单请求
        WxPayService wxPayService = WxPayUtil.initService(req.getWxPayConfig());
        try {
            com.github.binarywang.wxpay.bean.ecommerce.enums.TradeTypeEnum tradeTypeEnum = com.github.binarywang.wxpay.bean.ecommerce.enums.TradeTypeEnum.NATIVE;
            TransactionsResult result = wxPayService.getEcommerceService().partner(tradeTypeEnum, request);
            if (ObjectUtil.isNull(result)) {
                log.error("微信服务商--扫码支付--交易下单，响应信息为空！");
                return resp;
            }

            // 下单成功，返回支付信息
            resp.setSuccess(true);
            resp.setPayMessage(result.getCodeUrl());
        } catch (WxPayException e) {
            log.error("微信服务商--扫码支付--交易下单，请求失败！", e);
        }
        return resp;
    }

    /**
     * 微信直营商户扫码支付
     *
     * @param req 支付请求参数
     * @return 支付下单结果
     */
    private TradeOrderResp directPay(TradeOrderReq req) {
        log.info("微信直营商户--扫码支付--交易下单，请求参数：{}", JSUtil.toJsonString(req));

        // step1：创建交易返回对象
        TradeOrderResp resp = new TradeOrderResp();
        resp.setSuccess(false);

        // step2：处理请求参数
        WxPayUnifiedOrderV3Request.Amount amount = new WxPayUnifiedOrderV3Request.Amount()
                .setTotal(WxPayUtil.yuanToCent(req.getAmount()));

        WxPayUnifiedOrderV3Request request = new WxPayUnifiedOrderV3Request()
                .setDescription(req.getGoodsName())
                .setOutTradeNo(req.getTradeSerialNo())
                .setTimeExpire(WxPayUtil.timeExpireFormat(req.getTimeExpire()))
                .setAttach(req.getAttach())
                .setNotifyUrl(req.getNotifyUrl())
                .setAmount(amount);

        // step3：发起下单请求
        WxPayService wxPayService = WxPayUtil.initService(req.getWxPayConfig());
        try {
            WxPayUnifiedOrderV3Result result = wxPayService.unifiedOrderV3(TradeTypeEnum.NATIVE, request);
            if (ObjectUtil.isNull(result)) {
                log.error("微信直营商户--扫码支付--交易下单，响应信息为空！");
                return resp;
            }

            // 下单成功，返回支付信息
            resp.setSuccess(true);
            resp.setPayMessage(result.getCodeUrl());
        } catch (WxPayException e) {
            log.error("微信直营商户--扫码支付--交易下单，请求失败！", e);
        }
        return resp;
    }

    /**
     * 交易查询
     *
     * @param req 交易查询参数
     * @return 响应结果
     */
    @Override
    public TradeQueryResp tradeQuery(TradeQueryReq req) {
        log.info("微信扫码支付--交易查询，请求参数：{}", JSUtil.toJsonString(req));
        return wxPayCommonBiz.tradeQuery(req);
    }

    /**
     * 交易关闭
     *
     * @param req 交易关闭
     * @return 响应结果
     */
    @Override
    public Boolean tradeClose(TradeCloseReq req) {
        log.info("微信扫码支付--交易关闭，请求参数：{}", JSUtil.toJsonString(req));
        return wxPayCommonBiz.tradeClose(req);
    }

    /**
     * 交易通知
     *
     * @param req 交易通知参数
     * @return 通知处理结果
     */
    @Override
    public TradeNotifyResp tradeNotify(TradeNotifyReq req) {
        log.info("微信扫码支付--交易通知，请求参数：{}", JSUtil.toJsonString(req));
        return wxPayCommonBiz.tradeNotify(req);
    }

    /**
     * 交易退款
     *
     * @param req 退款请求参数
     * @return 响应结果
     */
    @Override
    public RefundResp refund(RefundReq req) {
        log.info("微信扫码支付--交易退款，请求参数：{}", JSUtil.toJsonString(req));
        return wxPayCommonBiz.refund(req);
    }

    /**
     * 交易退款查询
     *
     * @param req 退款查询请求参数
     * @return 响应结果
     */
    @Override
    public RefundQueryResp refundQuery(RefundQueryReq req) {
        log.info("微信扫码支付--交易退款查询，请求参数：{}", JSUtil.toJsonString(req));
        return wxPayCommonBiz.refundQuery(req);
    }
}
