/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.core.tools;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author wujing
 */
@Slf4j
public final class JSUtil {

    private JSUtil() {
    }

    public static String toJSONString(Object obj) {
        try {
            return JSONUtil.toJsonStr(obj);
        } catch (Exception e) {
            log.error("json序列化失败", e);
            return "";
        }
    }

    public static <T> T parseObject(String jsonString, Class<T> elementClasses) {
        try {
            JSONObject jsonObject = JSONUtil.parseObj(jsonString);
            return jsonObject.toBean(elementClasses);
        } catch (Exception e) {
            log.error("json解析失败，原字符串={}", jsonString);
            return null;
        }
    }

    public static <T> List<T> parseArray(String jsonString, Class<T> elementClasses) {
        try {
            JSONArray jsonArray = JSONUtil.parseArray(jsonString);
            return jsonArray.toList(elementClasses);
        } catch (Exception e) {
            log.error("json解析失败，原因：", e);
            return null;
        }
    }

}
