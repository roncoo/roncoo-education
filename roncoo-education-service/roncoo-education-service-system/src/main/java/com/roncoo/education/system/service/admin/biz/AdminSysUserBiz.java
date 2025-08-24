package com.roncoo.education.system.service.admin.biz;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.cache.CacheRedis;
import com.roncoo.education.common.core.base.Constants;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.MenuTypeEnum;
import com.roncoo.education.common.core.enums.ResultEnum;
import com.roncoo.education.common.base.BaseBiz;
import com.roncoo.education.common.tools.BeanUtil;
import com.roncoo.education.common.tools.Sha1Util;
import com.roncoo.education.system.dao.SysRoleDao;
import com.roncoo.education.system.dao.SysRoleUserDao;
import com.roncoo.education.system.dao.SysUserDao;
import com.roncoo.education.system.dao.impl.mapper.entity.*;
import com.roncoo.education.system.dao.impl.mapper.entity.SysUserExample.Criteria;
import com.roncoo.education.system.service.admin.req.*;
import com.roncoo.education.system.service.admin.resp.AdminSysMenuUserResp;
import com.roncoo.education.system.service.admin.resp.AdminSysUserPageResp;
import com.roncoo.education.system.service.admin.resp.AdminSysUserViewResp;
import com.roncoo.education.system.service.biz.SysConfigCommonBiz;
import com.roncoo.education.system.service.biz.SysUserCommonBiz;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 后台用户信息
 *
 * @author wujing
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class AdminSysUserBiz extends BaseBiz {

    @NotNull
    private final SysUserDao dao;
    @NotNull
    private final SysRoleUserDao sysRoleUserDao;
    @NotNull
    private final SysRoleDao sysRoleDao;
    @NotNull
    private final SysUserCommonBiz sysUserCommonBiz;
    @NotNull
    private final SysConfigCommonBiz sysConfigCommonBiz;
    @NotNull
    private final CacheRedis cacheRedis;

    public Result<Page<AdminSysUserPageResp>> listForPage(AdminSysUserPageReq req) {
        SysUserExample example = new SysUserExample();
        Criteria c = example.createCriteria();
        if (StringUtils.hasText(req.getMobile())) {
            c.andMobileEqualTo(req.getMobile());
        }
        example.setOrderByClause(" sort asc, id desc ");
        Page<SysUser> page = dao.page(req.getPageCurrent(), req.getPageSize(), example);
        Page<AdminSysUserPageResp> respPage = PageUtil.transform(page, AdminSysUserPageResp.class);
        if (CollUtil.isNotEmpty(respPage.getList())) {
            List<Long> userIdList = respPage.getList().stream().map(AdminSysUserPageResp::getId).collect(Collectors.toList());
            List<SysRoleUser> roleUserList = sysRoleUserDao.listByUserIds(userIdList);
            if (CollUtil.isNotEmpty(roleUserList)) {
                List<Long> roles = roleUserList.stream().map(SysRoleUser::getRoleId).collect(Collectors.toList());
                List<SysRole> roleList = sysRoleDao.listByIds(roles);
                extracted(respPage, roleUserList, roleList);
            }
        }
        return Result.success(respPage);
    }

    private void extracted(Page<AdminSysUserPageResp> respPage, List<SysRoleUser> roleUserList, List<SysRole> roleList) {
        if (CollUtil.isNotEmpty(roleList)) {
            Map<Long, String> roleNameMap = roleList.stream().collect(Collectors.toMap(SysRole::getId, SysRole::getRoleName));
            Map<Long, List<Long>> map = roleUserList.stream().collect(Collectors.groupingBy(SysRoleUser::getUserId, Collectors.mapping(SysRoleUser::getRoleId, Collectors.toList())));
            for (AdminSysUserPageResp resp : respPage.getList()) {
                List<Long> roleIdList = map.get(resp.getId());
                if (CollUtil.isNotEmpty(roleIdList)) {
                    List<String> roleNameList = new ArrayList<>();
                    for (Long roleId : roleIdList) {
                        roleNameList.add(roleNameMap.get(roleId));
                    }
                    resp.setRoleNameList(roleNameList);
                }
            }
        }
    }

    public Result<String> save(AdminSysUserSaveReq req) {
        // 解密
        String mobilePsw = sysConfigCommonBiz.decrypt(req.getMobilePwdEncrypt());
        if (!StringUtils.hasText(mobilePsw)) {
            return Result.error("密码不能为空");
        }

        SysUser sysUser = dao.getByMobile(req.getMobile());
        if (ObjectUtil.isNotNull(sysUser)) {
            return Result.error("用户已添加成管理员");
        }
        SysUser record = BeanUtil.copyProperties(req, SysUser.class);
        record.setMobileSalt(IdUtil.simpleUUID());
        record.setMobilePsw(Sha1Util.getSign(record.getMobileSalt() + mobilePsw));
        int results = dao.save(record);
        if (results > 0) {
            return Result.success("操作成功");
        }
        return Result.error(ResultEnum.SYSTEM_SAVE_FAIL);
    }

    @Transactional
    public Result<String> delete(AdminSysUserDeleteReq req) {
        if (req.getId() == null) {
            return Result.error("主键ID不能为空");
        }
        // 1、删除用户所有角色
        sysRoleUserDao.deleteByUserId(req.getId());
        // 2、删除用户
        dao.deleteById(req.getId());
        return Result.success("操作成功");
    }

    public Result<String> update(AdminSysUserUpdateReq req) {
        if (req.getId() == null) {
            return Result.error("主键ID不能为空");
        }
        SysUser sysUser = dao.getById(req.getId());
        if (ObjectUtil.isNull(sysUser)) {
            return Result.error("找不到管理员信息");
        }
        SysUser record = BeanUtil.copyProperties(req, SysUser.class);
        int results = dao.updateById(record);
        if (results > 0) {
            return Result.success("操作成功");
        }
        return Result.error(ResultEnum.SYSTEM_UPDATE_FAIL);
    }

    public Result<AdminSysUserViewResp> view(AdminSysUserViewReq req) {
        if (req.getId() == null) {
            return Result.error("主键ID不能为空");
        }
        SysUser sysUser = dao.getById(req.getId());
        if (ObjectUtil.isNull(sysUser)) {
            return Result.error("管理员不存在");
        }
        AdminSysUserViewResp resp = BeanUtil.copyProperties(sysUser, AdminSysUserViewResp.class);
        // 列出菜单
        List<SysMenu> sysMenus = sysUserCommonBiz.listMenu(sysUser.getId());
        // 路由
        resp.setRouterList(sysUserCommonBiz.routerList(sysMenus));
        // 菜单
        resp.setMenuList(sysUserCommonBiz.menuList(sysMenus));
        // 权限
        resp.setPermissionList(sysMenus.stream().filter(item -> StringUtils.hasText(item.getPermission())).map(SysMenu::getPermission).collect(Collectors.toList()));
        // 获取接口权限，放入缓存
        List<String> apis = sysMenus.stream().filter(item -> StringUtils.hasText(item.getApis())).map(SysMenu::getApis).collect(Collectors.toList());
        cacheRedis.set(Constants.RedisPre.ADMIN_APIS.concat(sysUser.getId().toString()), apis, 1, TimeUnit.DAYS);
        return Result.success(resp);
    }

    public Result<String> updatePassword(AdminSysUserUpdatePasswordReq req) {
        // 解密
        String mobilePsw = sysConfigCommonBiz.decrypt(req.getMobilePwdEncrypt());
        if (!StringUtils.hasText(mobilePsw)) {
            return Result.error("密码不能为空");
        }
        if (req.getUserId() == null) {
            return Result.error("用户ID不能为空,请重试");
        }
        SysUser record = new SysUser();
        record.setId(req.getUserId());
        record.setMobileSalt(IdUtil.simpleUUID());
        record.setMobilePsw(Sha1Util.getSign(record.getMobileSalt() + mobilePsw));
        dao.updateById(record);
        return Result.success("操作成功");
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
