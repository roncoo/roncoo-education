package com.roncoo.education.common.config;

/**
 * @author fengyw
 * @author fengyw
 */
public final class ThreadContext {

    private ThreadContext() {
    }

    /**
     * 用户ID（Admin端、User登录才有）
     */
    private static final ThreadLocal<String> USER_ID_LOCAL = new ThreadLocal<>();


    /**
     * 获取用户ID
     *
     * @return 用户ID
     */
    public static Long userId() {
        return Long.valueOf(USER_ID_LOCAL.get());
    }

    /**
     * 添加用户ID
     *
     * @param val 用户ID
     */
    public static void setUserId(String val) {
        if (val == null) {
            removeUserId();
            return;
        }
        USER_ID_LOCAL.set(val);
    }

    /**
     * 移除用户ID
     */
    public static void removeUserId() {
        USER_ID_LOCAL.remove();
    }

}
