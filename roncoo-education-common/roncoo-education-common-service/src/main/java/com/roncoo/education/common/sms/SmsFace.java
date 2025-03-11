package com.roncoo.education.common.sms;

/**
 * 短信发送
 *
 * @author fengyw
 */
public interface SmsFace {
    /**
     * 发送短信验证码
     *
     * @param mobile 手机
     * @param code   验证码
     * @param sms
     * @return
     */
    Boolean sendVerCode(String mobile, String code, Sms sms);
    
    /**
     * 发送课程购买成功通知
     *
     * @param mobile 手机
     * @param courseName 课程名称
     * @param orderNo 订单号
     * @param sms
     * @return
     */
    Boolean sendPurchaseSuccess(String mobile, String courseName, Long orderNo, Sms sms);
}
