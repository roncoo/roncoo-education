/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.web.boss.controller;

import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.abel533.echarts.Option;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.BaseException;
import com.roncoo.education.util.tools.Constants;
import com.roncoo.education.util.tools.JSONUtil;
import com.roncoo.education.web.boss.biz.IndexBiz;
import com.roncoo.education.web.boss.common.bean.vo.SysMenuVO;
import com.roncoo.education.web.boss.service.SysMenuService;

@Controller
public class IndexController extends BaseController {

	@Autowired
	private SysMenuService sysMenuService;
	
	@Autowired
	private IndexBiz indexBiz;

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public void index(ModelMap modelMap) {
		Long userNo = Long.valueOf(SecurityUtils.getSubject().getSession().getAttribute(Constants.Session.USER_NO).toString());
		List<SysMenuVO> menuVOList = sysMenuService.listByUser(userNo);
		if (menuVOList == null || menuVOList.isEmpty()) {
			throw new BaseException("没权限");
		}
		modelMap.put("menuList", menuVOList);
		SecurityUtils.getSubject().getSession(false).setAttribute(Constants.Session.BOSS_MENU, JSONUtil.toJSONString(menuVOList));
	}

	/**
	 * 进入首页
	 */
	@RequestMapping(value = "/index", method = RequestMethod.POST)
	public String postIndex() {
		return redirect("/index");
	}

	@RequestMapping("/content")
	public void context(ModelMap modelMap) {
		modelMap.put("javaVersion", System.getProperty("java.version"));
		modelMap.put("osName", System.getProperty("os.name"));
	}

	/**
	 * 图表
	 * 
	 * @param modelMap
	 * @return
	 * @author wuyun
	 */
	@ResponseBody
	@RequestMapping(value = "/orderUser", method = RequestMethod.GET)
	public Option orderUser() {
		return indexBiz.orderUser();
	}
}
