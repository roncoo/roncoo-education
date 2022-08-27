package com.roncoo.education.system.service.admin.biz;

import cn.hutool.core.collection.CollectionUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.system.dao.SysMenuDao;
import com.roncoo.education.system.dao.SysMenuRoleDao;
import com.roncoo.education.system.dao.impl.mapper.entity.SysMenuRole;
import com.roncoo.education.system.service.admin.req.AdminSysMenuRoleListReq;
import com.roncoo.education.system.service.admin.req.AdminSysMenuRoleSaveReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单角色关联表
 *
 * @author wujing
 */
@Component
public class AdminSysMenuRoleBiz {

    @Autowired
    private SysMenuRoleDao dao;
    @Autowired
    private SysMenuDao sysMenuDao;

    /**
     * 列出菜单角色关联信息接口
     *
     * @param req
     * @return
     */
    public Result<List<String>> list(AdminSysMenuRoleListReq req) {
        if (req.getRoleId() == null) {
            return Result.error("角色ID不能为空");
        }
        List<SysMenuRole> list = dao.listByRoleId(req.getRoleId());
        List<String> roleIdList = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(list)) {
            for (SysMenuRole sysMenuRole : list) {
                roleIdList.add(String.valueOf(sysMenuRole.getMenuId()));
            }

        }
        return Result.success(roleIdList);
    }

    @Transactional
    public Result<Integer> save(AdminSysMenuRoleSaveReq req) {
        if (req.getRoleId() == null) {
            return Result.error("角色ID不能为空");
        }
        if (CollectionUtil.isNotEmpty(req.getMenuId())) {
            // 先删除角色下所有的关联菜单
            dao.deleteByRoleId(req.getRoleId());
            for (Long menuId : req.getMenuId()) {
                SysMenuRole entity = new SysMenuRole();
                entity.setMenuId(menuId);
                entity.setRoleId(req.getRoleId());
                dao.save(entity);
            }
        }
        return Result.success(1);
    }

}
