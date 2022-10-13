package com.roncoo.education.common.pay;


import com.roncoo.education.common.pay.req.*;
import com.roncoo.education.common.pay.resp.*;

/**
 * 支付接口
 *
 * @author LYQ
 */
public interface PayFace {

    /**
     * 交易下单
     *
     * @param req 下单请求参数
     * @return 响应结果
     */
    TradeOrderResp tradeOrder(TradeOrderReq req);

    /**
     * 交易查询
     *
     * @param req 交易查询参数
     * @return 响应结果
     */
    TradeQueryResp tradeQuery(TradeQueryReq req);

    /**
     * 交易关闭
     *
     * @param req 交易关闭
     * @return 响应结果
     */
    Boolean tradeClose(TradeCloseReq req);

    /**
     * 交易通知
     *
     * @param req 交易通知参数
     * @return 通知处理结果
     */
    TradeNotifyResp tradeNotify(TradeNotifyReq req);

    /**
     * 交易退款
     *
     * @param req 退款请求参数
     * @return 响应结果
     */
    RefundResp refund(RefundReq req);

    /**
     * 交易退款查询
     *
     * @param req 退款查询请求参数
     * @return 响应结果
     */
    RefundQueryResp refundQuery(RefundQueryReq req);

}
