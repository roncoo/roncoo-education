package com.roncoo.education.system.service.controller.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.system.common.bean.dto.WebsiteLinkListDTO;
import com.roncoo.education.system.common.interfaces.gateway.ApiWebsiteLink;
import com.roncoo.education.system.service.biz.gateway.ApiWebsiteLinkBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;

/**
 * 站点友情链接
 *
 * @author wuyun
 */
@RestController
public class ApiWebsiteLinkController extends BaseController implements ApiWebsiteLink {

	@Autowired
	private ApiWebsiteLinkBiz biz;

	@Override
	public Result<WebsiteLinkListDTO> list() {
		return biz.list();
	}

}
