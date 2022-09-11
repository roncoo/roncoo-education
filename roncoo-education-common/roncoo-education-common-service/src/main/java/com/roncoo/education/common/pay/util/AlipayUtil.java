package com.roncoo.education.common.pay.util;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.StrUtil;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.*;
import com.alipay.api.request.*;
import com.alipay.api.response.*;
import com.roncoo.education.common.core.tools.JSUtil;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 支付宝工具类
 *
 * @author LYQ
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AlipayUtil {

    /**
     * 默认编码
     */
    private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * 支付宝请求地址
     */
    private static final String REQUEST_URL = "https://openapi.alipay.com/gateway.do";

    /**
     * alipay.trade.precreate(统一收单线下交易预创建)
     * 收银员通过收银台或商户后台调用支付宝接口，生成二维码后，展示给用户，由用户扫描二维码完成订单支付
     *
     * @param aliPayConfig 支付宝配置
     * @param model        请求参数
     * @param notifyUrl    支付宝服务器主动通知商户服务器里指定的页面http/https路径
     * @return 预下单结果
     * @see <a href="https://opendocs.alipay.com/open/02ekfg?scene=19">统一收单线下交易预创建</a>
     */
    public static AlipayTradePrecreateResponse preCreate(AliPayConfig aliPayConfig, AlipayTradePrecreateModel model, String notifyUrl) {
        try {
            AlipayClient alipayClient = new DefaultAlipayClient(REQUEST_URL, aliPayConfig.getAliPayAppId(), aliPayConfig.getAliPayAppPrivateKey(), "json", DEFAULT_CHARSET, aliPayConfig.getAliPayPublicKey(), aliPayConfig.getSignType());
            AlipayTradePrecreateRequest request = new AlipayTradePrecreateRequest();
            request.setNotifyUrl(notifyUrl);

            // 请求参数
            request.setBizModel(model);
            return alipayClient.execute(request);
        } catch (AlipayApiException e) {
            log.error("支付宝--统一收单线下交易预创建失败！", e);
        }
        return null;
    }

    /**
     * alipay.trade.app.pay(app支付接口2.0)
     * 外部商户APP唤起快捷SDK创建订单并支付
     *
     * @param aliPayConfig 支付宝配置
     * @param model        请求参数
     * @param notifyUrl    支付宝服务器主动通知商户服务器里指定的页面http/https路径
     * @return 预下单结果
     * @see <a href="https://opendocs.alipay.com/open/02e7gq?scene=20">app支付接口2.0</a>
     */
    public static AlipayTradeAppPayResponse appPay(AliPayConfig aliPayConfig, AlipayTradeAppPayModel model, String notifyUrl) {
        try {
            AlipayClient alipayClient = new DefaultAlipayClient(REQUEST_URL, aliPayConfig.getAliPayAppId(), aliPayConfig.getAliPayAppPrivateKey(), "json", DEFAULT_CHARSET, aliPayConfig.getAliPayPublicKey(), aliPayConfig.getSignType());

            AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
            request.setNotifyUrl(notifyUrl);

            // 请求参数
            request.setBizModel(model);
            return alipayClient.sdkExecute(request);
        } catch (AlipayApiException e) {
            log.error("支付宝--app支付下单失败！", e);
        }
        return null;
    }

    /**
     * alipay.trade.wap.pay(手机网站支付接口2.0)
     * 外部商户创建订单并支付
     *
     * @param aliPayConfig 支付宝配置
     * @param model        请求参数
     * @param returnUrl    HTTP/HTTPS开头字符串,同步跳转页面地址
     * @param notifyUrl    支付宝服务器主动通知商户服务器里指定的页面http/https路径
     * @return 预下单结果
     * @see <a href="https://opendocs.alipay.com/open/02ivbs?scene=common">手机网站支付接口2.0</a>
     */
    public static AlipayTradeWapPayResponse wapPay(AliPayConfig aliPayConfig, AlipayTradeWapPayModel model, String returnUrl, String notifyUrl) {
        try {
            AlipayClient alipayClient = new DefaultAlipayClient(REQUEST_URL, aliPayConfig.getAliPayAppId(), aliPayConfig.getAliPayAppPrivateKey(), "json", DEFAULT_CHARSET, aliPayConfig.getAliPayPublicKey(), aliPayConfig.getSignType());

            AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
            request.setReturnUrl(returnUrl);
            request.setNotifyUrl(notifyUrl);

            // 请求参数
            request.setBizModel(model);
            return alipayClient.pageExecute(request);
        } catch (AlipayApiException e) {
            log.error("支付宝--wap支付下单失败！", e);
        }
        return null;
    }


    /**
     * alipay.trade.query(统一收单线下交易查询)
     *
     * @param aliPayConfig 支付宝配置
     * @param outTradeNo   特殊选填--订单支付时传入的商户订单号,和支付宝交易号不能同时为空。trade_no,out_trade_no如果同时存在优先取trade_no
     * @param tradeNo      特殊选填--支付宝交易号，和商户订单号不能同时为空
     * @param queryOptions 选填--查询选项，商户通过上送该参数来定制同步需要额外返回的信息字段，数组格式。
     * @return 订单信息
     * @see <a href="https://opendocs.alipay.com/open/02ekfh?scene=23">统一收单线下交易查询</a>
     */
    public static AlipayTradeQueryResponse tradeQuery(AliPayConfig aliPayConfig, String outTradeNo, String tradeNo, List<String> queryOptions) {
        try {
            AlipayClient alipayClient = new DefaultAlipayClient(REQUEST_URL, aliPayConfig.getAliPayAppId(), aliPayConfig.getAliPayAppPrivateKey(), "json", DEFAULT_CHARSET, aliPayConfig.getAliPayPublicKey(), aliPayConfig.getSignType());
            AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();

            AlipayTradeQueryModel model = new AlipayTradeQueryModel();
            if (StrUtil.isNotBlank(outTradeNo)) {
                model.setOutTradeNo(outTradeNo);
            }
            if (StrUtil.isNotBlank(tradeNo)) {
                model.setTradeNo(tradeNo);
            }
            if (CollectionUtil.isNotEmpty(queryOptions)) {
                model.setQueryOptions(queryOptions);
            }
            request.setBizModel(model);
            return alipayClient.execute(request);
        } catch (AlipayApiException e) {
            log.error("支付宝--统一收单线下交易查询！", e);
        }
        return null;
    }

    /**
     * alipay.trade.refund(统一收单交易退款接口)
     *
     * @param aliPayConfig 支付宝配置
     * @param model        退款请求参数
     * @return 退款发起结果
     * @see <a href="https://opendocs.alipay.com/open/02ekfk">统一收单交易退款</a>
     */
    public static AlipayTradeRefundResponse tradeRefund(AliPayConfig aliPayConfig, AlipayTradeRefundModel model) {
        try {
            AlipayClient alipayClient = new DefaultAlipayClient(REQUEST_URL, aliPayConfig.getAliPayAppId(), aliPayConfig.getAliPayAppPrivateKey(), "json", DEFAULT_CHARSET, aliPayConfig.getAliPayPublicKey(), aliPayConfig.getSignType());
            AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
            // 请求参数
            request.setBizModel(model);
            return alipayClient.execute(request);
        } catch (AlipayApiException e) {
            log.error("支付宝--统一收单交易退款！", e);
        }
        return null;
    }

    /**
     * alipay.trade.fastpay.refund.query(统一收单交易退款查询)
     * 商户可使用该接口查询自已通过alipay.trade.refund提交的退款请求是否执行成功。
     * 该接口的返回码10000，仅代表本次查询操作成功，不代表退款成功，
     * 当接口返回的refund_status值为REFUND_SUCCESS时表示退款成功，否则表示退款没有执行成功。
     * 如果退款未成功，商户可以调用退款接口重试，重试时请务必保证退款请求号和退款金额一致，防止重复退款。 注：发起退款查询接口的时间不能离退款请求时间太短，建议之间间隔10秒以上。
     *
     * @param aliPayConfig 支付宝配置
     * @param tradeNo      特殊可选--支付宝交易号。和商户订单号不能同时为空
     * @param outTradeNo   特殊可选--商户订单号。订单支付时传入的商户订单号,和支付宝交易号不能同时为空。 trade_no,out_trade_no如果同时存在优先取trade_no
     * @param outRequestNo 必选--退款请求号。请求退款接口时，传入的退款请求号，如果在退款请求时未传入，则该值为创建交易时的商户订单号。
     * @param queryOptions 可选--查询选项，商户通过上送该参数来定制同步需要额外返回的信息字段，数组格式。枚举支持：refund_detail_item_list：本次退款使用的资金渠道;gmt_refund_pay：退款执行成功的时间；
     * @return 退款订单信息
     * @see <a href="https://opendocs.alipay.com/open/02ekfl">统一收单交易退款查询</a>
     */
    public static AlipayTradeFastpayRefundQueryResponse refundQuery(AliPayConfig aliPayConfig, String tradeNo, String outTradeNo, String outRequestNo, List<String> queryOptions) {
        try {
            AlipayClient alipayClient = new DefaultAlipayClient(REQUEST_URL, aliPayConfig.getAliPayAppId(), aliPayConfig.getAliPayAppPrivateKey(), "json", DEFAULT_CHARSET, aliPayConfig.getAliPayPublicKey(), aliPayConfig.getSignType());
            AlipayTradeFastpayRefundQueryRequest request = new AlipayTradeFastpayRefundQueryRequest();

            AlipayTradeFastpayRefundQueryModel model = new AlipayTradeFastpayRefundQueryModel();

            if (StrUtil.isNotBlank(tradeNo)) {
                model.setTradeNo(tradeNo);
            }
            if (StrUtil.isNotBlank(outTradeNo)) {
                model.setOutTradeNo(outTradeNo);
            }
            if (StrUtil.isNotBlank(outRequestNo)) {
                model.setOutRequestNo(outRequestNo);
            }
            // 返回参数选项，按需传入
            if (CollectionUtil.isNotEmpty(queryOptions)) {
                model.setQueryOptions(queryOptions);
            }

            request.setBizModel(model);
            return alipayClient.execute(request);
        } catch (AlipayApiException e) {
            log.error("支付宝--统一收单交易退款查询！", e);
        }
        return null;
    }

    /**
     * alipay.trade.close(统一收单交易关闭接口)
     * 用于交易创建后，用户在一定时间内未进行支付，可调用该接口直接将未付款的交易进行关闭。
     *
     * @param aliPayConfig 支付宝配置
     * @param tradeNo      特殊可选--该交易在支付宝系统中的交易流水号。最短 16 位，最长 64 位。和out_trade_no不能同时为空，如果同时传了 out_trade_no和 trade_no，则以 trade_no为准。
     * @param outTradeNo   特殊可选--订单支付时传入的商户订单号,和支付宝交易号不能同时为空。 trade_no,out_trade_no如果同时存在优先取trade_no
     * @param operatorId   可选--商家操作员编号 id，由商家自定义。
     * @return 关闭结果
     * @see <a href="https://opendocs.alipay.com/open/02o6e7">统一收单交易关闭</a>
     */
    public static AlipayTradeCloseResponse tradeClose(AliPayConfig aliPayConfig, String tradeNo, String outTradeNo, String operatorId) {
        try {
            AlipayClient alipayClient = new DefaultAlipayClient(REQUEST_URL, aliPayConfig.getAliPayAppId(), aliPayConfig.getAliPayAppPrivateKey(), "json", DEFAULT_CHARSET, aliPayConfig.getAliPayPublicKey(), aliPayConfig.getSignType());
            AlipayTradeCloseRequest request = new AlipayTradeCloseRequest();

            AlipayTradeCloseModel model = new AlipayTradeCloseModel();
            if (StrUtil.isNotBlank(tradeNo)) {
                model.setTradeNo(tradeNo);
            }
            if (StrUtil.isNotBlank(outTradeNo)) {
                model.setOutTradeNo(outTradeNo);
            }
            if (StrUtil.isNotBlank(operatorId)) {
                model.setOperatorId(operatorId);
            }

            request.setBizModel(model);
            return alipayClient.execute(request);
        } catch (AlipayApiException e) {
            log.error("支付宝--统一收单交易关闭！", e);
        }
        return null;
    }

    public static <T> T toBean(String content, Class<T> clazz) {
        return JSUtil.parseObject(content, clazz);
    }
}
