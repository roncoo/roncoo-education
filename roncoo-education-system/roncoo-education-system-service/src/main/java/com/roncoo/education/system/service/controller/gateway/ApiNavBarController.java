package com.roncoo.education.system.service.controller.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.system.common.bean.dto.NavBarListDTO;
import com.roncoo.education.system.common.interfaces.gateway.ApiNavBar;
import com.roncoo.education.system.service.biz.gateway.ApiNavBarBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;

/**
 * 头部导航
 *
 * @author wuyun
 */
@RestController
public class ApiNavBarController extends BaseController implements ApiNavBar {

	@Autowired
	private ApiNavBarBiz biz;

	@Override
	public Result<NavBarListDTO> list() {
		return biz.list();
	}

}
