package com.roncoo.education.system.common.interfaces.gateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.system.common.bean.dto.WebsiteLinkListDTO;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 站点友情链接 
 *
 * @author wuyun
 */
public interface ApiWebsiteLink {
    
	/**
	 * 获取站点友情链接接口
	 * 
	 * @return 站点友情链接
	 * @author wuyun
	 */
	@ApiOperation(value = "获取站点友情链接接口", notes = "获取站点友情链接")
	@RequestMapping(value = "/system/api/website/link", method = RequestMethod.POST)
	Result<WebsiteLinkListDTO> list();
}
