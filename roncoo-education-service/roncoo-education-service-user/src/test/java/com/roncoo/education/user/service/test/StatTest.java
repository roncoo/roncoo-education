/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.user.service.test;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.tools.JSUtil;
import com.roncoo.education.user.service.admin.biz.AdminStatBiz;
import com.roncoo.education.user.service.admin.resp.AdminStatLoginResp;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class StatTest extends BaseTest {

    @Autowired
    private AdminStatBiz adminStatBiz;

    @Test
    public void stat() {
        Result<AdminStatLoginResp> result = adminStatBiz.statLogin(-8);
        System.out.println(JSUtil.toJsonString(result.getData()));
    }

}
