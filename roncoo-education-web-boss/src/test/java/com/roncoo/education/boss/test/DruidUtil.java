package com.roncoo.education.boss.test;

import com.alibaba.druid.filter.config.ConfigTools;

/**
 * druid 加密
 * 
 * @author wujing
 */
public class DruidUtil {

	public static void main(String[] args) throws Exception {
		String password = "123456";
		System.out.println(ConfigTools.encrypt(password));
	}

}
