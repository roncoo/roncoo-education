/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.core.tools;

import cn.hutool.core.util.RandomUtil;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间+随机数，一定几率是会重复的，但忽略这个，数据库校验了唯一性
 */
public final class NumUtil {

    private final static String YYYYMMDDHHMMSS = "yyyyMMddHHmmss";

    private NumUtil() {
    }

    public static String getVerCode() {
        return RandomUtil.randomNumbers(6);
    }

    public static Long getOrderNo() {
        return Long.valueOf(format(new Date(), YYYYMMDDHHMMSS) + RandomUtil.randomNumbers(3));
    }

    public static Long getSerialNumber() {
        return Long.valueOf(format(new Date(), YYYYMMDDHHMMSS) + RandomUtil.randomNumbers(4));
    }

    private static String format(Date date, String format) {
        String result = "";
        try {
            if (date != null) {
                DateFormat dateFormat = new SimpleDateFormat(format);
                result = dateFormat.format(date);
            }
        } catch (Exception e) {
        }
        return result;
    }

}
