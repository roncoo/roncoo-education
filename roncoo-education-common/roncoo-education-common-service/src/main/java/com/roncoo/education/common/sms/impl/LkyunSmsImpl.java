package com.roncoo.education.common.sms.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.roncoo.education.common.core.tools.JsonUtil;
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

    @Override
    public Boolean sendVerCode(String mobile, String code, Sms sms) {
        return lkyun(mobile, getParamForCode(code), sms.getLkyunSmsSignName(), sms.getLkyunSmsAuthCode(), sms.getLkyunSmsAccessKeyId(), sms.getLkyunSmsAccessKeySecret());
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
        try {
            JSONObject jn = JSONUtil.parseObj(HttpRequest.post("https://cloud.roncoos.com/gateway/user/api/sms/send/sms").header("Content-Type", "application/json").body(JSONUtil.toJsonStr(map)).execute().body());
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
