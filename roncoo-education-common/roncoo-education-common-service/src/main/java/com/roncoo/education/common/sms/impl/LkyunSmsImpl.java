package com.roncoo.education.common.sms.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.roncoo.education.common.tools.JsonUtil;
import com.roncoo.education.common.sms.Sms;
import com.roncoo.education.common.sms.SmsFace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author fengyw
 */
@Slf4j
@Component(value = "lkyunSms")
public class LkyunSmsImpl implements SmsFace {

    private static final String SMS_SEND_PATH = "/gateway/user/api/sms/send/sms";

    @Override
    public Boolean sendVerCode(String mobile, String code, Sms sms) {
        return lkyun(mobile, getParamForCode(code), sms.getLkyunSmsSignName(), sms.getLkyunSmsAuthCode(),
                sms.getLkyunSmsDomain(), sms.getLkyunSmsAccessKeyId(), sms.getLkyunSmsAccessKeySecret());
    }
    
    @Override
    public Boolean sendPurchaseSuccess(String mobile, String courseName, Long orderNo, Sms sms) {
        return lkyun(mobile, getParamForPurchase(courseName, orderNo), sms.getLkyunSmsSignName(), sms.getLkyunSmsAuthCode(),
                sms.getLkyunSmsDomain(), sms.getLkyunSmsAccessKeyId(), sms.getLkyunSmsAccessKeySecret());
    }

    /**
     * 领课云
     *
     * @param phone
     * @param templateParam
     * @param signName
     * @param templateCode
     * @param domain
     * @param accessKeyId
     * @param accessKeySecret
     * @return
     */
    private static Boolean lkyun(String phone, String templateParam, String signName, String templateCode, String domain,
                                 String accessKeyId, String accessKeySecret) {
        if (StrUtil.isBlank(domain)) {
            log.error("领课云短信接口域名未配置，手机号={}", phone);
            return false;
        }

        Map<String, Object> map = new HashMap<>();
        map.put("accessKeyId", accessKeyId);
        map.put("phoneNumbers", phone);
        map.put("templateCode", templateCode);
        map.put("templateParam", templateParam);
        map.put("signName", signName);
        map.put("signatureNonce", String.valueOf(System.currentTimeMillis()));
        map.put("sign", sign(map, accessKeySecret));
        try {
            String url = StrUtil.removeSuffix(domain.trim(), "/") + SMS_SEND_PATH;
            JSONObject jn = JSONUtil.parseObj(HttpRequest.post(url).header("Content-Type", "application/json").body(JSONUtil.toJsonStr(map)).execute().body());
            if (!jn.getInt("code").equals(200)) {
                log.error("短信发送错误={}，手机号={}，templateParam={}", jn, phone, templateParam);
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
}
