package com.roncoo.education.system.service.admin.biz;


import com.roncoo.education.common.cache.CacheRedis;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.tools.Constants;
import com.roncoo.education.common.core.tools.JWTUtil;
import com.roncoo.education.common.core.tools.SHA1Util;
import com.roncoo.education.system.dao.SysUserDao;
import com.roncoo.education.system.dao.impl.mapper.entity.SysMenu;
import com.roncoo.education.system.dao.impl.mapper.entity.SysUser;
import com.roncoo.education.system.service.admin.req.AdminSysUserLoginReq;
import com.roncoo.education.system.service.admin.resp.AdminSysUserLoginResp;
import com.roncoo.education.system.service.biz.SysUserCommonBiz;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
    private final CacheRedis cacheRedis;
    private final SysUserCommonBiz sysUserCommonBiz;

    /**
     * 用户登录
     *
     * @param req 登录请求参数
     * @return 登录结果
     */
    public Result<AdminSysUserLoginResp> login(AdminSysUserLoginReq req) {
        // 验证码校验
        String verCode = cacheRedis.get(Constants.RedisPre.ADMIN_VERI_CODE + req.getVerToken());
        if (!StringUtils.hasText(verCode)) {
            return Result.error("验证码已过期");
        }
        if (!verCode.equals(req.getVerCode())) {
            return Result.error("验证码不正确");
        }

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

        // 列出菜单
        List<SysMenu> sysMenus = sysUserCommonBiz.listMenu(sysUser.getId());
        // 路由
        resp.setRouterList(sysUserCommonBiz.routerList(sysMenus));
        // 菜单
        resp.setMenuList(sysUserCommonBiz.menuList(sysMenus));

        // 获取接口权限，放入缓存
        List<String> apis = sysMenus.stream().map(SysMenu::getApis).collect(Collectors.toList());
        cacheRedis.set(Constants.RedisPre.ADMINI_APIS.concat(sysUser.getId().toString()), apis, 1, TimeUnit.DAYS);

        return Result.success(resp);
    }

}
