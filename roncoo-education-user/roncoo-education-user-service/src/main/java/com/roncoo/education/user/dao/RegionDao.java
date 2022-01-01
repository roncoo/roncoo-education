package com.roncoo.education.user.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.dao.impl.mapper.entity.Region;
import com.roncoo.education.user.dao.impl.mapper.entity.RegionExample;

import java.util.List;

public interface RegionDao {
    int save(Region record);

    int deleteById(Long id);

    int updateById(Region record);

    Region getById(Long id);

    Page<Region> listForPage(int pageCurrent, int pageSize, RegionExample example);

    List<Region> listByLevel(Integer level);

    List<Region> listByProvinceId(Integer provinceId);

    List<Region> listByCityId(Integer cityId);
}
