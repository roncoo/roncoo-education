/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.service;

import com.roncoo.education.common.core.base.Base;
import com.roncoo.education.common.core.tools.JSUtil;

import javax.servlet.http.HttpServletRequest;
import java.io.DataInputStream;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Map;
import java.util.TreeMap;

/**
 * 控制基础类，所以controller都应该继承这个类
 *
 * @author wujing
 */
public class BaseController extends Base {

    /**
     * 重定向
     *
     * @param format
     * @param arguments
     * @return
     */
    public static String redirect(String format, Object... arguments) {
        return new StringBuffer("redirect:").append(MessageFormat.format(format, arguments)).toString();
    }

    public static String getString(HttpServletRequest request) {
        DataInputStream in = null;
        try {
            in = new DataInputStream(request.getInputStream());
            byte[] buf = new byte[request.getContentLength()];
            in.readFully(buf);
            return new String(buf, "UTF-8");
        } catch (Exception e) {
            return "";
        } finally {
            if (null != in) {
                try {
                    in.close();// 关闭数据流
                } catch (IOException e) {
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static TreeMap<String, Object> getParamMap(HttpServletRequest request) {
        TreeMap<String, Object> paramMap = new TreeMap<>();
        Map<String, String[]> map = request.getParameterMap();
        for (String key : map.keySet()) {
            paramMap.put(key, map.get(key)[0]);
        }
        if (paramMap.isEmpty()) {
            return new TreeMap<>(JSUtil.parseObject(getString(request), TreeMap.class));
        }
        return paramMap;
    }

}
