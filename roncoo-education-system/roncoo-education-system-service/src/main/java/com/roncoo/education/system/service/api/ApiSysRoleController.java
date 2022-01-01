package com.roncoo.education.system.service.api;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.system.service.api.biz.ApiSysRoleBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色信息
 *
 * @author wujing
 */
@RestController
public class ApiSysRoleController extends BaseController {

    @Autowired
    private ApiSysRoleBiz biz;

}
