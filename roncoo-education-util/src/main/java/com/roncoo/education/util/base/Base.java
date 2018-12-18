/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.base;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 基础类
 * 
 * @author wujing
 */
public class Base {

	protected static final String REGEX_MOBILE = "^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(17[0,3,5-8])|(18[0-9])|166|198|199)\\d{8}$";// 手机号码校验

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	protected static final ExecutorService callbackExecutor = Executors.newFixedThreadPool(50);

	public final static Integer FREEZE = 3;// 冻结状态

	public final static BigDecimal LECTURER_DEFAULT_PROPORTION = BigDecimal.valueOf(0.70); // 讲师默认分成

	public static String getString(Map<String, Object> map, String key) {
		if (null != map.get(key)) {
			return map.get(key).toString();
		}
		return "";
	}

	public static Map<String, Object> getMap() {
		return new HashMap<>();
	}

	public void log(Object obj) {
		logger.info(ReflectionToStringBuilder.toString(obj, ToStringStyle.MULTI_LINE_STYLE));
	}
}
