package com.roncoo.education.system.common.interfaces.gateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.system.common.bean.dto.NavBarListDTO;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 头部导航 
 *
 * @author wuyun
 */
public interface ApiNavBar {
    
	/**
	 * 获取头部导航信息接口
	 * 
	 * @return 站点信息
	 * @author wuyun
	 */
	@ApiOperation(value = "获取头部导航信息接口", notes = "获取头部导航信息")
	@RequestMapping(value = "/system/api/nav/bar/list", method = RequestMethod.POST)
	Result<NavBarListDTO> list();
}
