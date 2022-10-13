package com.roncoo.education.system.service.admin.biz;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.config.ThreadContext;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.MenuTypeEnum;
import com.roncoo.education.common.core.enums.ResultEnum;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.system.dao.SysMenuDao;
import com.roncoo.education.system.dao.SysMenuRoleDao;
import com.roncoo.education.system.dao.SysRoleUserDao;
import com.roncoo.education.system.dao.SysUserDao;
import com.roncoo.education.system.dao.impl.mapper.entity.SysMenu;
import com.roncoo.education.system.dao.impl.mapper.entity.SysMenuExample;
import com.roncoo.education.system.dao.impl.mapper.entity.SysMenuRole;
import com.roncoo.education.system.dao.impl.mapper.entity.SysRoleUser;
import com.roncoo.education.system.service.admin.req.*;
import com.roncoo.education.system.service.admin.resp.AdminSysMenuResp;
import com.roncoo.education.system.service.admin.resp.AdminSysMenuUserResp;
import com.roncoo.education.system.service.admin.resp.AdminSysMenuViewResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单信息
 *
 * @author wujing
 */
@Component
public class AdminSysMenuBiz {

    @Autowired
    private SysMenuDao dao;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysRoleUserDao sysRoleUserDao;
    @Autowired
    private SysMenuRoleDao sysMenuRoleDao;

    public Result<List<AdminSysMenuResp>> list(AdminSysMenuListReq req) {
        SysMenuExample example = new SysMenuExample();
        SysMenuExample.Criteria c = example.createCriteria();
        if (StringUtils.hasText(req.getMenuName())) {
            c.andMenuNameEqualTo(req.getMenuName());
        }
        if (ObjectUtil.isNotEmpty(req.getStatusId())) {
            c.andStatusIdEqualTo(req.getStatusId());
        }
        example.setOrderByClause("sort asc, id desc");
        List<SysMenu> sysMenuList = dao.selectByExampleWithBLOBs(example);
        return Result.success(filter(0L, sysMenuList));
    }

