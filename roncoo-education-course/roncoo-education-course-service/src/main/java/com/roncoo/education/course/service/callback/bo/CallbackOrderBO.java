package com.roncoo.education.course.service.callback.bo;

import java.io.Serializable;

/**
 * <p>
 * 订单信息表
 * </p>
 *
 * @author wujing123
 */
public class CallbackOrderBO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String payKey;

    private String productName;

    private String outTradeNo;

    private String orderPrice;

    private String productType;

    private String tradeStatus;

    private String successTime;

    private String orderTime;

    private String trxNo;

    private String remark;

    private String notify_time;

    private String notify_type;

    private String notify_id;

    private String app_id;

    private String charset;

    private String version;

    private String sign_type;

    private String trade_no;

    private String out_trade_no;

    private String sign;

    public String getPayKey() {
        return payKey;
    }

    public void setPayKey(String payKey) {
        this.payKey = payKey;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getOrderPrice() {
        return orderPrice;
    }

    public void setOrderPrice(String orderPrice) {
        this.orderPrice = orderPrice;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getTradeStatus() {
        return tradeStatus;
    }

    public void setTradeStatus(String tradeStatus) {
        this.tradeStatus = tradeStatus;
    }

    public String getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(String successTime) {
        this.successTime = successTime;
    }

    public String getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    public String getTrxNo() {
        return trxNo;
    }

    public void setTrxNo(String trxNo) {
        this.trxNo = trxNo;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getNotify_time() {
        return notify_time;
    }

    public void setNotify_time(String notify_time) {
        this.notify_time = notify_time;
    }

    public String getNotify_type() {
        return notify_type;
    }

    public void setNotify_type(String notify_type) {
        this.notify_type = notify_type;
    }

    public String getNotify_id() {
        return notify_id;
    }

    public void setNotify_id(String notify_id) {
        this.notify_id = notify_id;
    }

    public String getApp_id() {
        return app_id;
    }

    public void setApp_id(String app_id) {
        this.app_id = app_id;
    }

    public String getCharset() {
        return charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getSign_type() {
        return sign_type;
    }

    public void setSign_type(String sign_type) {
        this.sign_type = sign_type;
    }

    public String getTrade_no() {
        return trade_no;
    }

    public void setTrade_no(String trade_no) {
        this.trade_no = trade_no;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public String toString() {
        return "OrderInfoRoncooPayNotifyBO [payKey=" + payKey + ", productName=" + productName + ", outTradeNo=" + outTradeNo + ", orderPrice=" + orderPrice + ", productType=" + productType + ", tradeStatus=" + tradeStatus + ", successTime=" + successTime + ", orderTime=" + orderTime + ", trxNo=" + trxNo + ", remark=" + remark + ", notify_time=" + notify_time + ", notify_type=" + notify_type + ", notify_id=" + notify_id + ", app_id=" + app_id + ", charset=" + charset + ", version=" + version + ", sign_type=" + sign_type + ", trade_no=" + trade_no + ", out_trade_no=" + out_trade_no + ", sign=" + sign + "]";
    }

}
