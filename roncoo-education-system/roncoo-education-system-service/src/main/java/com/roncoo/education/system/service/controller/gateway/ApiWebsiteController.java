package com.roncoo.education.system.service.controller.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.system.common.bean.dto.WebsiteDTO;
import com.roncoo.education.system.common.interfaces.gateway.ApiWebsite;
import com.roncoo.education.system.service.biz.gateway.ApiWebsiteBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;

/**
 * 站点信息
 *
 * @author wuyun
 */
@RestController
public class ApiWebsiteController extends BaseController implements ApiWebsite {

	@Autowired
	private ApiWebsiteBiz biz;

	@Override
	public Result<WebsiteDTO> get() {
		return biz.get();
	}

}
