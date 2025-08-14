package com.roncoo.education.common.sms.impl;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.roncoo.education.common.tools.JsonUtil;
import com.roncoo.education.common.sms.Sms;
import com.roncoo.education.common.sms.SmsFace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @author fengyw
 */
@Slf4j
@Component(value = "aliyunSms")
public class AliyunSmsImpl implements SmsFace {

    @Override
    public Boolean sendVerCode(String mobile, String code, Sms sms) {
        return aliyun(mobile, getParamForCode(code), sms.getAliyunSmsSignName(), sms.getAliyunSmsAuthCode(), sms.getAliyunSmsAccessKeyId(), sms.getAliyunSmsAccessKeySecret());
    }
    
    @Override
    public Boolean sendPurchaseSuccess(String mobile, String courseName, Long orderNo, Sms sms) {
        return aliyun(mobile, getParamForPurchase(courseName, orderNo), sms.getAliyunSmsSignName(), sms.getAliyunSmsAuthCode(), sms.getAliyunSmsAccessKeyId(), sms.getAliyunSmsAccessKeySecret());
    }

    /**
     * 阿里云
     *
     * @param phone
     * @param templateParam
     * @param signName
     * @param templateCode
     * @param accessKeyId
     * @param accessKeySecret
     * @return
     */
    private static Boolean aliyun(String phone, String templateParam, String signName, String templateCode, String accessKeyId, String accessKeySecret) {
        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
        IAcsClient acsClient = new DefaultAcsClient(profile);
        // 组装请求对象
        CommonRequest request = new CommonRequest();
        request.setSysMethod(MethodType.POST);
        request.setSysDomain("dysmsapi.aliyuncs.com");
        request.setSysVersion("2017-05-25");
        request.setSysAction("SendSms");
        request.putQueryParameter("RegionId", "cn-hangzhou");
        request.putQueryParameter("PhoneNumbers", phone);
        request.putQueryParameter("SignName", signName);
        request.putQueryParameter("TemplateCode", templateCode);
        request.putQueryParameter("TemplateParam", templateParam);
        try {
            JSONObject resultJson = JSONUtil.parseObj(acsClient.getCommonResponse(request).getData());
            if (!"OK".equals(resultJson.getStr("Code"))) {
                log.error("短信发送错误={}，手机号={}，templateParam={}", resultJson, phone, templateParam);
                return false;
            }
            return true;
        } catch (Exception e) {
            log.error("发送错误", e);
            return false;
        }
    }

    private static String getParamForCode(String code) {
        HashMap<String, String> map = new HashMap<>();
        map.put("code", code);
        return JsonUtil.toJsonString(map);
    }
    
    private static String getParamForPurchase(String courseName, Long orderNo) {
        HashMap<String, String> map = new HashMap<>();
        map.put("courseName", courseName);
        map.put("orderNo", orderNo.toString());
        return JsonUtil.toJsonString(map);
    }
}
