package com.roncoo.education.system.service.controller.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.system.common.bean.dto.WebsiteNavListDTO;
import com.roncoo.education.system.common.interfaces.gateway.ApiWebsiteNav;
import com.roncoo.education.system.service.biz.gateway.ApiWebsiteNavBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;

/**
 * 站点导航
 *
 * @author wuyun
 */
@RestController
public class ApiWebsiteNavController extends BaseController implements ApiWebsiteNav {

	@Autowired
	private ApiWebsiteNavBiz biz;

	@Override
	public Result<WebsiteNavListDTO> list() {
		return biz.list();
	}

}
