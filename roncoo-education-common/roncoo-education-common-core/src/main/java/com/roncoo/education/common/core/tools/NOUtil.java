/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.core.tools;

import cn.hutool.core.util.RandomUtil;

import java.util.Date;


/**
 * 时间+随机数，一定几率是会重复的，但忽略这个，数据库校验了唯一性
 */
public final class NOUtil {

    private NOUtil() {
    }

    public static Long getOrderNo() {
        return Long.valueOf(DateUtil.format(new Date(), Constants.DATE.YYYYMMDDHHMMSS) + RandomUtil.randomNumbers(3));
    }

    public static Long getSerialNumber() {
        return Long.valueOf(DateUtil.format(new Date(), Constants.DATE.YYYYMMDDHHMMSS) + RandomUtil.randomNumbers(4));
    }

    public static Long getUserNo() {
        return Long.valueOf(DateUtil.format(new Date(), Constants.DATE.YYYYMMDDHHMMSS) + RandomUtil.randomNumbers(2));
    }

}
