package com.roncoo.education.common.service;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipaySystemOauthTokenRequest;
import com.alipay.api.request.AlipayUserInfoShareRequest;
import com.alipay.api.response.AlipaySystemOauthTokenResponse;
import com.alipay.api.response.AlipayUserInfoShareResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;

/**
 * 支付宝OAuth工具类
 *
 * @author devin
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AlipayOAuthUtil {

    /**
     * 默认编码
     */
    private static final String DEFAULT_CHARSET = "UTF-8";

    /**
     * 支付宝请求地址
     */
    private static final String REQUEST_URL = "https://openapi.alipay.com/gateway.do";

    /**
     * 获取支付宝用户信息
     *
     * @param appId        应用ID
     * @param privateKey   应用私钥
     * @param alipayPubKey 支付宝公钥
     * @param code         授权码
     * @return 用户信息
     */
    public static WxOAuth2UserInfo getAuthInfo(String appId, String privateKey, String alipayPubKey, String code) {
        try {
            AlipayClient alipayClient = new DefaultAlipayClient(REQUEST_URL, appId, privateKey, "json", DEFAULT_CHARSET, alipayPubKey, "RSA2");
            
            // 获取访问令牌
            AlipaySystemOauthTokenRequest tokenRequest = new AlipaySystemOauthTokenRequest();
            tokenRequest.setCode(code);
            tokenRequest.setGrantType("authorization_code");
            AlipaySystemOauthTokenResponse tokenResponse = alipayClient.execute(tokenRequest);
            
            if (!tokenResponse.isSuccess()) {
                log.error("获取支付宝访问令牌失败：{}", tokenResponse.getSubMsg());
                return null;
            }
            
            // 获取用户信息
            AlipayUserInfoShareRequest userInfoRequest = new AlipayUserInfoShareRequest();
            AlipayUserInfoShareResponse userInfoResponse = alipayClient.execute(userInfoRequest, tokenResponse.getAccessToken());
            
            if (!userInfoResponse.isSuccess()) {
                log.error("获取支付宝用户信息失败：{}", userInfoResponse.getSubMsg());
                return null;
            }
            
            // 转换为通用用户信息对象
            WxOAuth2UserInfo userInfo = new WxOAuth2UserInfo();
            userInfo.setOpenid(userInfoResponse.getUserId());
            userInfo.setUnionId(userInfoResponse.getUserId()); // 支付宝没有unionId，使用userId代替
            userInfo.setNickname(userInfoResponse.getNickName());
            userInfo.setHeadImgUrl(userInfoResponse.getAvatar());
            userInfo.setCity(userInfoResponse.getCity());
            userInfo.setProvince(userInfoResponse.getProvince());
            userInfo.setCountry("中国"); // 支付宝没有返回国家信息，默认为中国
            userInfo.setSex(userInfoResponse.getGender().equals("m") ? 1 : 2); // m-男，f-女
            
            return userInfo;
        } catch (AlipayApiException e) {
            log.error("获取支付宝用户信息异常", e);
            return null;
        }
    }
}
