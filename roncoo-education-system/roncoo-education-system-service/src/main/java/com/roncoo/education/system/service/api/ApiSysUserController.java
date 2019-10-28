package com.roncoo.education.system.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.system.service.api.biz.ApiSysUserBiz;
import com.roncoo.education.util.base.BaseController;

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
