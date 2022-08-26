package com.roncoo.education.system.service.admin.biz;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.MenuTypeEnum;
import com.roncoo.education.common.core.enums.ResultEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.system.dao.SysMenuDao;
import com.roncoo.education.system.dao.SysMenuRoleDao;
import com.roncoo.education.system.dao.SysRoleUserDao;
import com.roncoo.education.system.dao.SysUserDao;
import com.roncoo.education.system.dao.impl.mapper.entity.SysMenu;
import com.roncoo.education.system.dao.impl.mapper.entity.SysMenuRole;
import com.roncoo.education.system.dao.impl.mapper.entity.SysRoleUser;
import com.roncoo.education.system.dao.impl.mapper.entity.SysUser;
import com.roncoo.education.system.service.admin.req.*;
import com.roncoo.education.system.service.admin.resp.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

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

    public Result<AdminSysMenuListResp> list(AdminSysMenuListReq req) {
        AdminSysMenuListResp resq = new AdminSysMenuListResp();
        List<AdminSysMenuResp> list = new ArrayList<>();
        if (StringUtils.isEmpty(req.getMenuName())) {
            list = recursion(0L);
        } else {
            // 模糊查询
            List<SysMenu> sysMenuList = dao.listByMenuName(req.getMenuName());
            if (CollectionUtil.isNotEmpty(sysMenuList)) {
                for (SysMenu sysMenu : sysMenuList) {
                    AdminSysMenuResp sysMenuRESQ = BeanUtil.copyProperties(sysMenu, AdminSysMenuResp.class);
                    sysMenuRESQ.setLabel(sysMenu.getMenuName());
                    sysMenuRESQ.setChildren(recursion(sysMenu.getId()));
                    list.add(sysMenuRESQ);
                }
            }
        }

        if (CollectionUtil.isNotEmpty(list)) {
            resq.setSysMenu(list);
        }
        return Result.success(resq);
    }

    /**
     * 递归显示菜单(角色关联菜单)
     */
    private List<AdminSysMenuResp> recursion(Long parentId) {
        List<AdminSysMenuResp> lists = new ArrayList<>();
        List<SysMenu> list = dao.listByParentId(parentId);
        if (CollectionUtil.isNotEmpty(list)) {
            for (SysMenu m : list) {
                AdminSysMenuResp resq = BeanUtil.copyProperties(m, AdminSysMenuResp.class);
                resq.setLabel(resq.getMenuName());
                resq.setChildren(recursion(m.getId()));
                lists.add(resq);
            }
        }
        return lists;
    }

    public Result<Integer> save(AdminSysMenuSaveReq req) {
        SysMenu record = BeanUtil.copyProperties(req, SysMenu.class);
        int results = dao.save(record);
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
    }

    @Transactional
    public Result<Integer> delete(AdminSysMenuDeleteReq req) {
        if (req.getId() == null) {
            return Result.error("主键ID不能为空");
        }
        // 删除菜单,存在子菜单则迭代删除子菜单
        List<SysMenu> list = dao.listByParentId(req.getId());
        if (CollectionUtil.isNotEmpty(list)) {
            for (SysMenu sysMenu : list) {
                dao.deleteById(sysMenu.getId());
            }
        }
        int results = dao.deleteById(req.getId());
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.SYSTEM_DELETE_FAIL);
    }

    public Result<Integer> update(AdminSysMenuUpdateReq req) {
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
            return Result.success(results);
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

    public Result<AdminSysMenuUserListResp> userList(AdminSysMenuUserListReq req) {
        if (req.getUserId() == null) {
            return Result.error("用户编不能为空");
        }
        SysUser sysUser = sysUserDao.getById(req.getUserId());
        if (ObjectUtil.isNull(sysUser)) {
            return Result.error("用户异常");
        }
        AdminSysMenuUserListResp resq = new AdminSysMenuUserListResp();
        List<SysMenuRole> sysMenuRoleList = new ArrayList<>();
        List<SysRoleUser> sysRoleUserList = sysRoleUserDao.listByUserId(sysUser.getId());
        for (SysRoleUser sru : sysRoleUserList) {
            sysMenuRoleList.addAll(sysMenuRoleDao.listByRoleId(sru.getRoleId()));
        }
        // 筛选
        List<AdminSysMenuUserResp> list = listByRole(sysMenuRoleList, MenuTypeEnum.BUTTON.getCode());
        if (CollectionUtil.isNotEmpty(list)) {
            resq.setSysMenu(list);
        }
        return Result.success(resq);
    }

    private List<AdminSysMenuUserResp> listByRole(List<SysMenuRole> sysMenuRoleList, Integer menuType) {
        List<AdminSysMenuUserResp> list = userRecursion(0L, menuType);
        List<AdminSysMenuUserResp> sysMenuUserRESQList = new ArrayList<>();
        sysMenuUserRESQList = listMenu(sysMenuUserRESQList, sysMenuRoleList, list);
        return sysMenuUserRESQList;
    }

    private List<AdminSysMenuUserResp> listMenu(List<AdminSysMenuUserResp> sysMenuVOList, List<SysMenuRole> sysMenuRoleList, List<AdminSysMenuUserResp> list) {
        for (AdminSysMenuUserResp mv : list) {
            AdminSysMenuUserResp v = new AdminSysMenuUserResp();
            for (SysMenuRole vo : sysMenuRoleList) {
                if (mv.getId().equals(vo.getMenuId())) {
                    v = BeanUtil.copyProperties(mv, AdminSysMenuUserResp.class);
                    break;
                }
            }
            if (ObjectUtil.isNotNull(v) && v.getId() != null) {
                sysMenuVOList.add(v);
                List<AdminSysMenuUserResp> l = new ArrayList<>();
                if (v != null) {
                    v.setChildren(l);
                }
                listMenu(l, sysMenuRoleList, mv.getChildren());
            }
        }
        return sysMenuVOList;
    }

    /**
     * 用户递归显示菜单(角色关联菜单)
     */
    private List<AdminSysMenuUserResp> userRecursion(Long parentId, Integer menuType) {
        List<AdminSysMenuUserResp> lists = new ArrayList<>();
        List<SysMenu> list = dao.listByParentIdAndNotMenuType(parentId, menuType);
        if (CollectionUtil.isNotEmpty(list)) {
            for (SysMenu m : list) {
                AdminSysMenuUserResp resq = BeanUtil.copyProperties(m, AdminSysMenuUserResp.class);
                if (m.getMenuName().equals("首页")) {
                    resq.setHidden(true);
                }
                resq.setName(m.getMenuName());
                resq.setPath(m.getMenuUrl());
                resq.setApiUrl(m.getApiUrl());
                resq.setIcon(m.getMenuIcon());
                resq.setChildren(userRecursion(m.getId(), menuType));
                lists.add(resq);
            }
        }
        return lists;
    }

    public Result<AdminSysMenuUserListResp> buttonList(AdminSysMenuUserListReq req) {
        SysUser sysUser = sysUserDao.getById(req.getUserId());
        if (ObjectUtil.isNull(sysUser)) {
            return Result.error("用户异常");
        }
        AdminSysMenuUserListResp resq = new AdminSysMenuUserListResp();
        List<SysMenuRole> sysMenuRoleList = new ArrayList<>();
        List<SysRoleUser> sysRoleUserList = sysRoleUserDao.listByUserId(sysUser.getId());
        for (SysRoleUser sru : sysRoleUserList) {
            sysMenuRoleList.addAll(sysMenuRoleDao.listByRoleId(sru.getRoleId()));
        }
        // 筛选
        List<AdminSysMenuUserResp> list = userRecursion(0L, null);
        List<AdminSysMenuUserResp> apiUrlList = new ArrayList<>();
        List<AdminSysMenuUserResp> listResqs = getListMenu(apiUrlList, sysMenuRoleList, list);
        if (CollectionUtil.isNotEmpty(listResqs)) {
            resq.setSysMenu(listResqs);
        }
        return Result.success(resq);
    }

    // 列出用户所有按钮菜单
    private List<AdminSysMenuUserResp> getListMenu(List<AdminSysMenuUserResp> apiUrlList, List<SysMenuRole> sysMenuRoleList, List<AdminSysMenuUserResp> list) {
        for (AdminSysMenuUserResp mv : list) {
            AdminSysMenuUserResp v = new AdminSysMenuUserResp();
            for (SysMenuRole vo : sysMenuRoleList) {
                if (mv.getId().equals(vo.getMenuId()) && MenuTypeEnum.BUTTON.getCode().equals(mv.getMenuType())) {
                    v.setApiUrl(mv.getApiUrl());
                    v.setName(mv.getName());
                    apiUrlList.add(v);
                    break;
                }
            }
            if (ObjectUtil.isNotNull(v)) {
                getListMenu(apiUrlList, sysMenuRoleList, mv.getChildren());
            }
        }
        return apiUrlList;
    }

}
