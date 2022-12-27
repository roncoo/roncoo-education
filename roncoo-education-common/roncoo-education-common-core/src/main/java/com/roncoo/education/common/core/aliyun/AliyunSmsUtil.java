//package com.roncoo.education.common.core.aliyun;
//
//import cn.hutool.json.JSONObject;
//import cn.hutool.json.JSONUtil;
//import com.aliyuncs.CommonRequest;
//import com.aliyuncs.DefaultAcsClient;
//import com.aliyuncs.IAcsClient;
//import com.aliyuncs.http.MethodType;
//import com.aliyuncs.profile.DefaultProfile;
//import com.aliyuncs.profile.IClientProfile;
//import lombok.extern.slf4j.Slf4j;
//
///**
// * @author fengyw
// */
//@Slf4j
//public final class AliyunSmsUtil {
//
//    private AliyunSmsUtil() {
//    }
//
//    /**
//     * 发送验证码
//     *
//     * @param mobile
//     * @param code
//     * @param aliyun
//     */
//    public static Boolean sendVerCode(String mobile, String code, Aliyun aliyun) {
//        String templateParam = "{\"code\":\"{code}\"}".replace("{code}", code);
//        return send(mobile, templateParam, aliyun.getAliyunSmsSignName(), aliyun.getAliyunSmsAuthCode(), aliyun.getAliyunSmsAccessKeyId(), aliyun.getAliyunSmsAccessKeySecret());
//    }
//
//    private static Boolean send(String phone, String templateParam, String signName, String templateCode, String accessKeyId, String accessKeySecret) {
//        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
//        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
//        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
//        IAcsClient acsClient = new DefaultAcsClient(profile);
//        // 组装请求对象
//        CommonRequest request = new CommonRequest();
//        request.setSysMethod(MethodType.POST);
//        request.setSysDomain("dysmsapi.aliyuncs.com");
//        request.setSysVersion("2017-05-25");
//        request.setSysAction("SendSms");
//        request.putQueryParameter("RegionId", "cn-hangzhou");
//        request.putQueryParameter("PhoneNumbers", phone);
//        request.putQueryParameter("SignName", signName);
//        request.putQueryParameter("TemplateCode", templateCode);
//        request.putQueryParameter("TemplateParam", templateParam);
//        try {
//            JSONObject resultJson = JSONUtil.parseObj(acsClient.getCommonResponse(request).getData());
//            if (!"OK".equals(resultJson.getStr("Code"))) {
//                log.error("短信发送错误={}", resultJson.getStr("Message"));
//                return false;
//            }
//            return true;
//        } catch (Exception e) {
//            log.error("系统繁忙", e);
//            return false;
//        }
//    }
//}
