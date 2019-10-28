package com.roncoo.education.system.service.api.pc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.system.service.api.pc.biz.PcApiSysMenuBiz;
import com.roncoo.education.system.common.req.SysMenuDeleteREQ;
import com.roncoo.education.system.common.req.SysMenuListREQ;
import com.roncoo.education.system.common.req.SysMenuSaveREQ;
import com.roncoo.education.system.common.req.SysMenuUpdateREQ;
import com.roncoo.education.system.common.req.SysMenuUserListREQ;
import com.roncoo.education.system.common.req.SysMenuViewREQ;
import com.roncoo.education.system.common.resq.SysMenuListRESQ;
import com.roncoo.education.system.common.resq.SysMenuUserListRESQ;
import com.roncoo.education.system.common.resq.SysMenuViewRESQ;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 菜单信息
 *
 * @author wujing
 */
@RestController
@RequestMapping(value = "/system/pc/menu")
public class PcApiSysMenuController extends BaseController {

	@Autowired
	private PcApiSysMenuBiz biz;

	/**
	 * 菜单信息列出信息接口
	 */
	@ApiOperation(value = "菜单信息列出信息接口", notes = "菜单信息列出信息")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<SysMenuListRESQ> list(@RequestBody SysMenuListREQ sysMenuListREQ) {
		return biz.list(sysMenuListREQ);
	}

	/**
	 * 菜单信息保存信息接口
	 */
	@ApiOperation(value = "菜单信息保存接口", notes = "菜单信息保存接口")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<Integer> save(@RequestBody SysMenuSaveREQ sysMenuSaveREQ) {
		return biz.save(sysMenuSaveREQ);
	}

	/**
	 * 菜单信息删除接口
	 */
	@ApiOperation(value = "菜单信息删除接口", notes = "菜单信息删除接口")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> delete(@RequestBody SysMenuDeleteREQ sysMenuDeleteREQ) {
		return biz.delete(sysMenuDeleteREQ);
	}

	/**
	 * 菜单信息更新接口
	 */
	@ApiOperation(value = "菜单信息更新接口", notes = "菜单信息更新接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody SysMenuUpdateREQ sysMenuUpdateREQ) {
		return biz.update(sysMenuUpdateREQ);
	}

	/**
	 * 菜单信息查看接口
	 */
	@ApiOperation(value = "菜单信息查看接口", notes = "菜单信息查看接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<SysMenuViewRESQ> view(@RequestBody SysMenuViewREQ sysMenuViewREQ) {
		return biz.view(sysMenuViewREQ);
	}

	/**
	 * 用户菜单信息列出信息接口
	 */
	@ApiOperation(value = "用户菜单信息列出信息接口(登录用户)", notes = "用户菜单信息列出信息接口(登录用户)")
	@RequestMapping(value = "/user/list", method = RequestMethod.POST)
	public Result<SysMenuUserListRESQ> list(@RequestBody SysMenuUserListREQ sysMenuUserListREQ) {
		return biz.userList(sysMenuUserListREQ);
	}


	/**
	 * 用户菜单信息列出信息接口
	 */
	@ApiOperation(value = "用户按钮菜单信息列出信息接口(按钮)", notes = "用户按钮菜单信息列出信息接口(登录用户)")
	@RequestMapping(value = "/user/button/list", method = RequestMethod.POST)
	public Result<SysMenuUserListRESQ> buttonList(@RequestBody SysMenuUserListREQ sysMenuUserListREQ) {
		return biz.buttonList(sysMenuUserListREQ);
	}

}
