package com.roncoo.education.user.service.api.pc.biz;

import java.util.List;
import java.util.concurrent.TimeUnit;

import com.roncoo.education.system.feign.interfaces.IFeignSysMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.roncoo.education.system.feign.vo.SysMenuVO;
import com.roncoo.education.user.common.req.UserLoginPasswordREQ;
import com.roncoo.education.user.common.resq.UserLoginRESQ;
import com.roncoo.education.user.service.dao.PlatformDao;
import com.roncoo.education.user.service.dao.UserDao;
import com.roncoo.education.user.service.dao.UserLogLoginDao;
import com.roncoo.education.user.service.dao.impl.mapper.entity.Platform;
import com.roncoo.education.user.service.dao.impl.mapper.entity.User;
import com.roncoo.education.user.service.dao.impl.mapper.entity.UserLogLogin;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.LoginStatusEnum;
import com.roncoo.education.util.enums.RedisPreEnum;
import com.roncoo.education.util.enums.StatusIdEnum;
import com.roncoo.education.util.tools.JSONUtil;
import com.roncoo.education.util.tools.JWTUtil;
import com.xiaoleilu.hutool.crypto.DigestUtil;
import com.xiaoleilu.hutool.util.CollectionUtil;

@Component
public class PcApiUserLoginBiz {

	@Autowired
	private PlatformDao platformDao;
	@Autowired
	private UserDao userDao;
	@Autowired
	private UserLogLoginDao userLogLoginDao;
	@Autowired
	private IFeignSysMenu feignSysMenu;
	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public Result<UserLoginRESQ> loginPassword(UserLoginPasswordREQ req) {
		if (StringUtils.isEmpty(req.getClientId())) {
			return Result.error("clientId不能为空");
		}
		if (StringUtils.isEmpty(req.getMobile())) {
			return Result.error("手机号不能为空");
		}
		if (StringUtils.isEmpty(req.getPassword())) {
			return Result.error("密码不能为空");
		}
		Platform platform = platformDao.getByClientId(req.getClientId());
		if (null == platform) {
			return Result.error("该平台不存在");
		}
		if (!StatusIdEnum.YES.getCode().equals(platform.getStatusId())) {
			return Result.error("该平台状态异常，请联系管理员");
		}

		// 密码错误次数校验

		// 用户校验
		User user = userDao.getByMobile(req.getMobile());
		if (null == user) {
			return Result.error("账号或者密码不正确");
		}
		// 密码校验
		if (!DigestUtil.sha1Hex(user.getMobileSalt() + req.getPassword()).equals(user.getMobilePsw())) {
			loginLog(user.getUserNo(), req.getClientId(), LoginStatusEnum.FAIL, req.getIp());
			// 放入缓存，错误次数+1
			return Result.error("账号或者密码不正确");
		}
		// 获取用户菜单权限
		List<String> menuList = feignSysMenu.listByUserAndMenu(user.getUserNo());
		if (CollectionUtil.isEmpty(menuList)) {
			return Result.error("没权限！");
		}

		// 登录日志
		loginLog(user.getUserNo(), req.getClientId(), LoginStatusEnum.SUCCESS, req.getIp());

		// 用户菜单存入缓存, 时间

		redisTemplate.opsForValue().set(RedisPreEnum.ADMINI_MENU.getCode().concat(user.getUserNo().toString()), JSONUtil.toJSONString(menuList), 1, TimeUnit.DAYS);

		UserLoginRESQ dto = new UserLoginRESQ();
		dto.setUserNo(user.getUserNo());
		dto.setMobile(user.getMobile());
		dto.setToken(JWTUtil.create(user.getUserNo(), JWTUtil.DATE));

		// 登录成功，存入缓存，单点登录使用
		// redisTemplate.opsForValue().set(dto.getUserNo().toString(), dto.getToken(),
		// 1, TimeUnit.DAYS);

		return Result.success(dto);
	}

	private void loginLog(Long userNo, String clientId, LoginStatusEnum status, String ip) {
		UserLogLogin record = new UserLogLogin();
		record.setUserNo(userNo);
		record.setClientId(clientId);
		record.setLoginStatus(status.getCode());
		record.setLoginIp(ip);
		userLogLoginDao.save(record);
	}
}
