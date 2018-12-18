/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.tools;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

/**
 * Object To Map and Map To Object
 * 
 * @author wujing
 */
public final class ObjectMapUtil {

	private ObjectMapUtil() {

	}

	public static <T> T map2Obj(Map<String, Object> map, Class<T> clz) throws Exception {
		T obj = clz.newInstance();
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

	public static Map<String, Object> Obj2Map(Object obj) {
		Map<String, Object> map = new HashMap<String, Object>();
		Field[] fields = obj.getClass().getDeclaredFields();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				if (null != field.get(obj))
					map.put(field.getName(), field.get(obj));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return map;
	}

}
