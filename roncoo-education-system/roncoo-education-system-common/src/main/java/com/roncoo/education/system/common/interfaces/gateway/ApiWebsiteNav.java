package com.roncoo.education.system.common.interfaces.gateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.system.common.bean.dto.WebsiteNavListDTO;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 站点导航
 *
 * @author wuyun
 */
public interface ApiWebsiteNav {

	/**
	 * 获取站点导航信息接口
	 * 
	 * @return
	 * @author wuyun
	 */
	@ApiOperation(value = "获取站点导航信息接口", notes = "获取站点导航信息")
	@RequestMapping(value = "/system/api/website/nav/list", method = RequestMethod.POST)
	Result<WebsiteNavListDTO> list();
}
