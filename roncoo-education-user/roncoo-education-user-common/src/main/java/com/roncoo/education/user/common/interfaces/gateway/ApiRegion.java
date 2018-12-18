package com.roncoo.education.user.common.interfaces.gateway;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	@RequestMapping(value = "/user/api/region/list/{level}", method = RequestMethod.POST)
	Result<RegionListDTO> listForLevel(@PathVariable(value = "level") Integer level);

	/**
	 * 区域列出接口
	 */
	@ApiOperation(value = "区域列出接口", notes = "根据provinceId获取区域列表信息")
	@RequestMapping(value = "/user/api/region/list/province/{provinceId}", method = RequestMethod.POST)
	Result<RegionListDTO> listForProvince(@PathVariable(value = "provinceId") Integer provinceId);

	/**
	 * 区域列出接口
	 */
	@ApiOperation(value = "区域列出接口", notes = "根据cityId获取区域列表信息")
	@RequestMapping(value = "/user/api/region/list/city/{cityId}", method = RequestMethod.POST)
	Result<RegionListDTO> listForCity(@PathVariable(value = "cityId") Integer cityId);

}
