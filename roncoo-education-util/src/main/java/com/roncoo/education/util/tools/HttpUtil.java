/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.tools;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * http调用工具类
 * 
 * @author wujing
 */
public final class HttpUtil {

	private static final String APPLICATION_JSON = "application/json";
	private static final String CONTENT_TYPE_TEXT_JSON = "text/json";
	private static final String CHARSET_UTF_8 = "UTF-8";
	private static final int TIMEOUT = 10000;

	private HttpUtil() {
	}

	private static final Logger logger = LoggerFactory.getLogger(HttpUtil.class);

	private static SimpleClientHttpRequestFactory requestFactory = null;
	static {
		requestFactory = new SimpleClientHttpRequestFactory();
		requestFactory.setConnectTimeout(60000); // 连接超时时间，单位=毫秒
		requestFactory.setReadTimeout(60000); // 读取超时时间，单位=毫秒
	}
	private static RestTemplate restTemplate = new RestTemplate(requestFactory);

	public static JsonNode postForObject(String url, Map<String, Object> map) {
		logger.info("POST 请求， url={}，map={}", url, map.toString());
		return restTemplate.postForObject(url, map, JsonNode.class);
	}

	/**
	 * 
	 * @param url
	 * @param param
	 * @return
	 */
	public static String postForPay(String url, Map<String, Object> param) {
		logger.info("POST 请求， url={}，map={}", url, param.toString());
		try {
			HttpPost httpPost = new HttpPost(url.trim());
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(TIMEOUT).setConnectionRequestTimeout(TIMEOUT).setSocketTimeout(TIMEOUT).build();
			httpPost.setConfig(requestConfig);
			List<NameValuePair> nvps = new ArrayList<NameValuePair>();
			for (Map.Entry<String, Object> entry : param.entrySet()) {
				nvps.add(new BasicNameValuePair(entry.getKey(), entry.getValue().toString()));
			}
			StringEntity se = new UrlEncodedFormEntity(nvps, CHARSET_UTF_8);
			httpPost.setEntity(se);
			HttpResponse httpResponse = HttpClientBuilder.create().build().execute(httpPost);
			return EntityUtils.toString(httpResponse.getEntity(), CHARSET_UTF_8);
		} catch (Exception e) {
			logger.info("HTTP请求出错", e);
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * 
	 * @param url
	 * @param param
	 * @return
	 */
	public static String post(String url, Map<String, Object> param) {
		logger.info("POST 请求， url={}，map={}", url, param.toString());
		try {
			HttpPost httpPost = new HttpPost(url.trim());
			RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(TIMEOUT).setConnectionRequestTimeout(TIMEOUT).setSocketTimeout(TIMEOUT).build();
			httpPost.setConfig(requestConfig);
			httpPost.addHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON);
			StringEntity se = new StringEntity(param.toString(), CHARSET_UTF_8);
			se.setContentType(CONTENT_TYPE_TEXT_JSON);
			se.setContentEncoding(new BasicHeader(HTTP.CONTENT_TYPE, APPLICATION_JSON));
			httpPost.setEntity(se);
			HttpResponse httpResponse = HttpClientBuilder.create().build().execute(httpPost);
			return EntityUtils.toString(httpResponse.getEntity(), CHARSET_UTF_8);
		} catch (Exception e) {
			logger.info("HTTP请求出错", e);
			e.printStackTrace();
		}
		return "";
	}

}
