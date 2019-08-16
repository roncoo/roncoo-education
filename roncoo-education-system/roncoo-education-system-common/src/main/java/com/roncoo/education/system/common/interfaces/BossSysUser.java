package com.roncoo.education.system.common.interfaces;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.roncoo.education.system.common.bean.qo.SysUserQO;
import com.roncoo.education.system.common.bean.vo.SysUserVO;
import com.roncoo.education.util.base.Page;

/**
 * 后台用户信息 
 *
 * @author wujing
 */
public interface BossSysUser {
	
	@RequestMapping(value = "/boss/system/sysUser/listForPage")
	Page<SysUserVO> listForPage(@RequestBody SysUserQO qo);
	
	@RequestMapping(value = "/boss/system/sysUser/save")
	int save(@RequestBody SysUserQO qo);
	
	@RequestMapping(value = "/boss/system/sysUser/deleteById")
	int deleteById(@RequestBody Long id);
	
	@RequestMapping(value = "/boss/system/sysUser/updateById")
	int updateById(@RequestBody SysUserQO qo);
	
	@RequestMapping(value = "/boss/system/sysUser/getById")
	SysUserVO getById(@RequestBody Long id);
	
}
