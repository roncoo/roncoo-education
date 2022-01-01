package com.roncoo.education.system.service.api;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.system.service.api.biz.ApiSysMenuRoleBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单角色关联表
 *
 * @author wujing
 */
@RestController
public class ApiSysMenuRoleController extends BaseController {

    @Autowired
    private ApiSysMenuRoleBiz biz;

}
