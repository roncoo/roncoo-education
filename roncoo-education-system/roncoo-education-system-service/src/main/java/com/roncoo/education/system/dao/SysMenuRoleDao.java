package com.roncoo.education.system.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.system.dao.impl.mapper.entity.SysMenuRole;
import com.roncoo.education.system.dao.impl.mapper.entity.SysMenuRoleExample;

import java.util.List;

public interface SysMenuRoleDao {
    int save(SysMenuRole record);

    int deleteById(Long id);

    int updateById(SysMenuRole record);

    int updateByExampleSelective(SysMenuRole record, SysMenuRoleExample example);

    SysMenuRole getById(Long id);

    Page<SysMenuRole> listForPage(int pageCurrent, int pageSize, SysMenuRoleExample example);

    /**
     * 根据角色ID列出角色下所有的菜单
     *
     * @param roleId
     * @return
     */
    List<SysMenuRole> listByRoleId(Long roleId);

    /**
     * 删除角色下的菜单
     *
     * @param roleId
     */
    int deleteByRoleId(Long roleId);
}
