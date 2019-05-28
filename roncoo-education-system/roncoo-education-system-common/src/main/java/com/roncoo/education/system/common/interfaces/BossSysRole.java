package com.roncoo.education.system.common.interfaces;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.roncoo.education.system.common.bean.qo.SysRoleQO;
import com.roncoo.education.system.common.bean.vo.SysRoleVO;
import com.roncoo.education.util.base.Page;

/**
 * 角色信息 
 *
 * @author wujing
 */
public interface BossSysRole {
	
	@RequestMapping(value = "/boss/system/sysRole/listForPage")
	Page<SysRoleVO> listForPage(@RequestBody SysRoleQO qo);
	
	@RequestMapping(value = "/boss/system/sysRole/save")
	int save(@RequestBody SysRoleQO qo);
	
	@RequestMapping(value = "/boss/system/sysRole/deleteById")
	int deleteById(@RequestBody Long id);
	
	@RequestMapping(value = "/boss/system/sysRole/updateById")
	int updateById(@RequestBody SysRoleQO qo);
	
	@RequestMapping(value = "/boss/system/sysRole/getById")
	SysRoleVO getById(@RequestBody Long id);
	
}
