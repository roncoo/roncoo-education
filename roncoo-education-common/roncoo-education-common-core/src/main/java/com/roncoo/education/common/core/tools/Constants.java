/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.core.tools;

/**
 * 常量工具类
 *
 * @author wujing
 */
public final class Constants {

    private Constants() {
    }

    // 手机号码校验
    public static final String REGEX_MOBILE = "^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199)\\d{8}$";

    public static final String ADMIN = "admin";
    public static final String TOKEN = "token";
    public static final String USER_ID = "userId";
    public static final Integer FREEZE = 3;

    /**
     * 单位：分钟
     */
    public final static int SESSIONTIME = 40;

    public interface RedisPre {
        String ADMINI_MENU = "admin::menu::";
        String USERS_INFO = "users::info::";
        String CONFIG_WEBSITE = "config::website";
    }

}
