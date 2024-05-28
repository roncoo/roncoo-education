/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.user.test.service;

import com.roncoo.education.common.core.enums.ClientTypeEnum;
import com.roncoo.education.common.core.enums.LoginAuthTypeEnum;
import com.roncoo.education.common.core.tools.JsonUtil;
import com.roncoo.education.user.service.api.biz.ApiUsersBiz;
import com.roncoo.education.user.service.api.req.WxCodeReq;
import com.roncoo.education.user.service.api.req.WxLoginReq;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.annotation.Resource;

public class LoginTest extends BaseTest {

    @Resource
    private ApiUsersBiz apiUsersBiz;

    @Test
    public void wxLogin() throws Exception {
        WxLoginReq req = new WxLoginReq();
        req.setLoginAuthType(LoginAuthTypeEnum.PC.getCode());
        req.setRedirectUrl("https://dev-os.roncoos.com/login");
        mvc.perform(MockMvcRequestBuilders.post("/user/api/users/wx/login").content(JsonUtil.toJsonString(req)));
    }

    @Test
    public void wxCode() throws Exception {
        WxCodeReq req = new WxCodeReq();
        req.setLoginAuthType(LoginAuthTypeEnum.PC.getCode());
        req.setClientType(ClientTypeEnum.PC.getCode());
        req.setCode("222");
        apiUsersBiz.wxCode(req);
    }

}
