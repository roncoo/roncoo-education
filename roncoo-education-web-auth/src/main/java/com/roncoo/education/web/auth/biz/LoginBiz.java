/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.web.auth.biz;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;

import com.roncoo.education.user.common.bean.qo.UserLogLoginQO;
import com.roncoo.education.user.common.bean.vo.PlatformVO;
import com.roncoo.education.user.common.bean.vo.UserVO;
import com.roncoo.education.user.feign.web.IBossPlatform;
import com.roncoo.education.user.feign.web.IBossUser;
import com.roncoo.education.user.feign.web.IBossUserLogLogin;
import com.roncoo.education.util.base.Base;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.LoginStatusEnum;
import com.roncoo.education.util.enums.StatusIdEnum;
import com.roncoo.education.util.tools.BeanUtil;
import com.roncoo.education.util.tools.StrUtil;
import com.roncoo.education.web.auth.common.bean.TokenVO;
import com.xiaoleilu.hutool.crypto.DigestUtil;
import com.xiaoleilu.hutool.http.HttpUtil;

public class LoginBiz extends Base {

	private static final int width = 60;// 图像宽度
	private static final int height = 20;// 图像高度
	private static final String CODE = "roncoo-education-web-auth";// 图像高度

	@Autowired
	private IBossPlatform bossPlatform;

	@Autowired
	private IBossUser bossUser;

	@Autowired
	private IBossUserLogLogin bossUserLogLogin;

	@Autowired
	private RedisTemplate<String, String> redisTemplate;

	public Result<PlatformVO> checkPlatform(String clientId) {
		PlatformVO vo = bossPlatform.getByClientId(clientId);
		if (vo == null || !StatusIdEnum.YES.getCode().equals(vo.getStatusId())) {
			return Result.error("该平台状态异常，请联系管理员");
		}
		return Result.success(vo);
	}

	public Result<String> login(HttpServletRequest request, TreeMap<String, Object> paramMap) {
		PlatformVO platformVO = bossPlatform.getByClientId(paramMap.get("clientId").toString());
		if (platformVO == null || !StatusIdEnum.YES.getCode().equals(platformVO.getStatusId())) {
			return Result.error("该平台状态异常，请联系管理员");
		}

		UserVO userVO = bossUser.getByMobile(getString(paramMap, "mobile"));
		if (userVO == null || !StatusIdEnum.YES.getCode().equals(userVO.getStatusId())) {
			return Result.error("状态异常，请联系管理员");
		}

		String argPassword = DigestUtil.sha1Hex(userVO.getMobileSalt() + paramMap.get("password"));
		if (!argPassword.equals(userVO.getMobilePsw())) {
			// 记录登录错误日志
			userLogLogin(userVO.getUserNo(), platformVO.getClientId(), LoginStatusEnum.FAIL, HttpUtil.getClientIP(request));
			return Result.error("账号或者密码不正确");
		}

		// 记录登录成功日志
		userLogLogin(userVO.getUserNo(), platformVO.getClientId(), LoginStatusEnum.SUCCESS, HttpUtil.getClientIP(request));

		// 获取重定向地址
		return Result.success(getRedirectUri(userVO.getUserNo(), paramMap.get("redirectUri").toString()));
	}

	public Result<TokenVO> token(String clientId, String clientSecret, String code) {
		PlatformVO platformVO = bossPlatform.getByClientId(clientId);
		if (!StatusIdEnum.YES.getCode().equals(platformVO.getStatusId())) {
			return Result.error("该平台状态异常，请联系管理员");
		}

		if (!platformVO.getClientSecret().equalsIgnoreCase(clientSecret)) {
			return Result.error("clientSecret不正确");
		}

		String userNo = redisTemplate.opsForValue().get(code);
		if (!StringUtils.hasText(userNo)) {
			return Result.error("code错误或者过期");
		}

		UserVO userVO = bossUser.getByUserNo(Long.valueOf(userNo));
		if (userVO == null) {
			return Result.error("账号不存在");
		}
		return Result.success(BeanUtil.copyProperties(userVO, TokenVO.class));
	}

	private String getRedirectUri(Long userNo, String redirectUri) {
		String code = StrUtil.get32UUID();
		// code存入缓存：30分钟有效
		redisTemplate.opsForValue().set(code, userNo.toString(), 30, TimeUnit.MINUTES);
		if (redirectUri.contains("?")) {
			redirectUri = redirectUri + "&code=" + code;
		} else {
			redirectUri = redirectUri + "?code=" + code;
		}
		return redirectUri;
	}

	private void userLogLogin(Long userNO, String clientId, LoginStatusEnum loginStatus, String ip) {
		UserLogLoginQO qo = new UserLogLoginQO();
		qo.setUserNo(userNO);
		qo.setClientId(clientId);
		qo.setLoginStatus(loginStatus.getCode());
		qo.setLoginIp(ip);
		bossUserLogLogin.save(qo);
	}

	public void createCode(HttpSession session, HttpServletResponse response) throws IOException {
		response.setContentType("image/jpeg");
		ServletOutputStream out = response.getOutputStream();
		// 准备缓冲图像,不支持表单
		BufferedImage bimg = new BufferedImage(width, height, BufferedImage.TYPE_INT_BGR);
		Random r = new Random();
		// 获取图形上下文环境
		Graphics gc = bimg.getGraphics();
		// 设定背景色并进行填充
		gc.setColor(getRandColor(200, 250));
		gc.fillRect(0, 0, width, height);
		// 设置图形上下文环境字体
		gc.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		// 随机产生200条干扰线条，使图像中的认证码不易被其他分析程序探测到
		gc.setColor(getRandColor(160, 200));
		for (int i = 0; i < 200; i++) {
			int x1 = r.nextInt(width);
			int y1 = r.nextInt(height);
			int x2 = r.nextInt(15);
			int y2 = r.nextInt(15);
			gc.drawLine(x1, y1, x1 + x2, y1 + y2);
		}
		// 随机产生100个干扰点，使图像中的验证码不易被其他分析程序探测到
		gc.setColor(getRandColor(120, 240));
		for (int i = 0; i < 100; i++) {
			int x = r.nextInt(width);
			int y = r.nextInt(height);
			gc.drawOval(x, y, 0, 0);
		}

		// 随机产生4个数字的验证码
		String rs = "";
		String rn = "";
		for (int i = 0; i < 4; i++) {
			rn = String.valueOf(r.nextInt(10));
			rs += rn;
			gc.setColor(new Color(20 + r.nextInt(110), 20 + r.nextInt(110), 20 + r.nextInt(110)));
			gc.drawString(rn, 13 * i + 5, 17);
		}

		// 释放图形上下文环境
		gc.dispose();
		ImageIO.write(bimg, "jpg", out);
		out.flush();
		out.close();
		session.setAttribute(CODE, rs);
	}

	private static Color getRandColor(int fc, int bc) {
		Random r = new Random();
		if (fc > 255) {
			fc = 255;
		}
		if (bc > 255) {
			bc = 255;
		}
		int red = fc + r.nextInt(bc - fc);// 红
		int green = fc + r.nextInt(bc - fc);// 绿
		int blue = fc + r.nextInt(bc - fc);// 蓝
		return new Color(red, green, blue);
	}

}
