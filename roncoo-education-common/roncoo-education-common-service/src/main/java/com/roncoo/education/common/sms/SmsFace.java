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
}
