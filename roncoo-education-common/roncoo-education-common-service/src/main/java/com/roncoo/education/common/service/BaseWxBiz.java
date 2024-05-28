/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.common.service;

import com.roncoo.education.common.core.base.Base;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpMapConfigImpl;
import org.springframework.stereotype.Component;

/**
 * 基础类
 *
 * @author wujing
 */
@Component
public class BaseWxBiz extends Base {

    public WxOAuth2UserInfo getAuthInfo(String appId, String appSecret, String code) throws WxErrorException {
        WxMpService wxMpService = new WxMpServiceImpl();
        WxMpMapConfigImpl mpMapConfig = new WxMpMapConfigImpl();
        mpMapConfig.setAppId(appId);
        mpMapConfig.setSecret(appSecret);
        wxMpService.setWxMpConfigStorage(mpMapConfig);
        WxOAuth2AccessToken accessToken = wxMpService.getOAuth2Service().getAccessToken(code);
        return wxMpService.getOAuth2Service().getUserInfo(accessToken, "zh_CN");
    }

}
