package com.roncoo.education.common.pay.biz;

import cn.hutool.core.util.ObjectUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.response.AlipayTradeCloseResponse;
import com.alipay.api.response.AlipayTradeFastpayRefundQueryResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.roncoo.education.common.core.tools.JSUtil;
import com.roncoo.education.common.pay.req.*;
import com.roncoo.education.common.pay.resp.RefundQueryResp;
import com.roncoo.education.common.pay.resp.RefundResp;
import com.roncoo.education.common.pay.resp.TradeNotifyResp;
import com.roncoo.education.common.pay.resp.TradeQueryResp;
import com.roncoo.education.common.pay.util.AlipayUtil;
import com.roncoo.education.common.pay.util.RefundStatusEnum;
import com.roncoo.education.common.pay.util.TradeNotifyRequest;
import com.roncoo.education.common.pay.util.TradeStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Map;

/**
 * 支付宝支付公共业务
 *
 * @author LYQ
 */
@Slf4j
@Component
public class AliPayCommonBiz {

    private static final DecimalFormat DF = new DecimalFormat("#.##");


    /**
     * 交易查询
     *
     * @param req 交易查询参数
     * @return 响应结果
     */
    public TradeQueryResp tradeQuery(TradeQueryReq req) {
        log.info("支付宝支付--交易查询，请求参数：{}", JSUtil.toJsonString(req));

        // step1：创建返回对象
        TradeQueryResp resp = new TradeQueryResp();
        resp.setOrderNo(req.getOrderNo());
        resp.setTradeStatus(TradeStatusEnum.WAIT_PAY.getCode());
        resp.setSuccess(false);

        // step3：发起查询请求
        AlipayTradeQueryResponse response = AlipayUtil.tradeQuery(req.getAliPayConfig(), req.getOrderNo(), null, null);

        // step4：处理响应信息
        if (response == null) {
            resp.setMsg("支付订单查询失败，响应结果为空！");
            return resp;
        }
        if (!response.isSuccess()) {
            resp.setMsg(response.getMsg());
            return resp;
        }

        // 订单查询成功
        resp.setSuccess(true);
        resp.setTradeNo(response.getTradeNo());
        resp.setAmount(new BigDecimal(response.getTotalAmount()));
        resp.setSuccessTime(response.getSendPayDate());

        //WAIT_BUYER_PAY（交易创建，等待买家付款
        // TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）
        // TRADE_SUCCESS（交易支付成功）
        // TRADE_FINISHED（交易结束，不可退款）
        if ("WAIT_BUYER_PAY".equals(response.getTradeStatus())) {
            resp.setTradeStatus(TradeStatusEnum.WAIT_PAY.getCode());
        } else if ("TRADE_CLOSED".equals(response.getTradeStatus())) {
            if (ObjectUtil.isNull(response.getSendPayDate())) {
                resp.setTradeStatus(TradeStatusEnum.FAILURE.getCode());
            } else {
                resp.setTradeStatus(TradeStatusEnum.SUCCESS.getCode());
            }
        } else if ("TRADE_SUCCESS".equals(response.getTradeStatus()) || "TRADE_FINISHED".equals(response.getTradeStatus())) {
            resp.setTradeStatus(TradeStatusEnum.SUCCESS.getCode());
        }
        return resp;
    }

    /**
     * 交易关闭
     *
     * @param req 交易关闭
     * @return 响应结果
     */
    public Boolean tradeClose(TradeCloseReq req) {
        log.info("支付宝支付--交易关闭，请求参数：{}", JSUtil.toJsonString(req));

        // step1：创建返回对象
        TradeQueryResp resp = new TradeQueryResp();
        resp.setOrderNo(req.getOrderNo());
        resp.setTradeStatus(TradeStatusEnum.WAIT_PAY.getCode());
        resp.setSuccess(false);

        AlipayTradeCloseResponse response = AlipayUtil.tradeClose(req.getAliPayConfig(), null, req.getOrderNo(), null);
        if (ObjectUtil.isNull(response)) {
            return false;
        }
        if (response.isSuccess()) {
            return true;
        }
        if ("ACQ.TRADE_NOT_EXIST".equals(response.getSubCode())) {
            // 交易不存在
            return true;
        }

        return ObjectUtil.isNotNull(response) && response.isSuccess();
    }

