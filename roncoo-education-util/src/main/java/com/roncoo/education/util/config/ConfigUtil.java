/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.config;

import java.io.IOException;
import java.util.Properties;

/**
 * 配置文件读取工具类
 *
 * @author wujing
 */
public final class ConfigUtil {

	private ConfigUtil() {
	}

	private static final Properties properties = new Properties();

	static {
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String getProperty(String keyName) {
		return properties.getProperty(keyName, "").trim();
	}

	public static final String ALIYUN_ACCESSKEYID = getProperty("aliyun_accessKeyId");
	public static final String ALIYUN_ACCESSKEYSECRET = getProperty("aliyun_accessKeySecret");

	public static final String ALIYUN_OSS_URL = getProperty("aliyun_oss_url");
	public static final String ALIYUN_OSS_BUCKET = getProperty("aliyun_oss_bucket");
	public static final String ALIYUN_OSS_ENDPOINT = getProperty("aliyun_oss_endpoint");

	public static final String ALIYUN_OAS_URL = getProperty("aliyun_oas_url");
	public static final String ALIYUN_OAS_VAULT = getProperty("aliyun_oas_vault");

	public static final String PERIOD_VIDEO_PATH = getProperty("period_video_path");
	public static final String PIC_PATH = getProperty("pic_path");

	// 支付回调配置
	public static final String RONCOO_PAY_NOTIFY = getProperty("roncoo_pay_notify");
	public static final String WEIXIN_PAY_NOTIFY = getProperty("weixin_pay_notify");
	public static final String ALI_PAY_NOTIFY = getProperty("ali_pay_notify");
	public static final String PAY_URL = getProperty("pay_url");
	public static final String PAY_KEY = getProperty("pay_key");
	public static final String PAY_SECRET = getProperty("pay_secret");

	public static final String POLYV_GETCATAURL = getProperty("polyv_getCataUrl");
	public static final String POLYV_UPLOADVIDEO = getProperty("polyv_uploadVideo");
	public static final String POLYV_CHANGECATAURL = getProperty("polyv_changeCataUrl");
	public static final String POLYV_DELVIDEOBYID = getProperty("polyv_delVideoById");
	public static final String POLYV_USEID = getProperty("polyv_useId");
	public static final String POLYV_DELETEVIDEO = getProperty("polyv_deleteVideo");
	public static final String POLYV_QUESTION = getProperty("polyv_question");
	public static final String POLYV_READTOKEN = getProperty("polyv_readtoken");
	public static final String POLYV_WRITETOKEN = getProperty("polyv_writetoken");
	public static final String POLYV_SECRETKEY = getProperty("polyv_secretkey");
	public static final String POLYV_CODESECRETKEY = getProperty("polyv_codeSecretKey");
	public static final String POLYV_GETTOKEN = getProperty("polyv_getToken");

	public static final String LECTURER_DEFAULT_PROPORTION = getProperty("lecturer_default_proportion");
}
