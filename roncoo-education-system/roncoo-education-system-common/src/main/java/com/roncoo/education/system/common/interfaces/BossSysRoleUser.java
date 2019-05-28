package com.roncoo.education.system.common.interfaces;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.roncoo.education.system.common.bean.qo.SysRoleUserQO;
import com.roncoo.education.system.common.bean.vo.SysRoleUserVO;
import com.roncoo.education.util.base.Page;

/**
 * 角色用户关联表 
 *
 * @author wujing
 */
public interface BossSysRoleUser {
	
	@RequestMapping(value = "/boss/system/sysRoleUser/listForPage")
	Page<SysRoleUserVO> listForPage(@RequestBody SysRoleUserQO qo);
	
	@RequestMapping(value = "/boss/system/sysRoleUser/save")
	int save(@RequestBody SysRoleUserQO qo);
	
	@RequestMapping(value = "/boss/system/sysRoleUser/deleteById")
	int deleteById(@RequestBody Long id);
	
	@RequestMapping(value = "/boss/system/sysRoleUser/updateById")
	int updateById(@RequestBody SysRoleUserQO qo);
	
	@RequestMapping(value = "/boss/system/sysRoleUser/getById")
	SysRoleUserVO getById(@RequestBody Long id);
	
}
