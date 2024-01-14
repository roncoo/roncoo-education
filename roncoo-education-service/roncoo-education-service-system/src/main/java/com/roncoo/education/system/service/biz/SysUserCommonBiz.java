/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.system.service.biz;

import cn.hutool.core.collection.CollectionUtil;
import com.roncoo.education.system.dao.SysMenuDao;
import com.roncoo.education.system.dao.SysMenuRoleDao;
import com.roncoo.education.system.dao.SysRoleUserDao;
import com.roncoo.education.system.dao.impl.mapper.entity.SysMenu;
import com.roncoo.education.system.dao.impl.mapper.entity.SysMenuRole;
import com.roncoo.education.system.dao.impl.mapper.entity.SysRoleUser;
import com.roncoo.education.system.dao.impl.mapper.entity.SysUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 上传接口
 *
 * @author wuyun
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class SysUserCommonBiz {

    private final SysRoleUserDao sysRoleUserDao;
    private final SysMenuRoleDao sysMenuRoleDao;
    private final SysMenuDao sysMenuDao;

    public List<SysMenu> listMenu(SysUser sysUser) {
        List<SysRoleUser> roleUsers = sysRoleUserDao.listByUserId(sysUser.getId());
        if (CollectionUtil.isEmpty(roleUsers)) {
            return new ArrayList<>();
        }
        List<SysMenuRole> menuRoles = sysMenuRoleDao.listByRoleIds(roleUsers.stream().map(SysRoleUser::getRoleId).collect(Collectors.toList()));
        if (CollectionUtil.isEmpty(menuRoles)) {
            return new ArrayList<>();
        }
        List<SysMenu> sysMenus = sysMenuDao.getByIds(menuRoles.stream().map(SysMenuRole::getMenuId).collect(Collectors.toList()));
        if (CollectionUtil.isEmpty(sysMenus)) {
            return new ArrayList<>();
        }
        return sysMenus;
    }

}
