/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.web.boss.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.web.boss.common.bean.qo.SysRoleUserQO;
import com.roncoo.education.web.boss.common.bean.vo.SysRoleUserVO;
import com.roncoo.education.web.boss.service.SysRoleService;
import com.roncoo.education.web.boss.service.SysRoleUserService;

/**
 * 角色用户关联表
 *
 * @author wujing123
 * @since 2018-01-29
 */
@Controller
@RequestMapping(value = "/admin/sysRoleUser")
public class SysRoleUserController extends BaseController {

	private final static String TARGETID = "admin-sysRoleUser";

	@Autowired
	private SysRoleUserService service;

	@Autowired
	private SysRoleService sysRoleService;

	@RequestMapping(value = "/set")
	public void set(@ModelAttribute SysRoleUserQO qo, ModelMap modelMap) {
		List<SysRoleUserVO> list = service.listByUserId(qo.getUserId());
		modelMap.put("roleUserList", list);
		modelMap.put("roleList", sysRoleService.listRoleForUser(qo, list));
		modelMap.put("bean", qo);
	}

	@ResponseBody
	@RequestMapping(value = "/save")
	public String save(@ModelAttribute SysRoleUserQO qo, @RequestParam(value = "ids") String ids) {
		if (service.save(qo, ids) > 0) {
			return success(TARGETID);
		}
		return error("添加失败");
	}

}
