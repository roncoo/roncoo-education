package com.roncoo.education.user.service.api.biz;

import cn.hutool.core.collection.CollectionUtil;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.user.dao.RegionDao;
import com.roncoo.education.user.dao.impl.mapper.entity.Region;
import com.roncoo.education.user.service.api.req.RegionCityIdReq;
import com.roncoo.education.user.service.api.req.RegionLevelReq;
import com.roncoo.education.user.service.api.req.RegionProvinceReq;
import com.roncoo.education.user.service.api.resp.RegionResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 行政区域表
 *
 * @author wujing
 */
@Component
@CacheConfig(cacheNames = {"user"})
public class ApiRegionBiz {

    @Autowired
    private RegionDao regionDao;

    @Cacheable
    public Result<List<RegionResp>> listForLevel(RegionLevelReq userRegionLevelBO) {
        List<Region> list = regionDao.listByLevel(userRegionLevelBO.getLevel());
        if (CollectionUtil.isNotEmpty(list)) {
            return Result.success(PageUtil.copyList(list, RegionResp.class));
        }
        return Result.error("找不到信息");
    }

    @Cacheable
    public Result<List<RegionResp>> listForProvince(RegionProvinceReq userRegionProvinceBO) {
        List<Region> list = regionDao.listByProvinceId(userRegionProvinceBO.getProvinceId());
        if (CollectionUtil.isNotEmpty(list)) {
            return Result.success(PageUtil.copyList(list, RegionResp.class));
        }
        return Result.error("找不到信息");
    }

    @Cacheable
    public Result<List<RegionResp>> listForCity(RegionCityIdReq userRegionCityIdBO) {
        List<Region> list = regionDao.listByCityId(userRegionCityIdBO.getCityId());
        if (CollectionUtil.isNotEmpty(list)) {
            return Result.success(PageUtil.copyList(list, RegionResp.class));
        }
        return Result.error("找不到信息");
    }

}
