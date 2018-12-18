/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.util.aliyun;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.roncoo.education.util.config.ConfigUtil;

/**
 * 阿里云短信接口
 */
public final class AliyunSmsUtil {

	private static final Logger logger = LoggerFactory.getLogger(AliyunSmsUtil.class);

	private AliyunSmsUtil() {
	}

	private static final String product = "Dysmsapi";
	private static final String domain = "dysmsapi.aliyuncs.com";
	private static final String accessKeyId = ConfigUtil.ALIYUN_ACCESSKEYID;
	private static final String accessKeySecret = ConfigUtil.ALIYUN_ACCESSKEYSECRET;

	/**
	 * 发送验证码
	 * 
	 * @param phone
	 *            手机
	 * @param code
	 *            验证码
	 * @throws ClientException
	 */
	public static void sendCode(String phone, String code) throws ClientException {
		sendMsg(phone, "龙果学院", "SMS_59740002", "{\"code\":\"" + code + "\",\"product\":\"" + "" + "\"}");
	}

	/**
	 * @param smsTemplateCode
	 * @param recNum
	 * @param smsParamString
	 * @return
	 * @throws ClientException
	 * @throws ApiException
	 */
	private static void sendMsg(String phone, String signName, String templateCode, String templateParam) throws ClientException {
		System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
		System.setProperty("sun.net.client.defaultReadTimeout", "10000");
		IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
		DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
		IAcsClient acsClient = new DefaultAcsClient(profile);
		SendSmsRequest request = new SendSmsRequest();
		request.setMethod(MethodType.POST);
		request.setPhoneNumbers(phone);
		request.setSignName(signName);
		request.setTemplateCode(templateCode);
		request.setTemplateParam(templateParam);
		SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
		if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
			// 请求成功
			logger.debug(sendSmsResponse.getCode());
		} else {
			throw new ClientException(sendSmsResponse.getCode(), sendSmsResponse.getMessage());
		}
	}

}
