/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.web.boss.controller.admin;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.enums.StatusIdEnum;
import com.roncoo.education.util.enums.UserTypeEnum;
import com.roncoo.education.util.tools.Constants;
import com.roncoo.education.util.tools.JSONUtil;
import com.roncoo.education.web.boss.common.bean.qo.SysUserQO;
import com.roncoo.education.web.boss.common.bean.vo.SysUserVO;
import com.roncoo.education.web.boss.service.SysUserService;

/**
 * 后台用户信息
 *
 * @author wujing123
 * @since 2018-01-29
 */
@Controller
@RequestMapping(value = "/admin/sysUser")
public class SysUserController extends BaseController {

	private final static String TARGETID = "admin-sysUser";

	@Autowired
	private SysUserService service;

	/**
	 * 重新登录功能
	 */
	@RequestMapping(value = "/login")
	public String login(@RequestParam(value = "id") Long id, ModelMap modelMap) {
		SysUserVO sysUser = service.getById(id);
		if (sysUser == null) {
			modelMap.put("msg", "用户不存在");
			return "error";
		}
		if (sysUser.getStatusId().equals(StatusIdEnum.NO.getCode())) {
			modelMap.put("msg", "用户状态已禁用");
			return "error";
		}
		SecurityUtils.getSubject().getSession().setAttribute(Constants.Session.USER_NO, sysUser.getUserNo());
		SecurityUtils.getSubject().getSession().setAttribute(Constants.Session.USER_VO, JSONUtil.toJSONString(sysUser));
		return redirect("/index");
	}

	/**
	 * 设置分成
	 */
	@RequestMapping(value = "/proportion")
	public void proportion(@RequestParam(value = "id") Long id, ModelMap modelMap) {
		modelMap.put("bean", service.getById(id));
	}

	@RequestMapping(value = "/orgSysUserList")
	public void orgSysUserList(@ModelAttribute SysUserQO qo, ModelMap modelMap) {
		modelMap.put("page", service.listForPage(qo));
		modelMap.put("bean", qo);
	}

	@RequestMapping(value = "/list")
	public void list(@ModelAttribute SysUserQO qo, ModelMap modelMap) {
		modelMap.put("page", service.listForPage(qo));
		modelMap.put("bean", qo);
		modelMap.put("userTypeEnums", UserTypeEnum.values());
	}

	@RequestMapping(value = "/add")
	public void add(ModelMap modelMap) {
		// 获取机构信息
		modelMap.put("userTypeEnums", UserTypeEnum.values());
	}

	@ResponseBody
	@RequestMapping(value = "/save")
	public String save(@ModelAttribute SysUserQO qo) {
		if (service.save(qo) > 0) {
			return success(TARGETID);
		}
		return error("添加失败");
	}

	@ResponseBody
	@RequestMapping(value = "/delete")
	public String delete(@RequestParam(value = "id") Long id) {
		if (service.deleteById(id) > 0) {
			return delete(TARGETID);
		}
		return error("删除失败");
	}

	@RequestMapping(value = "/edit")
	public void edit(@RequestParam(value = "id") Long id, ModelMap modelMap) {
		modelMap.put("bean", service.getById(id));
		modelMap.put("userTypeEnums", UserTypeEnum.values());
	}

	@ResponseBody
	@RequestMapping(value = "/update")
	public String update(@ModelAttribute SysUserQO qo) {
		if (service.updateById(qo) > 0) {
			return success(TARGETID);
		}
		return error("修改失败");
	}

	@ResponseBody
	@RequestMapping(value = "/updateBypassword")
	public String updateBypassword(@ModelAttribute SysUserQO qo) {
		if (service.updateBypassword(qo) > 0) {
			return success(TARGETID);
		}
		return error("更新失败");
	}

	@RequestMapping(value = "/password")
	public void password(@RequestParam(value = "userNo") Long userNo, ModelMap modelMap) {
		modelMap.put("userNo", userNo);
	}

	@RequestMapping(value = "/view")
	public void view(@RequestParam(value = "userNo") Long userNo, @RequestParam(value = "orgNo") String orgNo, ModelMap modelMap) {
		modelMap.put("bean", service.getByUserNoAndOrgNo(userNo, orgNo));
	}

}
