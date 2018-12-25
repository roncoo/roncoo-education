package com.roncoo.education.user.common.interfaces.gateway;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.user.common.bean.bo.UserRegionCityIdBO;
import com.roncoo.education.user.common.bean.bo.UserRegionLevelBO;
import com.roncoo.education.user.common.bean.bo.UserRegionProvinceBO;
import com.roncoo.education.user.common.bean.dto.RegionListDTO;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 行政区域表
 *
 * @author wujing
 */
public interface ApiRegion {

	/**
	 * 区域列出接口
	 */
	@ApiOperation(value = "区域列出接口", notes = "根据级别获取区域列出信息")
	@RequestMapping(value = "/user/api/region/list", method = RequestMethod.POST)
	Result<RegionListDTO> listForLevel(@RequestBody UserRegionLevelBO userRegionBO);

	/**
	 * 区域列出接口
	 */
	@ApiOperation(value = "区域列出接口", notes = "根据provinceId获取区域列表信息")
	@RequestMapping(value = "/user/api/region/list/province", method = RequestMethod.POST)
	Result<RegionListDTO> listForProvince(@RequestBody UserRegionProvinceBO userRegionProvinceBO);

	/**
	 * 区域列出接口
	 */
	@ApiOperation(value = "区域列出接口", notes = "根据cityId获取区域列表信息")
	@RequestMapping(value = "/user/api/region/list/city", method = RequestMethod.POST)
	Result<RegionListDTO> listForCity(@RequestBody UserRegionCityIdBO userRegionCityIdBO);

}
