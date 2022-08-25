package com.roncoo.education.user.service.api.biz;

import cn.hutool.crypto.digest.DigestUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.LoginStatusEnum;
import com.roncoo.education.common.core.tools.JWTUtil;
import com.roncoo.education.common.core.tools.StrUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.user.dao.LogLoginDao;
import com.roncoo.education.user.dao.UsersDao;
import com.roncoo.education.user.dao.impl.mapper.entity.LogLogin;
import com.roncoo.education.user.dao.impl.mapper.entity.Users;
import com.roncoo.education.user.service.api.req.PasswordReq;
import com.roncoo.education.user.service.api.req.RegisterReq;
import com.roncoo.education.user.service.api.resp.UsersLoginResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * 用户基本信息
 *
 * @author wujing
 */
@Component
public class ApiUsersBiz extends BaseBiz {

    @Autowired
    private UsersDao userDao;
    @Autowired
    private LogLoginDao logLoginDao;


    @Transactional
    public Result<UsersLoginResp> register(RegisterReq req) {
        if (StringUtils.isEmpty(req.getMobile())) {
            return Result.error("手机号不能为空");
        }
        if (StringUtils.isEmpty(req.getPassword())) {
            return Result.error("密码不能为空");
        }
        // 密码校验
        if (!req.getPassword().equals(req.getRepassword())) {
            return Result.error("密码不一致");
        }

        // 手机号重复校验
        Users user = userDao.getByMobile(req.getMobile());
        if (null != user) {
            return Result.error("该手机号已经注册，请更换手机号");
        }

        // 用户注册
        user = register(req.getMobile(), req.getPassword());

        // 登录日志
        loginLog(user.getId(), LoginStatusEnum.SUCCESS, req.getIp());

        UsersLoginResp dto = new UsersLoginResp();
        dto.setMobile(user.getMobile());
        dto.setToken(JWTUtil.create(user.getId(), JWTUtil.DATE));
        return Result.success(dto);
    }

    public Result<UsersLoginResp> loginPassword(PasswordReq req) {
        if (StringUtils.isEmpty(req.getMobile())) {
            return Result.error("手机号不能为空");
        }
        if (StringUtils.isEmpty(req.getPassword())) {
            return Result.error("密码不能为空");
        }
        // 密码错误次数校验

        // 用户校验
        Users user = userDao.getByMobile(req.getMobile());
        if (null == user) {
            return Result.error("账号或者密码不正确");
        }

        // 密码校验
        if (!DigestUtil.sha1Hex(user.getMobileSalt() + req.getPassword()).equals(user.getMobilePsw())) {
            loginLog(user.getId(), LoginStatusEnum.FAIL, req.getIp());
            // 放入缓存，错误次数+1
            return Result.error("账号或者密码不正确");
        }

        // 登录日志
        loginLog(user.getId(), LoginStatusEnum.SUCCESS, req.getIp());

        UsersLoginResp dto = new UsersLoginResp();
        dto.setMobile(user.getMobile());
        dto.setToken(JWTUtil.create(user.getId(), JWTUtil.DATE));

        // 登录成功，存入缓存，单点登录使用
        // redisTemplate.opsForValue().set(dto.getUserNo().toString(), dto.getToken(), 1, TimeUnit.DAYS);

        return Result.success(dto);
    }

    private Users register(String mobile, String password) {
        // 用户基本信息
        Users user = new Users();
        user.setMobile(mobile);
        user.setMobileSalt(StrUtil.get32UUID());
        user.setMobilePsw(DigestUtil.sha1Hex(user.getMobileSalt() + password));
        userDao.save(user);
        return user;
    }

    private void loginLog(Long userId, LoginStatusEnum status, String ip) {
        LogLogin record = new LogLogin();
        record.setUserId(userId);
        record.setLoginStatus(status.getCode());
        record.setLoginIp(ip);
        logLoginDao.save(record);
    }

}
