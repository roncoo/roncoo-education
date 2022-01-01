package com.roncoo.education.system.service.api;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.system.service.api.biz.ApiSysUserBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 后台用户信息
 *
 * @author wujing
 */
@RestController
public class ApiSysUserController extends BaseController {

    @Autowired
    private ApiSysUserBiz biz;

}
