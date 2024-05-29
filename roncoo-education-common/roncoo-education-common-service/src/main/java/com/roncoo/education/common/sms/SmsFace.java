package com.roncoo.education.common.sms;

/**
 * @author fengyw
 */
public interface SmsFace {
    Boolean sendVerCode(String mobile, String code, Sms sms);
}
