package com.roncoo.education.common.pay.impl.alipay;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alipay.api.domain.AlipayTradeWapPayModel;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.roncoo.education.common.core.tools.JsonUtil;
import com.roncoo.education.common.pay.PayFace;
import com.roncoo.education.common.pay.biz.AliPayCommonBiz;
import com.roncoo.education.common.pay.req.*;
import com.roncoo.education.common.pay.resp.*;
import com.roncoo.education.common.pay.util.AliPayConfig;
import com.roncoo.education.common.pay.util.AlipayUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.text.DecimalFormat;

/**
 * 支付宝H5支付
 *
 * @author LYQ
 */
@Slf4j
@Component(value = "aliH5Pay")
@RequiredArgsConstructor
public class AliH5PayImpl implements PayFace {

    private static final DecimalFormat DF = new DecimalFormat("#.##");

    @NotNull
    private final AliPayCommonBiz aliPayCommonBiz;

    /**
     * 交易下单
     *
     * @param req 下单请求参数
     * @return 响应结果
     */
    @Override
    public TradeOrderResp tradeOrder(TradeOrderReq req) {
        AliPayConfig aliPayConfig = req.getAliPayConfig();

        // 创建交易返回对象
        TradeOrderResp resp = new TradeOrderResp();
        resp.setSuccess(false);

        // 处理请求参数
        AlipayTradeWapPayModel model = new AlipayTradeWapPayModel();
        model.setOutTradeNo(req.getTradeSerialNo());
        model.setTotalAmount(DF.format(req.getAmount()));
        model.setSubject(req.getGoodsName());
        model.setProductCode("QUICK_WAP_PAY");
        model.setQuitUrl(req.getQuitUrl());
        model.setPassbackParams(req.getAttach());
        if (ObjectUtil.isNotNull(req.getTimeExpire())) {
            model.setTimeExpire(DateUtil.format(req.getTimeExpire(), DatePattern.NORM_DATETIME_PATTERN));
        }

        // 发起下单请求
        AlipayTradeWapPayResponse response = AlipayUtil.wapPay(aliPayConfig, model, model.getQuitUrl(), req.getNotifyUrl());

        // 处理响应信息
        if (response == null) {
            resp.setMsg("支付下单失败，响应结果为空");
            return resp;
        }
        if (!response.isSuccess()) {
            resp.setMsg(response.getMsg());
            return resp;
        }

        // 下单成功，返回支付信息
        log.info("支付宝H5支付--交易下单，返回表单：{}", response.getBody());
        resp.setSuccess(true);
        resp.setPayMessage(response.getBody());
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
        log.info("支付宝H5支付--交易查询，请求参数：{}", JsonUtil.toJsonString(req));
        return aliPayCommonBiz.tradeQuery(req);
    }

    /**
     * 交易关闭
     *
     * @param req 交易关闭
     * @return 响应结果
     */
    @Override
    public Boolean tradeClose(TradeCloseReq req) {
        log.info("支付宝H5支付--交易关闭，请求参数：{}", JsonUtil.toJsonString(req));
        return aliPayCommonBiz.tradeClose(req);
    }

    /**
     * 交易通知
     *
     * @param req 交易通知参数
     * @return 通知处理结果
     */
    @Override
    public TradeNotifyResp tradeNotify(TradeNotifyReq req) {
        log.info("支付宝H5支付--交易回调通知，请求参数：{}", JsonUtil.toJsonString(req));
        return aliPayCommonBiz.tradeNotify(req);
    }

    /**
     * 交易退款
     *
     * @param req 退款请求参数
     * @return 响应结果
     */
    @Override
    public RefundResp refund(RefundReq req) {
        log.info("支付宝H5支付--申请退款，请求参数：{}", JsonUtil.toJsonString(req));
        return aliPayCommonBiz.refund(req);
    }

    /**
     * 交易退款查询
     *
     * @param req 退款查询请求参数
     * @return 响应结果
     */
    @Override
    public RefundQueryResp refundQuery(RefundQueryReq req) {
        log.info("支付宝H5支付--退款查询，请求参数：{}", JsonUtil.toJsonString(req));
        return aliPayCommonBiz.refundQuery(req);
    }
}
