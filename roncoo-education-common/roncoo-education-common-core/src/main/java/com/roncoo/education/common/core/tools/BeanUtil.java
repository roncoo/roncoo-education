/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.core.tools;

import org.springframework.beans.BeanUtils;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * 队列属性复制
 *
 * @param <T>
 * @author wujing
 */
public final class BeanUtil<T extends Serializable> {

    private BeanUtil() {
    }

    /**
     * @param source
     * @param clazz
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public static <T> T copyProperties(Object source, Class<T> clazz) {
        if (source == null) {
            return null;
        }
        T t = null;
        try {
            t = clazz.newInstance();
            BeanUtils.copyProperties(source, t);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return t;
    }

    /**
     * @param source
     * @param clazz
     * @return
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     * @throws InstantiationException
     */
    public static <T> List<T> copyProperties(List<?> source, Class<T> clazz) {
        if (source == null || source.size() == 0) {
            return Collections.emptyList();
        }
        List<T> res = new ArrayList<>(source.size());
        for (Object o : source) {
            T t = null;
            try {
                t = clazz.newInstance();
                BeanUtils.copyProperties(o, t);
            } catch (Exception e) {
                e.printStackTrace();
            }
            res.add(t);
        }
        return res;
    }

    public static <T> T objToBean(Map<?, ?> map, Class<T> clazz) {
        return cn.hutool.core.bean.BeanUtil.toBeanIgnoreCase(map, clazz, true);
    }

    public static Map<String, String> beanToStringMap(Object obj) {
        try {
            Map<String, String> resultMap = new HashMap<>();
            //获取类的属性描述器
            BeanInfo beaninfo = Introspector.getBeanInfo(obj.getClass(), Object.class);
            //获取类的属性集
            PropertyDescriptor[] pro = beaninfo.getPropertyDescriptors();
            for (PropertyDescriptor property : pro) {
                //得到属性的name
                String key = property.getName();
                Method get = property.getReadMethod();
                //执行get方法得到属性的值
                Object value = get.invoke(obj);
                if (value != null) {
                    resultMap.put(key, String.valueOf(value));
                }
            }
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
