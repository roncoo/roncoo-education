/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.user.test.service;

import com.roncoo.education.common.core.enums.LoginAuthTypeEnum;
import com.roncoo.education.common.core.tools.JsonUtil;
import com.roncoo.education.user.service.api.req.WxLoginReq;
import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

public class LoginTest extends BaseTest {

    @Test
    public void wxLogin() throws Exception {
        WxLoginReq req = new WxLoginReq();
        req.setLoginAuthType(LoginAuthTypeEnum.PC.getCode());
        req.setRedirectUrl("https://dev-os.roncoos.com/login");
        mvc.perform(MockMvcRequestBuilders.post("/user/api/users/wx/login").content(JsonUtil.toJsonString(req)));
    }

}
