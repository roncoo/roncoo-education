package com.roncoo.education.system.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.system.service.api.biz.ApiWebsiteNavBiz;
import com.roncoo.education.system.common.dto.WebsiteNavListDTO;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 站点导航
 *
 * @author wuyun
 */
@RestController
public class ApiWebsiteNavController extends BaseController {

	@Autowired
	private ApiWebsiteNavBiz biz;

	/**
	 * 获取站点导航信息接口
	 *
	 * @return
	 * @author wuyun
	 */
	@ApiOperation(value = "获取站点导航信息接口", notes = "获取站点导航信息")
	@RequestMapping(value = "/system/api/website/nav/list", method = RequestMethod.POST)
	public Result<WebsiteNavListDTO> list() {
		return biz.list();
	}

}
