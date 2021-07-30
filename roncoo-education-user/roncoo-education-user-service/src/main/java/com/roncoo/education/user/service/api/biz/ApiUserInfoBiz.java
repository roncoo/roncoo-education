package com.roncoo.education.user.service.api.biz;

import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.RSA;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import com.aliyuncs.exceptions.ClientException;
import com.roncoo.education.system.feign.interfaces.IFeignSys;
import com.roncoo.education.system.feign.vo.SysVO;
import com.roncoo.education.user.common.bo.UserLoginCodeBO;
import com.roncoo.education.user.common.bo.UserLoginPasswordBO;
import com.roncoo.education.user.common.bo.UserRegisterBO;
import com.roncoo.education.user.common.bo.UserSendCodeBO;
import com.roncoo.education.user.common.bo.auth.UserUpdateBO;
import com.roncoo.education.user.common.dto.UserLoginDTO;
import com.roncoo.education.user.service.dao.*;
import com.roncoo.education.user.service.dao.impl.mapper.entity.*;
import com.roncoo.education.util.aliyun.Aliyun;
import com.roncoo.education.util.aliyun.AliyunUtil;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.BaseException;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.*;
import com.roncoo.education.util.tools.*;
import com.xiaoleilu.hutool.crypto.DigestUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import com.xiaoleilu.hutool.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

/**
 * 用户基本信息
 *
 * @author wujing
 */
@Component
public class ApiUserInfoBiz extends BaseBiz {

    @Autowired
    private IFeignSys bossSys;

    @Autowired
    private PlatformDao platformDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserExtDao userExtDao;
    @Autowired
    private SendSmsLogDao sendSmsLogDao;
    @Autowired
    private UserLogLoginDao userLogLoginDao;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Transactional
    public Result<UserLoginDTO> register(UserRegisterBO userRegisterBO) {
        if (StringUtils.isEmpty(userRegisterBO.getMobile())) {
            return Result.error("手机号不能为空");
        }
        if (StringUtils.isEmpty(userRegisterBO.getPassword())) {
            return Result.error("密码不能为空");
        }
        if (StringUtils.isEmpty(userRegisterBO.getClientId())) {
            return Result.error("clientId不能为空");
        }

        // 密码校验
        if (!userRegisterBO.getPassword().equals(userRegisterBO.getRepassword())) {
            return Result.error("2次密码不一致");
        }

        Platform platform = platformDao.getByClientId(userRegisterBO.getClientId());
        if (null == platform) {
            return Result.error("该平台不存在");
        }
        if (!StatusIdEnum.YES.getCode().equals(platform.getStatusId())) {
            return Result.error("该平台状态异常，请联系管理员");
        }

        // 验证码校验
//		String redisSmsCode = redisTemplate.opsForValue().get(platform.getClientId() + userRegisterBO.getMobile());
//		if (StringUtils.isEmpty(redisSmsCode)) {
//			return Result.error("请输入验证码");
//		}
//		if (!redisSmsCode.equals(userRegisterBO.getCode())) {
//			return Result.error("验证码不正确，请重新输入");
//		}

        // 手机号重复校验
        User user = userDao.getByMobile(userRegisterBO.getMobile());
        if (null != user) {
            return Result.error("该手机号已经注册，请更换手机号");
        }

        // 用户注册
        user = register(userRegisterBO.getMobile(), userRegisterBO.getPassword(), platform.getClientId());

        // 同步数据到演示环境
        try {
            // 密码加密
            String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCzBzD+Sgi5z+zq8P/qMimssuozcjdx4pBu2U2OKf6bDdQEKmSB1KXCheG2m19+xnerIZ22fr8nHc0r1qIKIUrJ0UWU8qXkANe4u0wW0V52BsW6H02Jmc1mFjrYcOCpToVFGvLm17AgP6tNBAiTjiiBc0V+prAZ9ixrC5aPsI4gAwIDAQAB";
            RSA rsa = new RSA(null, publicKey);
            userRegisterBO.setPassword(rsa.encryptBase64(userRegisterBO.getPassword(), KeyType.PublicKey));
            userRegisterBO.setRepassword(rsa.encryptBase64(userRegisterBO.getRepassword(), KeyType.PublicKey));
            String jsonStr = JSONUtil.toJsonStr(userRegisterBO);
            logger.warn("-----------------同步注册参数:{}", jsonStr);
            String post = HttpUtil.post("http://demo.edu.roncoo.net/gateway/user/api/user/register", jsonStr);
            logger.warn("-----------------同步注册结果:{}", post);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("同步数据异常:{}", e.getMessage());
        }

        UserLoginDTO dto = new UserLoginDTO();
        dto.setUserNo(user.getUserNo());
        dto.setMobile(user.getMobile());
        dto.setToken(JWTUtil.create(user.getUserNo(), JWTUtil.DATE));
        return Result.success(dto);
    }

