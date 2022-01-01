package com.roncoo.education.course.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.dao.impl.mapper.entity.Zone;
import com.roncoo.education.course.dao.impl.mapper.entity.ZoneExample;

import java.util.List;

public interface ZoneDao {
    int save(Zone record);

    int deleteById(Long id);

    int updateById(Zone record);

    Zone getById(Long id);

    Page<Zone> listForPage(int pageCurrent, int pageSize, ZoneExample example);

    List<Zone> listAllZone();
}
