/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.system.service.biz;

import cn.hutool.core.collection.CollectionUtil;
import com.roncoo.education.common.core.enums.MenuTypeEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.system.dao.SysMenuDao;
import com.roncoo.education.system.dao.SysMenuRoleDao;
import com.roncoo.education.system.dao.SysRoleUserDao;
import com.roncoo.education.system.dao.impl.mapper.entity.SysMenu;
import com.roncoo.education.system.dao.impl.mapper.entity.SysMenuRole;
import com.roncoo.education.system.dao.impl.mapper.entity.SysRoleUser;
import com.roncoo.education.system.service.admin.resp.AdminSysMenuUserResp;
import com.roncoo.education.system.service.admin.resp.AdminSysUserLoginRouterResp;
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

    public List<SysMenu> listMenu(Long userId) {
        List<SysRoleUser> roleUsers = sysRoleUserDao.listByUserId(userId);
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

    /**
     * 获取路由
     *
     * @param menuList
     * @return
     */
    public List<AdminSysUserLoginRouterResp> routerList(List<SysMenu> menuList) {
        menuList = menuList.stream().filter(item -> item.getMenuType().equals(MenuTypeEnum.MENU.getCode())).collect(Collectors.toList());
        return BeanUtil.copyProperties(menuList, AdminSysUserLoginRouterResp.class);
    }

    /**
     * 获取菜单
     * @param menuList
     * @return
     */
    public List<AdminSysMenuUserResp> menuList(List<SysMenu> menuList) {
        return filters(0L, menuList);
    }

    /**
     * 菜单层级处理
     */
    private List<AdminSysMenuUserResp> filters(Long parentId, List<SysMenu> menuList) {
        List<SysMenu> sysMenuList = menuList.stream().filter(item -> parentId.compareTo(item.getParentId()) == 0 && !item.getMenuType().equals(MenuTypeEnum.PERMISSION.getCode())).collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(sysMenuList)) {
            List<AdminSysMenuUserResp> respList = BeanUtil.copyProperties(sysMenuList, AdminSysMenuUserResp.class);
            for (AdminSysMenuUserResp resp : respList) {
                resp.setChildren(filters(resp.getId(), menuList));
            }
            return respList;
        }
        return null;
    }


}
