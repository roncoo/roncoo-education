package com.roncoo.education.system.common.interfaces.gateway;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.system.common.bean.bo.WebsiteNavArticleBO;
import com.roncoo.education.system.common.bean.dto.WebsiteNavArticleDTO;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 站点导航文章
 *
 * @author wuyun
 */
public interface ApiWebsiteNavArticle {

	/**
	 * 获取站点导航文章接口
	 * 
	 * @return
	 * @author wuyun
	 */
	@ApiOperation(value = "获取站点导航文章接口", notes = "根据站点导航ID获取站点导航文章信息")
	@RequestMapping(value = "/system/api/website/nav/article/get", method = RequestMethod.POST)
	Result<WebsiteNavArticleDTO> get(@RequestBody WebsiteNavArticleBO websiteNavArticle);
}
