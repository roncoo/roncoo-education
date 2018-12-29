/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.config;

import java.io.IOException;
import java.util.Properties;

import lombok.extern.slf4j.Slf4j;

/**
 * 配置文件读取工具类
 *
 * @author wujing
 */
@Slf4j
public final class ConfigUtil {

	private ConfigUtil() {
	}

	private static final Properties properties = new Properties();

	static {
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
			log.warn("当前环境配置为：{}", getProperty("env"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getProperty(String keyName) {
		return properties.getProperty(keyName, "").trim();
	}

}
