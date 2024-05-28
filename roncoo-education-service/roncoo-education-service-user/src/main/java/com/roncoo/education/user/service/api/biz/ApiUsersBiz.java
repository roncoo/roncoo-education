package com.roncoo.education.user.service.api.biz;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.impl.WxMaServiceImpl;
import cn.binarywang.wx.miniapp.config.impl.WxMaDefaultConfigImpl;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.crypto.digest.DigestUtil;
import cn.hutool.extra.servlet.ServletUtil;
import com.roncoo.education.common.cache.CacheRedis;
import com.roncoo.education.common.core.base.BaseException;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.LoginAuthTypeEnum;
import com.roncoo.education.common.core.enums.LoginStatusEnum;
import com.roncoo.education.common.core.tools.*;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.common.sms.SmsUtil;
import com.roncoo.education.system.feign.interfaces.IFeignSysConfig;
import com.roncoo.education.system.feign.interfaces.vo.LoginConfig;
import com.roncoo.education.user.dao.LogLoginDao;
import com.roncoo.education.user.dao.UsersAccountDao;
import com.roncoo.education.user.dao.UsersDao;
import com.roncoo.education.user.dao.impl.mapper.entity.LogLogin;
import com.roncoo.education.user.dao.impl.mapper.entity.Users;
import com.roncoo.education.user.dao.impl.mapper.entity.UsersAccount;
import com.roncoo.education.user.service.api.req.*;
import com.roncoo.education.user.service.api.resp.UsersLoginResp;
import com.roncoo.education.user.service.api.resp.WxCodeResp;
import lombok.RequiredArgsConstructor;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.service.WxOAuth2Service;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.impl.WxMpMapConfigImpl;
import me.chanjar.weixin.open.api.impl.WxOpenInMemoryConfigStorage;
import me.chanjar.weixin.open.api.impl.WxOpenOAuth2ServiceImpl;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
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
    private final UsersDao userDao;
    @NotNull
    private final UsersAccountDao usersAccountDao;
    @NotNull
    private final LogLoginDao logLoginDao;
    @NotNull
    private final CacheRedis cacheRedis;
    @NotNull
    private final IFeignSysConfig feignSysConfig;
    @NotNull
    private final HttpServletRequest request;

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
        Users user = userDao.getByMobile(req.getMobile());
        if (null != user) {
            return Result.error("该手机号已经注册，请更换手机号");
        }

        // 用户注册
        user = register(req.getMobile(), mobilePsw);

        // 日志
        log(user.getId(), LoginStatusEnum.REGISTER, BeanUtil.copyProperties(req, LogLogin.class));

        UsersLoginResp dto = new UsersLoginResp();
        dto.setMobile(user.getMobile());
        dto.setToken(JwtUtil.create(user.getId(), JwtUtil.DATE));

        // token，放入缓存
        cacheRedis.set(dto.getToken(), user.getId(), 1, TimeUnit.DAYS);
        return Result.success(dto);
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
        Users user = userDao.getByMobile(req.getMobile());
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
            log(user.getId(), LoginStatusEnum.FAIL, BeanUtil.copyProperties(req, LogLogin.class));
            return Result.error("账号或者密码不正确");
        }

        // 成功登录日志
        log(user.getId(), LoginStatusEnum.SUCCESS, BeanUtil.copyProperties(req, LogLogin.class));

        UsersLoginResp dto = new UsersLoginResp();
        dto.setMobile(user.getMobile());
        dto.setToken(JwtUtil.create(user.getId(), JwtUtil.DATE));

        // token，放入缓存
        cacheRedis.set(dto.getToken(), user.getId(), 1, TimeUnit.DAYS);
        return Result.success(dto);
    }

    private String decrypt(String password) {
        String privateKey = cacheRedis.get(Constants.RedisPre.PRIVATEKEY);
        if (StringUtils.isEmpty(privateKey)) {
            privateKey = feignSysConfig.getLogin().getRsaLoginPrivateKey();
            cacheRedis.set(Constants.RedisPre.PRIVATEKEY, privateKey, 1, TimeUnit.DAYS);
        }
        return RsaUtil.decrypt(password, privateKey);
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

        // 用户账户
        UsersAccount usersAccount = new UsersAccount();
        usersAccount.setUserId(user.getId());
        usersAccount.setAvailableAmount(BigDecimal.ZERO);
        usersAccount.setFreezeAmount(BigDecimal.ZERO);
        usersAccount.setSign(Md5Util.md5(usersAccount.getUserId().toString(), usersAccount.getAvailableAmount().toPlainString(), usersAccount.getFreezeAmount().toPlainString()));
        usersAccountDao.save(usersAccount);
        return user;
    }

    private void log(Long userId, LoginStatusEnum status, LogLogin record) {
        record.setUserId(userId);
        record.setLoginStatus(status.getCode());
        record.setLoginIp(ServletUtil.getClientIP(request));
        IpUtil.IpInfo ipInfo = getIpInfo(record.getLoginIp());
        if (ObjectUtil.isNotNull(ipInfo)) {
            record.setProvince(ipInfo.getPro());
            record.setCity(ipInfo.getCity());
        }
        logLoginDao.save(record);
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

        if (SmsUtil.sendVerCode(req.getMobile(), code, feignSysConfig.getSms())) {
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

        if (!StringUtils.hasText(req.getMobilePwdEncrypt())) {
            return Result.error("密码不能为空");
        }
        // 解密
        String mobilePsw = decrypt(req.getMobilePwdEncrypt());
        if (!StringUtils.hasText(mobilePsw)) {
            return Result.error("密码不能为空");
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
        recorde.setMobilePsw(DigestUtil.sha1Hex(recorde.getMobileSalt() + mobilePsw));
        userDao.updateById(recorde);

        return Result.success("重置成功");
    }

    public Result<String> wxLogin(WxLoginReq req) {
        LoginConfig loginConfig = feignSysConfig.getLogin();
        if (req.getLoginAuthType().equals(LoginAuthTypeEnum.PC.getCode())) {
            if (!loginConfig.getWxPcLoginEnable().equals("1")) {
                return Result.error("网页应用登录没开启");
            }
            WxOAuth2Service wxOAuth2Service = new WxOpenOAuth2ServiceImpl(loginConfig.getWxPcLoginAppId(), loginConfig.getWxPcLoginAppSecret(), new WxOpenInMemoryConfigStorage());
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
            WxOAuth2Service wxOAuth2Service = new WxOpenOAuth2ServiceImpl(loginConfig.getWxPcLoginAppId(), loginConfig.getWxPcLoginAppSecret(), new WxOpenInMemoryConfigStorage());
            WxOAuth2AccessToken accessToken = wxOAuth2Service.getAccessToken(req.getCode());
            codeResp.setAuthInfo(getAuthInfo(wxOAuth2Service, accessToken));
        } else if (req.getLoginAuthType().equals(LoginAuthTypeEnum.MP.getCode())) {
            // 公众号
            WxMpService wxMpService = new WxMpServiceImpl();
            WxMpMapConfigImpl mpMapConfig = new WxMpMapConfigImpl();
            mpMapConfig.setAppId(loginConfig.getWxMpLoginAppId());
            mpMapConfig.setSecret(loginConfig.getWxMpLoginAppSecret());
            wxMpService.setWxMpConfigStorage(mpMapConfig);
            WxOAuth2AccessToken accessToken = wxMpService.getOAuth2Service().getAccessToken(req.getCode());
            codeResp.setAuthInfo(getAuthInfo(wxMpService.getOAuth2Service(), accessToken));
        } else if (req.getLoginAuthType().equals(LoginAuthTypeEnum.MA.getCode())) {
            // 小程序
            WxMaService wxMaService = new WxMaServiceImpl();
            WxMaDefaultConfigImpl wxMaConfig = new WxMaDefaultConfigImpl();
            wxMaConfig.setAppid(loginConfig.getWxMaLoginAppId());
            wxMaConfig.setSecret(loginConfig.getWxMaLoginAppSecret());
            wxMaService.setWxMaConfig(wxMaConfig);
        }
        return Result.success(codeResp);
    }


    private WxCodeResp.AuthInfo getAuthInfo(WxOAuth2Service wxOAuth2Service, WxOAuth2AccessToken accessToken) throws WxErrorException {
        WxOAuth2UserInfo userInfo = wxOAuth2Service.getUserInfo(accessToken, null);
        WxCodeResp.AuthInfo authInfo = new WxCodeResp.AuthInfo();
        authInfo.setUnionId(userInfo.getUnionId());
        authInfo.setHeadImg(userInfo.getHeadImgUrl());
        authInfo.setNickname(userInfo.getNickname());
        authInfo.setGender(userInfo.getSex());
        return authInfo;
    }
}
