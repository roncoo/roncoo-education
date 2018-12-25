package com.roncoo.education.system.service.controller.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.system.common.bean.bo.WebsiteNavArticleBO;
import com.roncoo.education.system.common.bean.dto.WebsiteNavArticleDTO;
import com.roncoo.education.system.common.interfaces.gateway.ApiWebsiteNavArticle;
import com.roncoo.education.system.service.biz.gateway.ApiWebsiteNavArticleBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;

/**
 * 站点导航文章 
 *
 * @author wuyun
 */
@RestController
public class ApiWebsiteNavArticleController extends BaseController implements ApiWebsiteNavArticle{

    @Autowired
    private ApiWebsiteNavArticleBiz biz;
    
    @Override
	public Result<WebsiteNavArticleDTO> get(@RequestBody WebsiteNavArticleBO bo) {
		return biz.get(bo);
	}
}
