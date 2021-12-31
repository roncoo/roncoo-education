package com.roncoo.education.system.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.system.service.api.biz.ApiSysMenuBiz;
import com.roncoo.education.common.core.base.BaseController;

/**
 * 菜单信息
 *
 * @author wujing
 */
@RestController
public class ApiSysMenuController extends BaseController {

	@Autowired
	private ApiSysMenuBiz biz;

}
