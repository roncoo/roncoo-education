package com.roncoo.education.user.service.api.biz;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.roncoo.education.common.cache.CacheRedis;
import com.roncoo.education.common.core.base.BaseException;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.LoginStatusEnum;
import com.roncoo.education.common.core.tools.*;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.common.sms.SmsUtil;
import com.roncoo.education.system.feign.interfaces.IFeignSysConfig;
import com.roncoo.education.user.dao.LogLoginDao;
import com.roncoo.education.user.dao.UsersDao;
import com.roncoo.education.user.dao.impl.mapper.entity.LogLogin;
import com.roncoo.education.user.dao.impl.mapper.entity.Users;
import com.roncoo.education.user.service.api.req.LoginReq;
import com.roncoo.education.user.service.api.req.PasswordReq;
import com.roncoo.education.user.service.api.req.RegisterReq;
import com.roncoo.education.user.service.api.req.SendCodeReq;
import com.roncoo.education.user.service.api.resp.UsersLoginResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

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
    @Autowired
    private CacheRedis cacheRedis;
    @Autowired
    private IFeignSysConfig feignSysConfig;
    @Autowired
    private HttpServletRequest request;

    @Transactional(rollbackFor = Exception.class)
    public Result<UsersLoginResp> register(RegisterReq req) {
        if (!StringUtils.hasText(req.getMobile())) {
            return Result.error("手机号不能为空");
        }
        // 验证码校验
        String redisCode = cacheRedis.get(Constants.RedisPre.CODE + req.getMobile());
        if (!StringUtils.hasText(redisCode)) {
            return Result.error("验证码已经过期");
        }
        if (!req.getCode().equals(redisCode)) {
            return Result.error("验证码不正确");
        }
        // 删除验证码缓存
        cacheRedis.delete(Constants.RedisPre.CODE + req.getMobile());

        if (!StringUtils.hasText(req.getMobilePwd())) {
            return Result.error("密码不能为空");
        }
        // 密码校验
        if (!req.getMobilePwd().equals(req.getRepassword())) {
            return Result.error("密码不一致");
        }

        // 手机号重复校验
        Users user = userDao.getByMobile(req.getMobile());
        if (null != user) {
            return Result.error("该手机号已经注册，请更换手机号");
        }

        // 用户注册
        user = register(req.getMobile(), req.getMobilePwd());

        // 日志
        log(user.getId(), LoginStatusEnum.REGISTER, BeanUtil.copyProperties(req, LogLogin.class));

        UsersLoginResp dto = new UsersLoginResp();
        dto.setMobile(user.getMobile());
        dto.setToken(JWTUtil.create(user.getId(), JWTUtil.DATE));
        return Result.success(dto);
    }

    public Result<UsersLoginResp> login(LoginReq req) {
        if (!StringUtils.hasText(req.getMobile())) {
            return Result.error("手机号不能为空");
        }
        if (!StringUtils.hasText(req.getPassword())) {
            return Result.error("密码不能为空");
        }

        String verCode = cacheRedis.get(Constants.RedisPre.VERI_CODE + req.getVerToken());
        if (!StringUtils.hasText(verCode)) {
            return Result.error("验证码已过期");
        }
        if (!verCode.equals(req.getVerCode())) {
            return Result.error("验证码不正确");
        }

        // 用户校验
        Users user = userDao.getByMobile(req.getMobile());
        if (null == user) {
            return Result.error("账号或者密码不正确");
        }

        // 密码校验
        if (!DigestUtil.sha1Hex(user.getMobileSalt() + req.getPassword()).equals(user.getMobilePsw())) {
            // 错误登录日志
            log(user.getId(), LoginStatusEnum.FAIL, BeanUtil.copyProperties(req, LogLogin.class));
            return Result.error("账号或者密码不正确");
        }

        // 成功登录日志
        log(user.getId(), LoginStatusEnum.SUCCESS, BeanUtil.copyProperties(req, LogLogin.class));

        UsersLoginResp dto = new UsersLoginResp();
        dto.setMobile(user.getMobile());
        dto.setToken(JWTUtil.create(user.getId(), JWTUtil.DATE));

        // token，放入缓存
        cacheRedis.set(dto.getToken(), user.getId(), 1, TimeUnit.DAYS);
        return Result.success(dto);
    }

    private Users register(String mobile, String password) {
        // 用户基本信息
        Users user = new Users();
        user.setMobile(mobile);
        user.setMobileSalt(IdUtil.simpleUUID());
        user.setMobilePsw(DigestUtil.sha1Hex(user.getMobileSalt() + password));
        // 默认8位随机字符串
        user.setNickname(RandomUtil.randomString(8));
        user.setUserHead("https://static.roncoos.com/lingke.png");
        userDao.save(user);
        return user;
    }

    private void log(Long userId, LoginStatusEnum status, LogLogin record) {
        record.setUserId(userId);
        record.setLoginStatus(status.getCode());
        if (!StringUtils.hasText(record.getLoginIp())) {
            IPUtil.IpInfo ipInfo = IPUtil.getIpInfo(ServletUtil.getClientIP(request));
            if (ObjectUtil.isNotNull(ipInfo)) {
                record.setLoginIp(ipInfo.getIp());
                record.setProvince(ipInfo.getPro());
                record.setCity(ipInfo.getCity());
            }
        }
        logLoginDao.save(record);
    }

    public Result<String> sendCode(SendCodeReq req) {
        // 验证码发送次数校验
        if (!sendCodeCheck(req.getMobile())) {
            throw new BaseException("验证码发送次数过多，请稍后再试");
        }

        String code = NOUtil.getVerCode();
        log.warn("手机号：{}，验证码：{}", req.getMobile(), code);

        // 正常应该是发送成功才放入缓存，这里方便没有短信通道的情况下，也能测试注册（上线需要删除，并打开下面）
        cacheRedis.set(Constants.RedisPre.CODE + req.getMobile(), code, 5, TimeUnit.MINUTES);

        if (SmsUtil.sendVerCode(req.getMobile(), code, feignSysConfig.getSms())) {
            // 发送成功才放入缓存
            // cacheRedis.set(Constants.RedisPre.CODE + req.getMobile(), code, 5, TimeUnit.MINUTES);
            return Result.success("验证码发送成功，请查收");
        }
        return Result.error("验证码发送失败，请稍后再试");
    }

    /**
     * 5分钟内，同一个手机号不能超2次发送验证码
     *
     * @param mobile
     */
    private Boolean sendCodeCheck(String mobile) {
        String count = cacheRedis.get(Constants.RedisPre.CODE_STAT + mobile);
        if (StringUtils.hasText(count)) {
            int countNum = cacheRedis.set(Constants.RedisPre.CODE_STAT + mobile, Integer.valueOf(count).intValue() + 1, 5, TimeUnit.MINUTES);
            if (countNum < 2) {
                return Boolean.TRUE;
            }
            return Boolean.FALSE;
        }
        cacheRedis.set(Constants.RedisPre.CODE_STAT + mobile, 1, 5, TimeUnit.MINUTES);
        return Boolean.TRUE;
    }

    public Result<String> password(PasswordReq req) {
        // 验证码校验
        String redisCode = cacheRedis.get(Constants.RedisPre.CODE + req.getMobile());
        if (!StringUtils.hasText(redisCode)) {
            return Result.error("验证码已经过期");
        }
        if (!req.getCode().equals(redisCode)) {
            return Result.error("验证码不正确");
        }
        // 删除验证码缓存
        cacheRedis.delete(Constants.RedisPre.CODE + req.getMobile());

        if (!StringUtils.hasText(req.getMobilePwd())) {
            return Result.error("密码不能为空");
        }
        // 密码校验
        if (!req.getMobilePwd().equals(req.getRepassword())) {
            return Result.error("密码不一致");
        }

        // 手机号重复校验
        Users user = userDao.getByMobile(req.getMobile());
        if (null == user) {
            return Result.error("该手机号没注册，请先注册");
        }

        // 重置密码
        Users recorde = new Users();
        recorde.setId(user.getId());
        recorde.setMobileSalt(IdUtil.simpleUUID());
        recorde.setMobilePsw(DigestUtil.sha1Hex(recorde.getMobileSalt() + req.getMobilePwd()));
        userDao.updateById(recorde);

        return Result.success("重置成功");
    }
}
