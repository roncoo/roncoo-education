package com.roncoo.education.user.service.biz.gateway;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.user.common.bean.dto.RegionDTO;
import com.roncoo.education.user.common.bean.dto.RegionListDTO;
import com.roncoo.education.user.service.dao.RegionDao;
import com.roncoo.education.user.service.dao.impl.mapper.entity.Region;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import com.xiaoleilu.hutool.util.CollectionUtil;

/**
 * 行政区域表
 *
 * @author wujing
 */
@Component
public class ApiRegionBiz {

	@Autowired
	private RegionDao regionDao;

	public Result<RegionListDTO> listForLevel(Integer level) {
		List<Region> list = regionDao.listByLevel(level);
		if (CollectionUtil.isNotEmpty(list)) {
			RegionListDTO data = new RegionListDTO();
			data.setRegionList(PageUtil.copyList(list, RegionDTO.class));
			return Result.success(data);
		}
		return Result.error("找不到信息");
	}

	public Result<RegionListDTO> listForProvince(Integer provinceId) {
		List<Region> list = regionDao.listByProvinceId(provinceId);
		if (CollectionUtil.isNotEmpty(list)) {
			RegionListDTO data = new RegionListDTO();
			data.setRegionList(PageUtil.copyList(list, RegionDTO.class));
			return Result.success(data);
		}
		return Result.error("找不到信息");
	}

	public Result<RegionListDTO> listForCity(Integer cityId) {
		List<Region> list = regionDao.listByCityId(cityId);
		if (CollectionUtil.isNotEmpty(list)) {
			RegionListDTO data = new RegionListDTO();
			data.setRegionList(PageUtil.copyList(list, RegionDTO.class));
			return Result.success(data);
		}
		return Result.error("找不到信息");
	}

}
