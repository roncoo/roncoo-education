package com.roncoo.education.system.common.interfaces.gateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.system.common.bean.dto.WebsiteDTO;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 站点信息
 *
 * @author wuyun
 */
public interface ApiWebsite {

	/**
	 * 获取站点信息接口
	 * 
	 * @return 站点信息
	 * @author wuyun
	 */
	@ApiOperation(value = "获取站点信息接口", notes = "获取站点信息")
	@RequestMapping(value = "/system/api/website/get", method = RequestMethod.POST)
	Result<WebsiteDTO> get();
}
