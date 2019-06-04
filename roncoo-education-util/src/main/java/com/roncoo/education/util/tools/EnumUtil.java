package com.roncoo.education.util.tools;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class EnumUtil {

    private static final String DEFAULT_ENUM_NAME = "name";

    /**
     * 枚举转List格式
     *
     * @param targetEnumClazz 目标枚举Clazz
     * @return 装换结果
     */
    public static List<Map<String, Object>> toList(Class targetEnumClazz) {
        return toList(targetEnumClazz, DEFAULT_ENUM_NAME);
    }

    /**
     * 枚举转List格式
     *
     * @param targetEnumClazz 目标枚举Clazz
     * @param enumName        返回JSON中枚举名称对应的Key
     * @return 装换结果
     */
    public static List<Map<String, Object>> toList(Class targetEnumClazz, String enumName) {
        try {
            //获取方法
            Method[] methods = targetEnumClazz.getMethods();
            Field[] fields = targetEnumClazz.getDeclaredFields();
            List<Field> fieldList = new ArrayList<>();
            for (Method method : methods) {
                for (Field field : fields) {
                    if (method.getName().endsWith(toUpperCaseFirstOne(field.getName()))) {
                        fieldList.add(field);
                    }
                }
            }

            List<Map<String, Object>> resultList = new ArrayList<>();
            //获取值
            Enum[] enums = (Enum[]) targetEnumClazz.getEnumConstants();
            for (Enum e : enums) {
                Map<String, Object> eMap = new HashMap<>();
                String enumNameValue = e.name();
                for (Field field : fieldList) {
                    field.setAccessible(true);
                    if (field.getName().equals(enumName)) {
                        enumNameValue = enumNameValue + ";" + field.get(e);
                    } else {
                        eMap.put(field.getName(), field.get(e));
                    }
                }
                if (enumNameValue.startsWith(";")) {
                    enumNameValue = enumNameValue.substring(1);
                }
                eMap.put(enumName, enumNameValue);
                resultList.add(eMap);
            }

            return resultList;
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 首字母转大写
     *
     * @param s 需要操作的字符串
     * @return 转换后结果
     */
    private static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
        }
    }

}
