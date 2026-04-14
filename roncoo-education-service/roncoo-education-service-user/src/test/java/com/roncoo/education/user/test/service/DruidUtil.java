package com.roncoo.education.user.test.service;

import com.alibaba.druid.filter.config.ConfigTools;

public class DruidUtil {

    public static void main(String[] args) throws Exception {
        String password = "123456";
        // 加密后的密码
        System.out.println(ConfigTools.encrypt(password));
    }

}
