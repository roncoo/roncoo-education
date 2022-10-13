package com.roncoo.education.system.service.admin.biz;

import cn.hutool.core.collection.CollectionUtil;
import com.roncoo.education.common.core.base.Result;
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

    /**
     * 列出菜单角色关联信息接口
     *
     * @param req
     * @return
     */
    public Result<List<Long>> list(AdminSysMenuRoleListReq req) {
        List<SysMenuRole> menuRoleList = dao.listByRoleId(req.getRoleId());
        if (CollectionUtil.isNotEmpty(menuRoleList)) {
            List<Long> roleIdList = new ArrayList<>();
            for (SysMenuRole sysMenuRole : menuRoleList) {
                roleIdList.add(sysMenuRole.getMenuId());
            }
            return Result.success(roleIdList);
        }
        return Result.success(new ArrayList<>());
    }

    @Transactional(rollbackFor = Exception.class)
    public Result<String> save(AdminSysMenuRoleSaveReq req) {
        if (req.getRoleId() == null) {
            return Result.error("角色ID不能为空");
        }
        if (CollectionUtil.isNotEmpty(req.getMenuIdList())) {
            // 先删除该角色下所有的菜单
            dao.deleteByRoleId(req.getRoleId());
            // 再保存新的菜单
            for (Long menuId : req.getMenuIdList()) {
                SysMenuRole entity = new SysMenuRole();
                entity.setMenuId(menuId);
                entity.setRoleId(req.getRoleId());
                dao.save(entity);
            }
        }
        return Result.success("操作成功");
    }

}
