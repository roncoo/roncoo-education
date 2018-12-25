package com.roncoo.education.user.service.controller.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.user.common.bean.bo.UserRegionCityIdBO;
import com.roncoo.education.user.common.bean.bo.UserRegionLevelBO;
import com.roncoo.education.user.common.bean.bo.UserRegionProvinceBO;
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
	public Result<RegionListDTO> listForLevel(@RequestBody UserRegionLevelBO userRegionLevelBO) {
		return biz.listForLevel(userRegionLevelBO);
	}

	@Override
	public Result<RegionListDTO> listForProvince(@RequestBody UserRegionProvinceBO userRegionProvinceBO) {
		return biz.listForProvince(userRegionProvinceBO);
	}

	@Override
	public Result<RegionListDTO> listForCity(@RequestBody UserRegionCityIdBO userRegionCityIdBO) {
		return biz.listForCity(userRegionCityIdBO);
	}

}
