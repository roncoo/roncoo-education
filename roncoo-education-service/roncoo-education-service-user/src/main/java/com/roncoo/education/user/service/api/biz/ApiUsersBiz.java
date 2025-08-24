package com.roncoo.education.user.service.api.biz;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.roncoo.education.common.cache.CacheRedis;
import com.roncoo.education.common.core.base.BaseException;
import com.roncoo.education.common.core.base.Constants;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.LoginAuthTypeEnum;
import com.roncoo.education.common.core.enums.LoginStatusEnum;
import com.roncoo.education.common.core.enums.SmsPlatformEnum;
import com.roncoo.education.common.core.tools.JwtUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.common.service.BaseWxBiz;
import com.roncoo.education.common.sms.Sms;
import com.roncoo.education.common.sms.SmsFace;
import com.roncoo.education.common.tools.*;
import com.roncoo.education.system.feign.interfaces.IFeignSysConfig;
import com.roncoo.education.system.feign.interfaces.vo.LoginConfig;
import com.roncoo.education.user.dao.UsersAccountDao;
import com.roncoo.education.user.dao.UsersDao;
import com.roncoo.education.user.dao.UsersLogDao;
import com.roncoo.education.user.dao.impl.mapper.entity.Users;
import com.roncoo.education.user.dao.impl.mapper.entity.UsersAccount;
import com.roncoo.education.user.dao.impl.mapper.entity.UsersLog;
import com.roncoo.education.user.service.api.req.*;
import com.roncoo.education.user.service.api.resp.UsersLoginResp;
import com.roncoo.education.user.service.api.resp.WxCodeResp;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.service.WxOAuth2Service;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpMapConfigImpl;
import me.chanjar.weixin.open.api.impl.WxOpenOAuth2ServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 用户基本信息
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class ApiUsersBiz extends BaseBiz {

    @NotNull
    private final UsersDao usersDao;
    @NotNull
    private final UsersAccountDao usersAccountDao;
    @NotNull
    private final UsersLogDao usersLogDao;
    @NotNull
    private final CacheRedis cacheRedis;
    @NotNull
    private final IFeignSysConfig feignSysConfig;
    @NotNull
    private final HttpServletRequest request;
    @NotNull
    private final BaseWxBiz baseWxBiz;
    @NotNull
    private final Map<String, SmsFace> smsFaceMap;

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

        if (!StringUtils.hasText(req.getMobilePwdEncrypt())) {
            return Result.error("密码不能为空");
        }
        // 解密
        String mobilePsw = decrypt(req.getMobilePwdEncrypt());
        if (!StringUtils.hasText(mobilePsw)) {
            return Result.error("密码不能为空");
        }

        // 手机号重复校验
        Users user = usersDao.getByMobile(req.getMobile());
        if (null != user) {
            return Result.error("该手机号已经注册，请更换手机号");
        }

        // 用户注册
        user = register(req.getMobile(), mobilePsw, req.getRegisterSource(), null, null);

        return Result.success(login(user.getId(), user.getMobile(), LoginStatusEnum.REGISTER, BeanUtil.copyProperties(req, UsersLog.class)));
    }

    public Result<UsersLoginResp> login(LoginReq req) {
        if (!StringUtils.hasText(req.getMobile())) {
            return Result.error("手机号不能为空");
        }
        if (!StringUtils.hasText(req.getMobilePwdEncrypt())) {
            return Result.error("密码不能为空");
        }

        String verCode = cacheRedis.get(Constants.RedisPre.VER_CODE + req.getVerToken());
        if (!StringUtils.hasText(verCode)) {
            return Result.error("验证码已过期");
        }
        if (!verCode.equals(req.getVerCode().toLowerCase())) {
            return Result.error("验证码不正确");
        }

        // 用户校验
        Users user = usersDao.getByMobile(req.getMobile());
        if (null == user) {
            return Result.error("账号或者密码不正确");
        }

        // 解密
        String mobilePsw = decrypt(req.getMobilePwdEncrypt());
        if (!StringUtils.hasText(mobilePsw)) {
            return Result.error("密码不能为空");
        }

        // 密码校验
        if (!DigestUtil.sha1Hex(user.getMobileSalt() + mobilePsw).equals(user.getMobilePsw())) {
            // 错误登录日志
            log(user.getId(), LoginStatusEnum.FAIL, BeanUtil.copyProperties(req, UsersLog.class));
            return Result.error("账号或者密码不正确");
        }

        return Result.success(login(user.getId(), user.getMobile(), LoginStatusEnum.SUCCESS, BeanUtil.copyProperties(req, UsersLog.class)));
    }

    private String decrypt(String password) {
        String privateKey = cacheRedis.get(Constants.RedisPre.PRIVATEKEY);
        if (ObjectUtils.isEmpty(privateKey)) {
            privateKey = feignSysConfig.getLogin().getRsaLoginPrivateKey();
            cacheRedis.set(Constants.RedisPre.PRIVATEKEY, privateKey, 1, TimeUnit.DAYS);
        }
        return RsaUtil.decrypt(password, privateKey);
    }

    private Users register(String mobile, String password, Integer registerSource, String unionId, String openId) {
        // 用户基本信息
        Users user = new Users();
        user.setMobile(mobile);
        user.setMobileSalt(IdUtil.simpleUUID());
        user.setMobilePsw(DigestUtil.sha1Hex(user.getMobileSalt() + password));
        // 默认8位随机字符串
        user.setNickname(RandomUtil.randomString(8));
        user.setUserHead("https://static.roncoos.com/lingke.png");
        user.setRegisterSource(registerSource);
        user.setUnionId(unionId);
        user.setOpenId(openId);

        WxOAuth2UserInfo userInfo = null;
        if (StringUtils.hasText(unionId)) {
            userInfo = cacheRedis.get(Constants.RedisPre.WX_USER + unionId + openId, WxOAuth2UserInfo.class);
        } else {
            if (StringUtils.hasText(openId)) {
                userInfo = cacheRedis.get(Constants.RedisPre.WX_USER + openId, WxOAuth2UserInfo.class);
            }
        }
        if (ObjectUtil.isNotNull(userInfo)) {
            user.setCity(userInfo.getCity());
            user.setProvince(userInfo.getProvince());
            user.setCountry(userInfo.getCountry());
            user.setUserSex(userInfo.getSex());
            user.setNickname(userInfo.getNickname());
        }
        usersDao.save(user);

        // 用户账户
        UsersAccount usersAccount = new UsersAccount();
        usersAccount.setUserId(user.getId());
        usersAccount.setAvailableAmount(BigDecimal.ZERO);
        usersAccount.setFreezeAmount(BigDecimal.ZERO);
        usersAccount.setSign(Md5Util.md5(usersAccount.getUserId().toString(), usersAccount.getAvailableAmount().toPlainString(), usersAccount.getFreezeAmount().toPlainString()));
        usersAccountDao.save(usersAccount);
        return user;
    }

    public Result<String> sendCode(SendCodeReq req) {
        // 验证码发送次数校验
        if (!sendCodeCheck(req.getMobile())) {
            throw new BaseException("操作频繁，请稍后再试");
        }

        String code = NumUtil.getVerCode();
        log.warn("手机号：{}，验证码：{}", req.getMobile(), code);

        // TODO 正常应该是发送成功才放入缓存，这里方便没有短信通道的情况下，也能测试注册（上线需要删除该处）
        cacheRedis.set(Constants.RedisPre.CODE + req.getMobile(), code, 5, TimeUnit.MINUTES);

        Sms sms = feignSysConfig.getSms();
        SmsFace smsFace = smsFaceMap.get(SmsPlatformEnum.byCode(sms.getSmsPlatform()).getMode());
        if (ObjectUtil.isEmpty(smsFace)) {
            return Result.error("暂不支持该平台");
        }
        if (smsFace.sendVerCode(req.getMobile(), code, sms)) {
            // 发送成功，放入缓存
            cacheRedis.set(Constants.RedisPre.CODE + req.getMobile(), code, 5, TimeUnit.MINUTES);
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
            int countNum = cacheRedis.set(Constants.RedisPre.CODE_STAT + mobile, Integer.parseInt(count) + 1, 5, TimeUnit.MINUTES);
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

        if (!StringUtils.hasText(req.getMobilePwdEncrypt())) {
            return Result.error("密码不能为空");
        }
        // 解密
        String mobilePsw = decrypt(req.getMobilePwdEncrypt());
        if (!StringUtils.hasText(mobilePsw)) {
            return Result.error("密码不能为空");
        }

        // 手机号重复校验
        Users user = usersDao.getByMobile(req.getMobile());
        if (null == user) {
            return Result.error("该手机号没注册，请先注册");
        }

        // 重置密码
        Users recorde = new Users();
        recorde.setId(user.getId());
        recorde.setMobileSalt(IdUtil.simpleUUID());
        recorde.setMobilePsw(DigestUtil.sha1Hex(recorde.getMobileSalt() + mobilePsw));
        usersDao.updateById(recorde);

        return Result.success("重置成功");
    }

    public Result<String> wxLogin(WxLoginReq req) {
        LoginConfig loginConfig = feignSysConfig.getLogin();
        if (req.getLoginAuthType().equals(LoginAuthTypeEnum.PC.getCode())) {
            if (!loginConfig.getWxPcLoginEnable().equals("1")) {
                return Result.error("网页应用登录没开启");
            }
            WxOAuth2Service wxOAuth2Service = new WxOpenOAuth2ServiceImpl(loginConfig.getWxPcLoginAppId(), loginConfig.getWxPcLoginAppSecret());
            String authorizationUrl = wxOAuth2Service.buildAuthorizationUrl(req.getRedirectUrl(), WxConsts.QrConnectScope.SNSAPI_LOGIN, LoginAuthTypeEnum.PC.name());
            return Result.success(authorizationUrl);
        }
        if (req.getLoginAuthType().equals(LoginAuthTypeEnum.MP.getCode())) {
            if (!loginConfig.getWxMpLoginEnable().equals("1")) {
                return Result.error("公众号登录没开启");
            }
            WxMpService wxMpService = new WxMpServiceImpl();
            WxMpMapConfigImpl mpMapConfig = new WxMpMapConfigImpl();
            mpMapConfig.setAppId(loginConfig.getWxMpLoginAppId());
            mpMapConfig.setSecret(loginConfig.getWxMpLoginAppSecret());
            wxMpService.setWxMpConfigStorage(mpMapConfig);
            String authorizationUrl = wxMpService.getOAuth2Service().buildAuthorizationUrl(req.getRedirectUrl(), WxConsts.OAuth2Scope.SNSAPI_USERINFO, LoginAuthTypeEnum.MP.name());
            return Result.success(authorizationUrl);
        }
        log.error("登录类型暂没支持={}", JsonUtil.toJsonString(req));
        return Result.error("登录类型暂没支持");
    }

    public Result<WxCodeResp> wxCode(WxCodeReq req) throws WxErrorException {
        LoginConfig loginConfig = feignSysConfig.getLogin();
        WxCodeResp codeResp = new WxCodeResp();
        if (req.getLoginAuthType().equals(LoginAuthTypeEnum.PC.getCode())) {
            // 网页应用
            codeResp.setUserInfo(baseWxBiz.getAuthInfo(loginConfig.getWxPcLoginAppId(), loginConfig.getWxPcLoginAppSecret(), req.getCode()));
        } else if (req.getLoginAuthType().equals(LoginAuthTypeEnum.MP.getCode())) {
            // 公众号
            codeResp.setUserInfo(baseWxBiz.getAuthInfo(loginConfig.getWxMpLoginAppId(), loginConfig.getWxMpLoginAppSecret(), req.getCode()));
        } else if (req.getLoginAuthType().equals(LoginAuthTypeEnum.MA.getCode())) {
            // 小程序
            WxMaService wxMaService = new WxMaServiceImpl();
            WxMaDefaultConfigImpl wxMaConfig = new WxMaDefaultConfigImpl();
            wxMaConfig.setAppid(loginConfig.getWxMaLoginAppId());
            wxMaConfig.setSecret(loginConfig.getWxMaLoginAppSecret());
            wxMaService.setWxMaConfig(wxMaConfig);
        }

        Users users = usersDao.getByUnionIdOrOpenId(codeResp.getUserInfo().getUnionId(), codeResp.getUserInfo().getOpenid());
        if (ObjectUtil.isNull(users)) {
            // 没绑定
            codeResp.setBindingStatus(false);
            // 缓存用户信息
            if (StringUtils.hasText(codeResp.getUserInfo().getUnionId())) {
                cacheRedis.set(Constants.RedisPre.WX_USER + codeResp.getUserInfo().getUnionId(), codeResp.getUserInfo(), 1, TimeUnit.DAYS);
            } else {
                cacheRedis.set(Constants.RedisPre.WX_USER + codeResp.getUserInfo().getOpenid(), codeResp.getUserInfo(), 1, TimeUnit.DAYS);
            }
            return Result.success(codeResp);
        }

        // 已经绑定
        codeResp.setBindingStatus(true);
        // 登录
        UsersLoginResp loginResp = login(users.getId(), users.getMobile(), LoginStatusEnum.SUCCESS, BeanUtil.copyProperties(req, UsersLog.class));
        codeResp.setMobile(loginResp.getMobile());
        codeResp.setToken(loginResp.getToken());
        return Result.success(codeResp);
    }


    public Result<UsersLoginResp> wxBinding(WxBindingReq req) {
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

        // 手机号重复校验
        Users user = usersDao.getByMobile(req.getMobile());
        if (ObjectUtil.isNotNull(user)) {
            if (StringUtils.hasText(user.getUnionId()) || StringUtils.hasText(user.getOpenId())) {
                return Result.error("该手机号已绑定，请更换其他手机号");
            }
            Users newUser = new Users();
            newUser.setId(user.getId());
            newUser.setUnionId(req.getUnionId());
            newUser.setOpenId(req.getOpenId());
            newUser.setRegisterSource(req.getRegisterSource());
            usersDao.updateById(newUser);

            return Result.success(login(user.getId(), user.getMobile(), LoginStatusEnum.SUCCESS, BeanUtil.copyProperties(req, UsersLog.class)));
        }

        // 用户注册
        user = register(req.getMobile(), IdUtil.fastUUID(), req.getRegisterSource(), req.getUnionId(), req.getOpenId());

        return Result.success(login(user.getId(), user.getMobile(), LoginStatusEnum.REGISTER, BeanUtil.copyProperties(req, UsersLog.class)));
    }


    private UsersLoginResp login(Long userId, String mobile, LoginStatusEnum loginStatusEnum, UsersLog usersLog) {
        // 日志
        log(userId, loginStatusEnum, usersLog);

        UsersLoginResp resp = new UsersLoginResp();
        resp.setMobile(mobile);
        resp.setToken(JwtUtil.create(userId, JwtUtil.DATE));
        // token，放入缓存
        cacheRedis.set(resp.getToken(), userId, 1, TimeUnit.DAYS);
        return resp;
    }

    private void log(Long userId, LoginStatusEnum status, UsersLog record) {
        record.setUserId(userId);
        record.setLoginStatus(status.getCode());
        record.setLoginIp(IpUtil.getIpAddress(request));
        IpUtil.IpInfo ipInfo = getIpInfo(record.getLoginIp());
        if (ObjectUtil.isNotNull(ipInfo)) {
            record.setProvince(ipInfo.getPro());
            record.setCity(ipInfo.getCity());
        }
        usersLogDao.save(record);
    }
}