    /**
     * 交易通知
     *
     * @param req 交易通知参数
     * @return 通知处理结果
     */
    public TradeNotifyResp tradeNotify(TradeNotifyReq req) {
        log.info("支付宝支付--交易回调通知，请求参数：{}", JSUtil.toJsonString(req));

        // step1：创建返回对象
        TradeNotifyResp resp = new TradeNotifyResp();
        resp.setTradeStatus(TradeStatusEnum.WAIT_PAY.getCode());
        resp.setSuccess(false);
        resp.setReturnMsg("failure");

        Map<String, String> paramMap = req.getQueryParamMap();

        // step3：参数验签
        try {
            boolean signResult = AlipaySignature.verifyV1(paramMap, req.getAliPayConfig().getAliPayPublicKey(), paramMap.get("charset"), paramMap.get("sign_type"));
            if (!signResult) {
                log.error("支付宝支付--交易回调通知,验签失败！");
                resp.setMsg("验签失败");
                return resp;
            }
        } catch (AlipayApiException e) {
            log.error("支付宝支付--交易回调通知,验签异常！", e);
            resp.setMsg("验签异常");
            return resp;
        }

        // step4：处理请求参数
        TradeNotifyRequest tradeNotifyRequest = AlipayUtil.toBean(JSUtil.toJsonString(paramMap), TradeNotifyRequest.class);

        //WAIT_BUYER_PAY（交易创建，等待买家付款
        // TRADE_CLOSED（未付款交易超时关闭，或支付完成后全额退款）
        // TRADE_SUCCESS（交易支付成功）
        // TRADE_FINISHED（交易结束，不可退款）
        if ("WAIT_BUYER_PAY".equals(tradeNotifyRequest.getTradeStatus())) {
            resp.setTradeStatus(TradeStatusEnum.WAIT_PAY.getCode());
        } else if ("TRADE_CLOSED".equals(tradeNotifyRequest.getTradeStatus())) {
            if (ObjectUtil.isNull(tradeNotifyRequest.getGmtPayment())) {
                resp.setTradeStatus(TradeStatusEnum.FAILURE.getCode());
            } else {
                resp.setTradeStatus(TradeStatusEnum.SUCCESS.getCode());
            }
        } else if ("TRADE_SUCCESS".equals(tradeNotifyRequest.getTradeStatus()) || "TRADE_FINISHED".equals(tradeNotifyRequest.getTradeStatus())) {
            resp.setTradeStatus(TradeStatusEnum.SUCCESS.getCode());
        }

        resp.setSuccess(true);
        resp.setTradeOrderNo(tradeNotifyRequest.getOutTradeNo());
        resp.setTradeNo(tradeNotifyRequest.getTradeNo());
        resp.setAttach(tradeNotifyRequest.getBody());
        resp.setAmount(tradeNotifyRequest.getTotalAmount());
        resp.setSuccessTime(tradeNotifyRequest.getGmtPayment());
        resp.setReturnMsg("success");
        return resp;
    }

    /**
     * 交易退款
     *
     * @param req 退款请求参数
     * @return 响应结果
     */
    public RefundResp refund(RefundReq req) {
        log.info("支付宝支付--申请退款，请求参数：{}", JSUtil.toJsonString(req));

        // step1：创建返回对象
        RefundResp resp = new RefundResp();
        resp.setSuccess(false);
        resp.setRefundStatus(RefundStatusEnum.PROCESSING.getCode());

        // step3：处理请求参数
        AlipayTradeRefundModel model = new AlipayTradeRefundModel();
        model.setOutRequestNo(req.getRefundOrderNo());
        model.setOutTradeNo(req.getOrderNo());
        model.setRefundAmount(DF.format(req.getRefundAmount()));
        model.setRefundReason(req.getRefundReason());

        // step4：发起退款请求
        AlipayTradeRefundResponse response = AlipayUtil.tradeRefund(req.getAliPayConfig(), model);

        // step5：处理响应信息
        if (response == null) {
            resp.setMsg("支付宝支付--申请退款，响应结果为空！");
            return resp;
        }
        if (!response.isSuccess()) {
            resp.setMsg(response.getMsg());
            return resp;
        }

        resp.setSuccess(true);
        return resp;
    }

    /**
     * 交易退款查询
     *
     * @param req 退款查询请求参数
     * @return 响应结果
     */
    public RefundQueryResp refundQuery(RefundQueryReq req) {
        log.info("支付宝支付--退款查询，请求参数：{}", JSUtil.toJsonString(req));

        // step1：创建返回对象
        RefundQueryResp resp = new RefundQueryResp();
        resp.setSuccess(false);
        resp.setRefundStatus(RefundStatusEnum.PROCESSING.getCode());

        // step3：发起退款查询请求
        AlipayTradeFastpayRefundQueryResponse response = AlipayUtil.refundQuery(req.getAliPayConfig(), req.getOrderNo(), null, req.getRefundOrderNo(), null);

        // step4：处理响应信息
        if (response == null) {
            resp.setMsg("支付宝支付--退款查询，响应结果为空！");
            return resp;
        }
        if (!response.isSuccess()) {
            resp.setMsg(response.getMsg());
            return resp;
        }

        resp.setSuccess(true);
        if ("REFUND_SUCCESS".equals(response.getRefundStatus())) {
            resp.setRefundStatus(RefundStatusEnum.SUCCESS.getCode());
        }
        return resp;
    }

}
