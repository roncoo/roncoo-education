package com.roncoo.education.system.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.system.service.api.biz.ApiSysRoleBiz;
import com.roncoo.education.util.base.BaseController;

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
