package com.roncoo.education.system.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.dao.impl.mapper.entity.SysMenu;
import com.roncoo.education.system.dao.impl.mapper.entity.SysMenuExample;

import java.util.List;

public interface SysMenuDao {
    int save(SysMenu record);

    int deleteById(Long id);

    int updateById(SysMenu record);

    int updateByExampleSelective(SysMenu record, SysMenuExample example);

    SysMenu getById(Long id);

    Page<SysMenu> page(int pageCurrent, int pageSize, SysMenuExample example);

    /**
     * 根据父ID获取菜单
     *
     * @param parentId
     * @return
     */
    List<SysMenu> listByParentId(Long parentId);

    List<SysMenu> getByIds(List<Long> ids);

    List<SysMenu> listByExample(SysMenuExample example);

    List<SysMenu> selectByExampleWithBLOBs(SysMenuExample example);
}
