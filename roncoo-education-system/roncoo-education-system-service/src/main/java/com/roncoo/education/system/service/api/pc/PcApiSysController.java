package com.roncoo.education.system.service.api.pc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.system.service.api.pc.biz.PcApiSysBiz;
import com.roncoo.education.system.common.req.SysUpdateRESQ;
import com.roncoo.education.system.common.resq.SysViewRESQ;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 系统配置表
 */
@RestController
@RequestMapping(value = "/system/pc/sys")
public class PcApiSysController extends BaseController  {

	@Autowired
	private PcApiSysBiz biz;

	/**
	 * 获取系统配置信息接口
	 */
	@ApiOperation(value = "获取系统配置信息接口", notes = "获取系统配置信息")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<SysViewRESQ> view() {
		return biz.view();
	}

	/**
	 * 更新系统配置信息接口
	 */
	@ApiOperation(value = "更新系统配置信息接口", notes = "更新系统配置信息")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody SysUpdateRESQ sysUpdateRESQ) {
		return biz.update(sysUpdateRESQ);
	}

}
