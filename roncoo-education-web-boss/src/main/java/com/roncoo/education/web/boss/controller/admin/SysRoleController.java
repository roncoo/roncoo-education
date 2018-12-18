/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.web.boss.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.web.boss.common.bean.qo.SysRoleQO;
import com.roncoo.education.web.boss.service.SysRoleService;

/**
 * 角色信息
 *
 * @author wujing123
 * @since 2018-01-29
 */
@Controller
@RequestMapping(value = "/admin/sysRole")
public class SysRoleController extends BaseController {

	private final static String TARGETID = "admin-sysRole";

	@Autowired
	private SysRoleService service;

	@RequestMapping(value = "/list")
	public void list(@ModelAttribute SysRoleQO qo, ModelMap modelMap) {
		modelMap.put("page", service.listForPage(qo));
		modelMap.put("bean", qo);
	}

	@RequestMapping(value = "/add")
	public void add(ModelMap modelMap) {
		
	}

	@ResponseBody
	@RequestMapping(value = "/save")
	public String save(@ModelAttribute SysRoleQO qo) {
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
	}

	@ResponseBody
	@RequestMapping(value = "/update")
	public String update(@ModelAttribute SysRoleQO qo) {
		if (service.updateById(qo) > 0) {
			return success(TARGETID);
		}
		return error("修改失败");
	}

	@RequestMapping(value = "/view")
	public void view(@RequestParam(value = "id") Long id, ModelMap modelMap) {
		modelMap.put("bean", service.getById(id));
	}

}
