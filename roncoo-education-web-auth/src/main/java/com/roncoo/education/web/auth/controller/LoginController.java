/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.web.auth.controller;

import java.io.IOException;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.roncoo.education.user.common.bean.vo.PlatformVO;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.ResultEnum;
import com.roncoo.education.web.auth.biz.LoginBiz;
import com.roncoo.education.web.auth.common.bean.LoginQO;
import com.roncoo.education.web.auth.common.bean.TokenVO;

/**
 * 登录功能
 * 
 * @author wujing
 */
@Controller
public class LoginController extends BaseController {

	@Autowired
	private LoginBiz biz;

	/**
	 * 登录页面
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public void authorize(LoginQO loginQO, ModelMap modelMap) {
		logger.warn("授权登录页面：clientId={}, responseType={}, redirectUri={}", loginQO.getClientId(), loginQO.getResponseType(), loginQO.getRedirectUri());
		modelMap.put("bean", loginQO);

		if (!"code".equalsIgnoreCase(loginQO.getResponseType())) {
			modelMap.put("msg", "responseType不正确");
		}
		Result<PlatformVO> result = biz.checkPlatform(loginQO.getClientId());
		if (!ResultEnum.SUCCESS.getCode().equals(result.getCode())) {
			modelMap.put("msg", result.getMsg());
		}
	}

	/**
	 * 登录功能
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, ModelMap modelMap) {
		TreeMap<String, Object> paramMap = getParamMap(request);
		logger.warn("授权登录功能：request={}", paramMap);
		modelMap.put("bean", paramMap);
		Result<String> result = biz.login(request, paramMap);
		if (ResultEnum.SUCCESS.getCode().equals(result.getCode())) {
			// 登录成功，进行重定向
			return redirect(result.getData());
		}
		modelMap.put("msg", result.getMsg());
		return "login";
	}

	/**
	 * 用户信息获取
	 */
	@RequestMapping(value = "/token", method = RequestMethod.POST)
	@ResponseBody
	public Result<TokenVO> sendMobileCode(@RequestParam(name = "clientId", defaultValue = "") String clientId, @RequestParam(name = "clientSecret", defaultValue = "") String clientSecret, @RequestParam(name = "code", defaultValue = "") String code) {
		return biz.token(clientId, clientSecret, code);
	}

	/**
	 * 随机验证码
	 */
	@RequestMapping(value = "/code/{time}", method = RequestMethod.GET)
	public void create(HttpSession session, HttpServletResponse response, @PathVariable("time") String time) throws IOException {
		biz.createCode(session, response);
	}
}