    public Result<UserLoginDTO> loginPassword(UserLoginPasswordBO userLoginPasswordBO) {
        if (StringUtils.isEmpty(userLoginPasswordBO.getClientId())) {
            return Result.error("clientId不能为空");
        }
        if (StringUtils.isEmpty(userLoginPasswordBO.getMobile())) {
            return Result.error("手机号不能为空");
        }
        if (StringUtils.isEmpty(userLoginPasswordBO.getPassword())) {
            return Result.error("密码不能为空");
        }
        Platform platform = platformDao.getByClientId(userLoginPasswordBO.getClientId());
        if (null == platform) {
            return Result.error("该平台不存在");
        }
        if (!StatusIdEnum.YES.getCode().equals(platform.getStatusId())) {
            return Result.error("该平台状态异常，请联系管理员");
        }

        // 密码错误次数校验

        // 用户校验
        User user = userDao.getByMobile(userLoginPasswordBO.getMobile());
        if (null == user) {
            return Result.error("账号或者密码不正确");
        }
        // 密码校验
        if (!DigestUtil.sha1Hex(user.getMobileSalt() + userLoginPasswordBO.getPassword()).equals(user.getMobilePsw())) {
            loginLog(user.getUserNo(), userLoginPasswordBO.getClientId(), LoginStatusEnum.FAIL, userLoginPasswordBO.getIp());
            // 放入缓存，错误次数+1
            return Result.error("账号或者密码不正确");
        }

        // 登录日志
        loginLog(user.getUserNo(), userLoginPasswordBO.getClientId(), LoginStatusEnum.SUCCESS, userLoginPasswordBO.getIp());

        UserLoginDTO dto = new UserLoginDTO();
        dto.setUserNo(user.getUserNo());
        dto.setMobile(user.getMobile());
        dto.setToken(JWTUtil.create(user.getUserNo(), JWTUtil.DATE));

        // 登录成功，存入缓存，单点登录使用
        // redisTemplate.opsForValue().set(dto.getUserNo().toString(), dto.getToken(),
        // 1, TimeUnit.DAYS);

        return Result.success(dto);
    }

    public Result<UserLoginDTO> loginCode(UserLoginCodeBO userLoginCodeBO) {
        if (StringUtils.isEmpty(userLoginCodeBO.getClientId())) {
            return Result.error("clientId不能为空");
        }
        if (StringUtils.isEmpty(userLoginCodeBO.getMobile())) {
            return Result.error("手机号码不能为空");
        }
        Platform platform = platformDao.getByClientId(userLoginCodeBO.getClientId());
        if (ObjectUtil.isNull(platform)) {
            return Result.error("该平台不存在");
        }
        if (!StatusIdEnum.YES.getCode().equals(platform.getStatusId())) {
            return Result.error("该平台状态异常，请联系管理员");
        }

        // 登录错误次数的校验

        // 验证码校验
        String redisSmsCode = redisTemplate.opsForValue().get(platform.getClientId() + userLoginCodeBO.getMobile());
        if (StringUtils.isEmpty(redisSmsCode)) {
            return Result.error("验证码已经过期，请重新获取");
        }

        User user = userDao.getByMobile(userLoginCodeBO.getMobile());
        if (null == user) {
            return Result.error("该用户不存在");
        }

        if (!redisSmsCode.equals(userLoginCodeBO.getCode())) {
            loginLog(user.getUserNo(), userLoginCodeBO.getClientId(), LoginStatusEnum.FAIL, userLoginCodeBO.getIp());
            // 缓存控制错误次数
            return Result.error("验证码不正确,重新输入");
        }

        // 清空缓存
        redisTemplate.delete(platform.getClientId() + userLoginCodeBO.getMobile());

        // 登录日志
        loginLog(user.getUserNo(), userLoginCodeBO.getClientId(), LoginStatusEnum.SUCCESS, userLoginCodeBO.getIp());

        UserLoginDTO dto = new UserLoginDTO();
        dto.setUserNo(user.getUserNo());
        dto.setMobile(user.getMobile());
        dto.setToken(JWTUtil.create(user.getUserNo(), JWTUtil.DATE));

        // 登录成功，存入缓存，单点登录使用
        // redisTemplate.opsForValue().set(dto.getUserNo().toString(), dto.getToken(),
        // 1, TimeUnit.DAYS);
        return Result.success(dto);
    }

