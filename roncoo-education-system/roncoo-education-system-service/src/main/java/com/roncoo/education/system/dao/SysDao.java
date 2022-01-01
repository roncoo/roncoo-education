package com.roncoo.education.system.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.dao.impl.mapper.entity.Sys;
import com.roncoo.education.system.dao.impl.mapper.entity.SysExample;

public interface SysDao {
    int save(Sys record);

    int deleteById(Long id);

    int updateById(Sys record);

    Sys getById(Long id);

    Page<Sys> listForPage(int pageCurrent, int pageSize, SysExample example);

    /**
     * 获得系统配置表信息
     *
     * @return
     * @author YZJ
     */
    Sys getSys();
}
