package com.roncoo.education.system.common.interfaces.gateway;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.system.common.bean.bo.WebsiteRecruitBO;
import com.roncoo.education.system.common.bean.dto.WebsiteRecruitDTO;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 招募中心
 *
 * @author YZJ
 */
public interface ApiWebsiteRecruit {

	/**
	 * 获取讲师招募信息接口
	 * 
	 * @return
	 * @author YZJ
	 */
	@ApiOperation(value = "获取招募信息接口", notes = "获取招募信息")
	@RequestMapping(value = "/system/api/website/recruit/get", method = RequestMethod.POST)
	Result<WebsiteRecruitDTO> get(@RequestBody WebsiteRecruitBO websiteRecruitBO);
}