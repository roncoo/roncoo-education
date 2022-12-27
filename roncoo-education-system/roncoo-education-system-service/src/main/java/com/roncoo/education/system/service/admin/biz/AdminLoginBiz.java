package com.roncoo.education.system.service.admin.biz;


import cn.hutool.core.collection.CollectionUtil;
import com.roncoo.education.common.cache.CacheRedis;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.tools.Constants;
import com.roncoo.education.common.core.tools.JWTUtil;
import com.roncoo.education.common.core.tools.SHA1Util;
import com.roncoo.education.system.dao.SysMenuDao;
import com.roncoo.education.system.dao.SysMenuRoleDao;
import com.roncoo.education.system.dao.SysRoleUserDao;
import com.roncoo.education.system.dao.SysUserDao;
import com.roncoo.education.system.dao.impl.mapper.entity.SysMenu;
import com.roncoo.education.system.dao.impl.mapper.entity.SysMenuRole;
import com.roncoo.education.system.dao.impl.mapper.entity.SysRoleUser;
import com.roncoo.education.system.dao.impl.mapper.entity.SysUser;
import com.roncoo.education.system.service.admin.req.AdminSysUserLoginReq;
import com.roncoo.education.system.service.admin.resp.AdminSysUserLoginResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * 用户登录
 *
 * @author roncoo
 */
@RequiredArgsConstructor
@Component
public class AdminLoginBiz {

    private final SysUserDao sysUserDao;
    private final SysRoleUserDao sysRoleUserDao;
    private final SysMenuRoleDao sysMenuRoleDao;
    private final SysMenuDao sysMenuDao;
    private final CacheRedis cacheRedis;

    /**
     * 用户登录
     *
     * @param req 登录请求参数
     * @return 登录结果
     */
    public Result<AdminSysUserLoginResp> login(AdminSysUserLoginReq req) {
        SysUser sysUser = sysUserDao.getByMobile(req.getMobile());
        if (sysUser == null) {
            return Result.error("用户不存在");
        }
        // 校验状态
        if (!StatusIdEnum.YES.getCode().equals(sysUser.getStatusId())) {
            return Result.error("账号不可用");
        }
        // 密码校验
        if (!SHA1Util.getSign(sysUser.getMobileSalt() + req.getMobilePwd()).equals(sysUser.getMobilePsw())) {
            return Result.error("账号或密码不正确");
        }

        // 封装返回数据
        AdminSysUserLoginResp resp = new AdminSysUserLoginResp();
        resp.setMobile(sysUser.getMobile());
        resp.setRealName(sysUser.getRealName());
        resp.setToken(JWTUtil.create(sysUser.getId(), JWTUtil.DATE));

        // token，放入缓存
        cacheRedis.set(resp.getToken(), sysUser.getId(), 1, TimeUnit.DAYS);

        // 获取菜单权限，放入缓存
        cacheRedis.set(Constants.RedisPre.ADMINI_MENU.concat(sysUser.getId().toString()), extracted(sysUser), 1, TimeUnit.DAYS);

        // TODO 登录日志
        return Result.success(resp);
    }

    private List<String> extracted(SysUser sysUser) {
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
        return sysMenus.stream().map(SysMenu::getAuthValue).collect(Collectors.toList());
    }

}
