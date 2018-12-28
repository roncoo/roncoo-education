package com.roncoo.education.course.service.controller.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.course.common.bean.bo.ZoneBO;
import com.roncoo.education.course.common.bean.dto.ZoneDTO;
import com.roncoo.education.course.common.interfaces.gateway.ApiZone;
import com.roncoo.education.course.service.biz.gateway.ApiZoneBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;

/**
 * 专区课程关联表
 *
 * @author wuyun
 */
@RestController
public class ApiZoneController extends BaseController implements ApiZone {

	@Autowired
	private ApiZoneBiz biz;

	@Override
	public Result<Page<ZoneDTO>> listForPage(@RequestBody ZoneBO zoneBO) {
		return biz.listForPage(zoneBO);
	}

}
