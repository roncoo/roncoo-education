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

    public static final String ADMIN = "admin";
    public static final String TOKEN = "token";
    public static final String USER_ID = "userId";

    /**
     * session有效期，单位：分钟
     */
    public final static int SESSIONTIME = 40;

    public interface RedisPre {
        String ADMINI_MENU = "admin::menu::";
        String ADMIN_VERI_CODE = "admin::ver::code::";
        String USER_STUDY = "user::study::";
        String RESOURCE = "resource::";
        String PROGRESS = "progress::";
        String CODE = "code::";
    }

}
