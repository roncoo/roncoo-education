package com.roncoo.education.user.service.controller.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.user.common.bean.dto.RegionListDTO;
import com.roncoo.education.user.common.interfaces.gateway.ApiRegion;
import com.roncoo.education.user.service.biz.gateway.ApiRegionBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;

/**
 * 行政区域表
 *
 * @author wujing
 */
@RestController
public class ApiRegionController extends BaseController implements ApiRegion {

	@Autowired
	private ApiRegionBiz biz;

	@Override
	public Result<RegionListDTO> listForLevel(@PathVariable(value = "level") Integer level) {
		return biz.listForLevel(level);
	}

	@Override
	public Result<RegionListDTO> listForProvince(@PathVariable(value = "provinceId") Integer provinceId) {
		return biz.listForProvince(provinceId);
	}

	@Override
	public Result<RegionListDTO> listForCity(@PathVariable(value = "cityId") Integer cityId) {
		return biz.listForCity(cityId);
	}

}
