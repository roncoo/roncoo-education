package com.roncoo.education.system.service.dao;

import com.roncoo.education.system.service.dao.impl.mapper.entity.SysMenu;
import com.roncoo.education.system.service.dao.impl.mapper.entity.SysMenuExample;
import com.roncoo.education.util.base.Page;

public interface SysMenuDao {
    int save(SysMenu record);

    int deleteById(Long id);

    int updateById(SysMenu record);

    int updateByExampleSelective(SysMenu record, SysMenuExample example);

    SysMenu getById(Long id);

    Page<SysMenu> listForPage(int pageCurrent, int pageSize, SysMenuExample example);
}