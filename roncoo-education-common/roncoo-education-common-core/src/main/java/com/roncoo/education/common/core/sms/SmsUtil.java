package com.roncoo.education.common.core.sms;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.aliyuncs.CommonRequest;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.roncoo.education.common.core.enums.SmsPlatformEnum;
import com.roncoo.education.common.core.tools.JSUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author fengyw
 */
@Slf4j
public class SmsUtil {

    public static Boolean sendVerCode(String mobile, String code, Sms sms) {
        if (sms.getSmsPlatform().equals(SmsPlatformEnum.LK_YUN.getCode())) {
            return lkyun(mobile, getParamForCode(code), sms.getLkyunSmsSignName(), sms.getLkyunSmsAuthCode(), sms.getLkyunSmsAccessKeyId(), sms.getLkyunSmsAccessKeySecret());
        } else if (sms.getSmsPlatform().equals(SmsPlatformEnum.ALI_YUN.getCode())) {
            String templateParam = "{\"code\":\"{code}\"}".replace("{code}", code);
            return aliyun(mobile, templateParam, sms.getAliyunSmsSignName(), sms.getAliyunSmsAuthCode(), sms.getAliyunSmsAccessKeyId(), sms.getAliyunSmsAccessKeySecret());
        }
        log.error("该短信平台暂没实现，smsPlatform={}", sms.getSmsPlatform());
        return false;
    }

    private static String getParamForCode(String code) {
        HashMap<String, String> map = new HashMap<>();
        map.put("code", code);
        return JSUtil.toJsonString(map);
    }

    private static String sign(Map<String, Object> paramMap, String key) {
        SortedMap<String, Object> treeMap = new TreeMap<>(paramMap);
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Object> entry : treeMap.entrySet()) {
            if (!"sign".equals(entry.getKey()) && StrUtil.isNotBlank(String.valueOf(entry.getValue()))) {
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&");
            }
        }
        sb.append("key=").append(key);
        return SecureUtil.md5(sb.toString());
    }

    /**
     * 领课云
     *
     * @param phone
     * @param templateParam
     * @param signName
     * @param templateCode
     * @param accessKeyId
     * @param accessKeySecret
     * @return
     */
    private static Boolean lkyun(String phone, String templateParam, String signName, String templateCode, String accessKeyId, String accessKeySecret) {
        Map<String, Object> map = new HashMap<>();
        map.put("accessKeyId", accessKeyId);
        map.put("phoneNumbers", phone);
        map.put("templateCode", templateCode);
        map.put("templateParam", templateParam);
        map.put("signName", signName);
        map.put("signatureNonce", String.valueOf(System.currentTimeMillis()));
        map.put("sign", sign(map, accessKeySecret));
        JSONObject jn = JSONUtil.parseObj(HttpUtil.post("https://cloud.roncoos.com/gateway/user/api/sms/send/sms", map));
        if (jn.getInt("code").equals(200)) {
            return true;
        } else {
            log.error("发送错误", jn.getStr("msg"));
            return false;
        }
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
                log.error("短信发送错误={}", resultJson.getStr("Message"));
                return false;
            }
            return true;
        } catch (Exception e) {
            log.error("发送错误", e);
            return false;
        }
    }
}
