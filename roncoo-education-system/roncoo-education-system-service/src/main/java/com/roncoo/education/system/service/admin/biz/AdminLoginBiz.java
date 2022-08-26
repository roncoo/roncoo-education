package com.roncoo.education.system.service.admin.biz;


import com.roncoo.education.common.cache.CacheRedis;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.tools.Constants;
import com.roncoo.education.common.core.tools.JWTUtil;
import com.roncoo.education.common.core.tools.SHA1Util;
import com.roncoo.education.system.dao.SysUserDao;
import com.roncoo.education.system.dao.impl.mapper.entity.SysUser;
import com.roncoo.education.system.service.admin.req.AdminUsersLoginReq;
import com.roncoo.education.system.service.admin.resp.AdminUsersLoginResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

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

    /**
     * 用户登录
     *
     * @param req 登录请求参数
     * @return 登录结果
     */
    public Result<AdminUsersLoginResp> login(AdminUsersLoginReq req) {
        SysUser sysUser = sysUserDao.getByMobile(req.getMobile());
        if (sysUser == null) {
            return Result.error("用户不存在");
        }
        // 校验状态
        if (!StatusIdEnum.YES.getCode().equals(sysUser.getStatusId())) {
            return Result.error("账号不可用");
        }
        // 密码校验
        if (!SHA1Util.getSign(sysUser.getMobileSalt()+req.getPassword()).equals(sysUser.getMobilePsw())) {
            return Result.error("账号或密码不正确");
        }

        // 封装返回数据
        AdminUsersLoginResp resp = new AdminUsersLoginResp();
        resp.setMobile(sysUser.getMobile());
        resp.setRealName(sysUser.getRealName());
        resp.setToken(JWTUtil.create(sysUser.getId(), JWTUtil.DATE));
        cacheRedis.set(Constants.RedisPre.USERS_INFO.concat(sysUser.getId().toString()), resp.getToken(), 1, TimeUnit.DAYS);
        return Result.success(resp);
    }

}
