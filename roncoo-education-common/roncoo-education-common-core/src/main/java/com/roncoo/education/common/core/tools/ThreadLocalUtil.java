/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.core.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpSession;

/**
 * 用ThreadLocal来存储Session,以便实现Session any where.
 *
 * @author wujing
 */
public class ThreadLocalUtil {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    private static ThreadLocal<HttpSession> httpSessionThreadLocal = new ThreadLocal<HttpSession>();

    public static HttpSession getHttpSessionThreadLocal() {
        return ThreadLocalUtil.httpSessionThreadLocal.get();
    }

    public static void setHttpSessionThreadLocal(HttpSession httpSession) {
        ThreadLocalUtil.httpSessionThreadLocal.set(httpSession);
    }

    public static void remove() {
        ThreadLocalUtil.httpSessionThreadLocal.remove();
    }

}
