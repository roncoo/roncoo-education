/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.core.tools;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Collections;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * Object To Map and Map To Object
 *
 * @author wujing
 */
@Slf4j
public final class ObjMapUtil {

    private ObjMapUtil() {

    }

    public static String contrast(Map<String, Object> map1, Map<String, Object> map2) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : map1.entrySet()) {
            String value1 = entry.getValue().toString();
            String value2 = map2.get(entry.getKey()).toString();
            if (!value1.equals(value2)) {
                sb.append("F:").append(entry.getKey()).append(",N:").append(value1).append(",O:").append(value2).append(";");
            }
        }
        return sb.toString();
    }

    public static <T> T map2Obj(Map<String, Object> map, Class<T> clz) throws Exception {
        T obj = clz.getDeclaredConstructor().newInstance();
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            int mod = field.getModifiers();
            if (Modifier.isStatic(mod) || Modifier.isFinal(mod)) {
                continue;
            }
            field.setAccessible(true);
            field.set(obj, map.get(field.getName()));
        }
        return obj;
    }

    public static Map<String, Object> obj2Map(Object obj) {
        Map<String, Object> map = BeanUtil.beanToMap(obj, Boolean.FALSE, Boolean.TRUE);
        return CollectionUtil.isEmpty(map) ? Collections.emptyMap() : map;
    }

    public static SortedMap<String, Object> obj2MapForSort(Object obj) {
        SortedMap<String, Object> map = new TreeMap<>();
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (null != field.get(obj)) {
                    map.put(field.getName(), field.get(obj));
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                log.error("解析失败，原因", e);
            }
        }
        return map;
    }

}
