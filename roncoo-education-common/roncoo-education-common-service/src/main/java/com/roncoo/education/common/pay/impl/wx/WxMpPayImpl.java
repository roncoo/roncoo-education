package com.roncoo.education.common.pay.impl.wx;

import cn.hutool.core.util.ObjectUtil;
import com.github.binarywang.wxpay.bean.request.WxPayUnifiedOrderV3Request;
import com.github.binarywang.wxpay.bean.result.WxPayUnifiedOrderV3Result;
import com.github.binarywang.wxpay.bean.result.enums.TradeTypeEnum;
import com.github.binarywang.wxpay.config.WxPayConfig;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.WxPayService;
import com.roncoo.education.common.tools.JsonUtil;
import com.roncoo.education.common.pay.PayFace;
import com.roncoo.education.common.pay.biz.WxPayCommonBiz;
import com.roncoo.education.common.pay.req.*;
import com.roncoo.education.common.pay.resp.*;
import com.roncoo.education.common.pay.util.WxPayUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import jakarta.validation.constraints.NotNull;

/**
 * 微信公众号支付
 *
 * @author LYQ
 */
@Slf4j
@Component(value = "wxMpPay")
@RequiredArgsConstructor
public class WxMpPayImpl implements PayFace {


    @NotNull
    private final WxPayCommonBiz wxPayCommonBiz;

    @Override
    public TradeOrderResp tradeOrder(TradeOrderReq req) {
        return directPay(req);
    }

    /**
     * 微信直营商户小程序支付
     *
     * @param req 支付请求参数
     * @return 支付下单结果
     */
    private TradeOrderResp directPay(TradeOrderReq req) {
        // 创建交易返回对象
        TradeOrderResp resp = new TradeOrderResp();
        resp.setSuccess(false);

        // 处理请求参数
        WxPayUnifiedOrderV3Request.Amount amount = new WxPayUnifiedOrderV3Request.Amount().setTotal(WxPayUtil.yuanToCent(req.getAmount()));

        WxPayUnifiedOrderV3Request.Payer payer = new WxPayUnifiedOrderV3Request.Payer().setOpenid(req.getOpenId());

        WxPayUnifiedOrderV3Request request = new WxPayUnifiedOrderV3Request()
                .setDescription(req.getGoodsName())
                .setOutTradeNo(req.getTradeSerialNo())
                .setTimeExpire(WxPayUtil.timeExpireFormat(req.getTimeExpire()))
                .setAttach(req.getAttach())
                .setNotifyUrl(req.getNotifyUrl())
                .setAmount(amount)
                .setPayer(payer);

        // 发起下单请求
        WxPayService wxPayService = WxPayUtil.initService(req.getWxPayConfig());
        try {
            WxPayUnifiedOrderV3Result result = wxPayService.unifiedOrderV3(TradeTypeEnum.JSAPI, request);
            if (ObjectUtil.isNull(result)) {
                log.error("微信直营商户--公众号支付--交易下单，响应信息为空");
                return resp;
            }

            WxPayConfig payConfig = wxPayService.getConfig();
            WxPayUnifiedOrderV3Result.JsapiResult jsapiResult = result.getPayInfo(TradeTypeEnum.JSAPI, payConfig.getAppId(), payConfig.getMchId(), payConfig.getPrivateKey());
            // 下单成功，返回支付信息
            resp.setSuccess(true);
            resp.setPayMessage(JsonUtil.toJsonString(jsapiResult));
        } catch (WxPayException e) {
            log.error("微信直营商户--公众号支付--交易下单，请求失败", e);
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
        log.info("微信公众号支付--交易查询，请求参数：{}", JsonUtil.toJsonString(req));
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
        log.info("微信公众号支付--交易关闭，请求参数：{}", JsonUtil.toJsonString(req));
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
        log.info("微信公众号支付--交易通知，请求参数：{}", JsonUtil.toJsonString(req));
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
        log.info("微信公众号支付--交易退款，请求参数：{}", JsonUtil.toJsonString(req));
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
        log.info("微信公众号支付--交易退款查询，请求参数：{}", JsonUtil.toJsonString(req));
        return wxPayCommonBiz.refundQuery(req);
    }
}
