package com.roncoo.education.system.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.system.service.api.biz.ApiSysMenuRoleBiz;
import com.roncoo.education.util.base.BaseController;

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
