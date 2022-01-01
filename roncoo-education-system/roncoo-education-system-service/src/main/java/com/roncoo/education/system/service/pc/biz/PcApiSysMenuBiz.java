package com.roncoo.education.system.service.pc.biz;

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
import com.roncoo.education.system.service.pc.req.*;
import com.roncoo.education.system.service.pc.resq.*;
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
public class PcApiSysMenuBiz {

    @Autowired
    private SysMenuDao dao;
    @Autowired
    private SysUserDao sysUserDao;
    @Autowired
    private SysRoleUserDao sysRoleUserDao;
    @Autowired
    private SysMenuRoleDao sysMenuRoleDao;

    public Result<SysMenuListRESQ> list(SysMenuListREQ req) {
        SysMenuListRESQ resq = new SysMenuListRESQ();
        List<SysMenuRESQ> list = new ArrayList<>();
        if (StringUtils.isEmpty(req.getMenuName())) {
            list = recursion(0L);
        } else {
            // 模糊查询
            List<SysMenu> sysMenuList = dao.listByMenuName(req.getMenuName());
            if (CollectionUtil.isNotEmpty(sysMenuList)) {
                for (SysMenu sysMenu : sysMenuList) {
                    SysMenuRESQ sysMenuRESQ = BeanUtil.copyProperties(sysMenu, SysMenuRESQ.class);
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
    private List<SysMenuRESQ> recursion(Long parentId) {
        List<SysMenuRESQ> lists = new ArrayList<>();
        List<SysMenu> list = dao.listByParentId(parentId);
        if (CollectionUtil.isNotEmpty(list)) {
            for (SysMenu m : list) {
                SysMenuRESQ resq = BeanUtil.copyProperties(m, SysMenuRESQ.class);
                resq.setLabel(resq.getMenuName());
                resq.setChildren(recursion(m.getId()));
                lists.add(resq);
            }
        }
        return lists;
    }

    public Result<Integer> save(SysMenuSaveREQ req) {
        SysMenu record = BeanUtil.copyProperties(req, SysMenu.class);
        int results = dao.save(record);
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
    }

    @Transactional
    public Result<Integer> delete(SysMenuDeleteREQ req) {
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

    public Result<Integer> update(SysMenuUpdateREQ req) {
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

    public Result<SysMenuViewRESQ> view(SysMenuViewREQ req) {
        if (req.getId() == null) {
            return Result.error("主键ID不能为空");
        }
        SysMenu record = dao.getById(req.getId());
        if (ObjectUtil.isNull(record)) {
            return Result.error("找不到菜单信息");
        }
        return Result.success(BeanUtil.copyProperties(record, SysMenuViewRESQ.class));
    }

    public Result<SysMenuUserListRESQ> userList(SysMenuUserListREQ req) {
        if (req.getUserNo() == null) {
            return Result.error("用户编不能为空");
        }
        SysUser sysUser = sysUserDao.getByUserNo(req.getUserNo());
        if (ObjectUtil.isNull(sysUser)) {
            return Result.error("用户异常");
        }
        SysMenuUserListRESQ resq = new SysMenuUserListRESQ();
        List<SysMenuRole> sysMenuRoleList = new ArrayList<>();
        List<SysRoleUser> sysRoleUserList = sysRoleUserDao.listByUserId(sysUser.getId());
        for (SysRoleUser sru : sysRoleUserList) {
            sysMenuRoleList.addAll(sysMenuRoleDao.listByRoleId(sru.getRoleId()));
        }
        // 筛选
        List<SysMenuUserRESQ> list = listByRole(sysMenuRoleList, MenuTypeEnum.BUTTON.getCode());
        if (CollectionUtil.isNotEmpty(list)) {
            resq.setSysMenu(list);
        }
        return Result.success(resq);
    }

    private List<SysMenuUserRESQ> listByRole(List<SysMenuRole> sysMenuRoleList, Integer menuType) {
        List<SysMenuUserRESQ> list = userRecursion(0L, menuType);
        List<SysMenuUserRESQ> sysMenuUserRESQList = new ArrayList<>();
        sysMenuUserRESQList = listMenu(sysMenuUserRESQList, sysMenuRoleList, list);
        return sysMenuUserRESQList;
    }

    private List<SysMenuUserRESQ> listMenu(List<SysMenuUserRESQ> sysMenuVOList, List<SysMenuRole> sysMenuRoleList, List<SysMenuUserRESQ> list) {
        for (SysMenuUserRESQ mv : list) {
            SysMenuUserRESQ v = new SysMenuUserRESQ();
            for (SysMenuRole vo : sysMenuRoleList) {
                if (mv.getId().equals(vo.getMenuId())) {
                    v = BeanUtil.copyProperties(mv, SysMenuUserRESQ.class);
                    break;
                }
            }
            if (ObjectUtil.isNotNull(v) && v.getId() != null) {
                sysMenuVOList.add(v);
                List<SysMenuUserRESQ> l = new ArrayList<>();
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
    private List<SysMenuUserRESQ> userRecursion(Long parentId, Integer menuType) {
        List<SysMenuUserRESQ> lists = new ArrayList<>();
        List<SysMenu> list = dao.listByParentIdAndNotMenuType(parentId, menuType);
        if (CollectionUtil.isNotEmpty(list)) {
            for (SysMenu m : list) {
                SysMenuUserRESQ resq = BeanUtil.copyProperties(m, SysMenuUserRESQ.class);
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

    public Result<SysMenuUserListRESQ> buttonList(SysMenuUserListREQ req) {
        SysUser sysUser = sysUserDao.getByUserNo(req.getUserNo());
        if (ObjectUtil.isNull(sysUser)) {
            return Result.error("用户异常");
        }
        SysMenuUserListRESQ resq = new SysMenuUserListRESQ();
        List<SysMenuRole> sysMenuRoleList = new ArrayList<>();
        List<SysRoleUser> sysRoleUserList = sysRoleUserDao.listByUserId(sysUser.getId());
        for (SysRoleUser sru : sysRoleUserList) {
            sysMenuRoleList.addAll(sysMenuRoleDao.listByRoleId(sru.getRoleId()));
        }
        // 筛选
        List<SysMenuUserRESQ> list = userRecursion(0L, null);
        List<SysMenuUserRESQ> apiUrlList = new ArrayList<>();
        List<SysMenuUserRESQ> listResqs = getListMenu(apiUrlList, sysMenuRoleList, list);
        if (CollectionUtil.isNotEmpty(listResqs)) {
            resq.setSysMenu(listResqs);
        }
        return Result.success(resq);
    }

    // 列出用户所有按钮菜单
    private List<SysMenuUserRESQ> getListMenu(List<SysMenuUserRESQ> apiUrlList, List<SysMenuRole> sysMenuRoleList, List<SysMenuUserRESQ> list) {
        for (SysMenuUserRESQ mv : list) {
            SysMenuUserRESQ v = new SysMenuUserRESQ();
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