    /**
     * 菜单层级处理
     */
    private List<AdminSysMenuResp> filter(Long parentId, List<SysMenu> menuList) {
        List<SysMenu> sysMenuList = menuList.stream().filter(item -> parentId.compareTo(item.getParentId()) == 0).collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(sysMenuList)) {
            List<AdminSysMenuResp> respList = BeanUtil.copyProperties(sysMenuList, AdminSysMenuResp.class);
            for (AdminSysMenuResp resp : respList) {
                resp.setChildrenList(filter(resp.getId(), menuList));
            }
            return respList;
        }
        return null;
    }

    public Result<String> save(AdminSysMenuSaveReq req) {
        SysMenu record = BeanUtil.copyProperties(req, SysMenu.class);
        int results = dao.save(record);
        if (results > 0) {
            return Result.success("操作成功");
        }
        return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
    }

    public Result<String> delete(AdminSysMenuDeleteReq req) {
        if (req.getId() == null) {
            return Result.error("主键ID不能为空");
        }
        // 存在子菜单则不允许删除
        List<SysMenu> list = dao.listByParentId(req.getId());
        if (CollectionUtil.isNotEmpty(list)) {
            return Result.error("请先删除下级菜单");
        }
        int results = dao.deleteById(req.getId());
        if (results > 0) {
            return Result.success("操作成功");
        }
        return Result.error(ResultEnum.SYSTEM_DELETE_FAIL);
    }

    public Result<String> update(AdminSysMenuUpdateReq req) {
        if (req.getId() == null) {
            return Result.error("主键ID不能为空");
        }
        SysMenu sysMenu = dao.getById(req.getId());
        if (ObjectUtil.isNull(sysMenu)) {
            return Result.error("找不到菜单信息");
        }
        SysMenu record = BeanUtil.copyProperties(req, SysMenu.class);
        int results = dao.updateById(record);
        if (results > 0) {
            return Result.success("操作成功");
        }
        return Result.error(ResultEnum.SYSTEM_UPDATE_FAIL);
    }

    public Result<AdminSysMenuViewResp> view(AdminSysMenuViewReq req) {
        if (req.getId() == null) {
            return Result.error("主键ID不能为空");
        }
        SysMenu record = dao.getById(req.getId());
        if (ObjectUtil.isNull(record)) {
            return Result.error("找不到菜单信息");
        }
        return Result.success(BeanUtil.copyProperties(record, AdminSysMenuViewResp.class));
    }

    public Result<List<AdminSysMenuUserResp>> userList(AdminSysMenuUserListReq req) {
        if (ObjectUtil.isEmpty(req.getUserId())) {
            req.setUserId(ThreadContext.userId());
        }
        List<SysRoleUser> sysRoleUserList = sysRoleUserDao.listByUserId(req.getUserId());
        if (CollectionUtil.isEmpty(sysRoleUserList)) {
            return Result.success(new ArrayList<>());
        }
        // 用户的所有角色
        List<Long> roleList = sysRoleUserList.stream().map(SysRoleUser::getRoleId).collect(Collectors.toList());

        List<SysMenuRole> menuRoleList = sysMenuRoleDao.listByRoleIds(roleList);
        if (CollectionUtil.isEmpty(menuRoleList)) {
            return Result.success(new ArrayList<>());
        }
        // 用户的所有菜单
        List<Long> menuList = menuRoleList.stream().map(SysMenuRole::getMenuId).collect(Collectors.toList());
        SysMenuExample example = new SysMenuExample();
        SysMenuExample.Criteria c = example.createCriteria();
        c.andIdIn(menuList);
        c.andStatusIdEqualTo(StatusIdEnum.YES.getCode());
        // 只需要获取目录和菜单
        c.andMenuTypeLessThan(MenuTypeEnum.PERMISSION.getCode());
        example.setOrderByClause("sort asc, id desc");
        List<SysMenu> sysMenuList = dao.listByExample(example);
        return Result.success(filters(0L, sysMenuList));
    }

    /**
     * 菜单层级处理
     */
    private List<AdminSysMenuUserResp> filters(Long parentId, List<SysMenu> menuList) {
        List<SysMenu> sysMenuList = menuList.stream().filter(item -> parentId.compareTo(item.getParentId()) == 0).collect(Collectors.toList());
        if (CollectionUtil.isNotEmpty(sysMenuList)) {
            List<AdminSysMenuUserResp> respList = copys(sysMenuList);
            for (AdminSysMenuUserResp resp : respList) {
                resp.setChildren(filters(resp.getId(), menuList));
            }
            return respList;
        }
        return null;
    }

    private List<AdminSysMenuUserResp> copys(List<SysMenu> sysMenuList) {
        List<AdminSysMenuUserResp> respList = new ArrayList<>();
        for (SysMenu sysMenu : sysMenuList) {
            AdminSysMenuUserResp resp = new AdminSysMenuUserResp();
            resp.setId(sysMenu.getId());
            resp.setName(sysMenu.getMenuName());
            resp.setNameEn(sysMenu.getMenuName());
            resp.setMenuType(sysMenu.getMenuType());
            resp.setPath(sysMenu.getMenuUrl());
            resp.setSort(sysMenu.getSort());
            resp.setTargetName(sysMenu.getMenuIcon());
            respList.add(resp);
        }
        return respList;
    }

    public Result<List<String>> permissionList() {
        List<SysRoleUser> sysRoleUserList = sysRoleUserDao.listByUserId(ThreadContext.userId());
        if (CollectionUtil.isEmpty(sysRoleUserList)) {
            return Result.success(new ArrayList<>());
        }
        // 用户的所有角色
        List<Long> roleList = sysRoleUserList.stream().map(SysRoleUser::getRoleId).collect(Collectors.toList());

        List<SysMenuRole> menuRoleList = sysMenuRoleDao.listByRoleIds(roleList);
        if (CollectionUtil.isEmpty(menuRoleList)) {
            return Result.success(new ArrayList<>());
        }
        // 用户的所有菜单
        List<Long> menuList = menuRoleList.stream().map(SysMenuRole::getMenuId).collect(Collectors.toList());
        SysMenuExample example = new SysMenuExample();
        SysMenuExample.Criteria c = example.createCriteria();
        c.andIdIn(menuList);
        c.andStatusIdEqualTo(StatusIdEnum.YES.getCode());
        // 只需要获取权限
        c.andMenuTypeEqualTo(MenuTypeEnum.PERMISSION.getCode());
        example.setOrderByClause("sort asc, id desc");
        List<SysMenu> sysMenuList = dao.selectByExampleWithBLOBs(example);
        return Result.success(sysMenuList.stream().map(SysMenu::getAuthValue).collect(Collectors.toList()));
    }
}