    public Result<String> sendCode(UserSendCodeBO userSendCodeBO) {
        if (StringUtils.isEmpty(userSendCodeBO.getClientId())) {
            return Result.error("clientId不能为空");
        }
        if (!Pattern.compile(Constants.REGEX_MOBILE).matcher(userSendCodeBO.getMobile()).matches()) {
            return Result.error("手机号码格式不正确");
        }

        Platform platform = platformDao.getByClientId(userSendCodeBO.getClientId());
        if (ObjectUtil.isNull(platform)) {
            return Result.error("该平台不存在");
        }
        if (!StatusIdEnum.YES.getCode().equals(platform.getStatusId())) {
            return Result.error("该平台状态异常，请联系管理员");
        }

        SysVO sys = bossSys.getSys();
        if (ObjectUtil.isNull(sys)) {
            return Result.error("找不到系统配置信息");
        }
        // 创建日志实例
        SendSmsLog sendSmsLog = new SendSmsLog();
        sendSmsLog.setMobile(userSendCodeBO.getMobile());
        sendSmsLog.setTemplate(sys.getSmsCode());
        // 随机生成验证码
        sendSmsLog.setSmsCode(RandomUtil.randomNumbers(6));
        try {
            // 发送验证码
            boolean result = AliyunUtil.sendMsg(userSendCodeBO.getMobile(), sendSmsLog.getSmsCode(), BeanUtil.copyProperties(sys, Aliyun.class));
            // 发送成功，验证码存入缓存：5分钟有效
            if (result) {
                redisTemplate.opsForValue().set(userSendCodeBO.getClientId() + userSendCodeBO.getMobile(), sendSmsLog.getSmsCode(), 5, TimeUnit.MINUTES);
                sendSmsLog.setIsSuccess(IsSuccessEnum.SUCCESS.getCode());
                sendSmsLogDao.save(sendSmsLog);
                return Result.success("发送成功");
            }
            // 发送失败
            sendSmsLog.setIsSuccess(IsSuccessEnum.FAIL.getCode());
            sendSmsLogDao.save(sendSmsLog);
            throw new BaseException("发送失败");
        } catch (ClientException e) {
            sendSmsLog.setIsSuccess(IsSuccessEnum.FAIL.getCode());
            sendSmsLogDao.save(sendSmsLog);
            logger.error("发送失败，原因={}", e.getErrMsg());
            return Result.error("发送失败");
        }
    }

    private User register(String mobile, String password, String clientId) {
        // 用户基本信息
        User user = new User();
        user.setUserNo(NOUtil.getUserNo());
        user.setMobile(mobile);
        user.setMobileSalt(StrUtil.get32UUID());
        user.setMobilePsw(DigestUtil.sha1Hex(user.getMobileSalt() + password));
        user.setUserSource(clientId);
        userDao.save(user);

        // 用户其他信息
        UserExt userExt = new UserExt();
        userExt.setUserNo(user.getUserNo());
        userExt.setUserType(UserTypeEnum.USER.getCode());
        userExt.setMobile(user.getMobile());
        userExtDao.save(userExt);

        return user;
    }

    private void loginLog(Long userNo, String clientId, LoginStatusEnum status, String ip) {
        UserLogLogin record = new UserLogLogin();
        record.setUserNo(userNo);
        record.setClientId(clientId);
        record.setLoginStatus(status.getCode());
        record.setLoginIp(ip);
        userLogLoginDao.save(record);
    }

    public Result<Integer> updatePassword(UserUpdateBO userUpdateBO) {
        if (StringUtils.isEmpty(userUpdateBO.getMobile())) {
            return Result.error("手机号为空,请重试");
        }
        if (StringUtils.isEmpty(userUpdateBO.getCode())) {
            return Result.error("验证码不能为空");
        }
        if (StringUtils.isEmpty(userUpdateBO.getClientId())) {
            return Result.error("clientId不能为空");
        }

        Platform platform = platformDao.getByClientId(userUpdateBO.getClientId());
        if (null == platform) {
            return Result.error("该平台不存在");
        }
        if (!StatusIdEnum.YES.getCode().equals(platform.getStatusId())) {
            return Result.error("该平台状态异常，请联系管理员");
        }

        String redisCode = redisTemplate.opsForValue().get(platform.getClientId() + userUpdateBO.getMobile());
        if (StringUtils.isEmpty(redisCode)) {
            return Result.error("请输入验证码");
        }
        if (!userUpdateBO.getCode().equals(redisCode)) {
            return Result.error("验证码匹配不正确");
        }
        // 手机号去空处理
        String mobile = userUpdateBO.getMobile().replaceAll(" +", "");

        if (StringUtils.isEmpty(userUpdateBO.getConfirmPassword())) {
            return Result.error("新登录密码为空,请重试");
        }
        if (!userUpdateBO.getNewPassword().equals(userUpdateBO.getConfirmPassword())) {
            return Result.error("密码输入不一致，请重试");
        }

        User user = userDao.getByMobile(mobile);
        if (ObjectUtil.isNull(user)) {
            return Result.error("没找到用户信息,请重试");
        }
        if (DigestUtil.sha1Hex(user.getMobileSalt() + userUpdateBO.getNewPassword()).equals(user.getMobilePsw())) {
            return Result.error("输入的密码与原密码一致,请重试");
        }

        // 更新密码
        User bean = new User();
        bean.setId(user.getId());
        bean.setMobileSalt(StrUtil.get32UUID());
        bean.setMobilePsw(DigestUtil.sha1Hex(bean.getMobileSalt() + userUpdateBO.getNewPassword()));
        int result = userDao.updateById(bean);
        return result == 1 ? Result.success(result) : Result.error(ResultEnum.USER_UPDATE_FAIL.getDesc());
    }

}
