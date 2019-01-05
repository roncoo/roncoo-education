package com.roncoo.education.system.service.controller.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.system.common.bean.bo.WebsiteRecruitBO;
import com.roncoo.education.system.common.bean.dto.WebsiteRecruitDTO;
import com.roncoo.education.system.common.interfaces.gateway.ApiWebsiteRecruit;
import com.roncoo.education.system.service.biz.gateway.ApiWebsiteRecruitBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;

/**
 * 招募中心
 *
 * @author YZJ
 */
@RestController
public class ApiWebsiteRecruitController extends BaseController implements ApiWebsiteRecruit {

	@Autowired
	private ApiWebsiteRecruitBiz biz;

	@Override
	public Result<WebsiteRecruitDTO> get(@RequestBody WebsiteRecruitBO websiteRecruitBO) {
		return biz.get(websiteRecruitBO);
	}

}
