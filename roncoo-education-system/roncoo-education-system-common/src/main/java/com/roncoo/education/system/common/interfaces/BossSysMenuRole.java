package com.roncoo.education.system.common.interfaces;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.roncoo.education.system.common.bean.qo.SysMenuRoleQO;
import com.roncoo.education.system.common.bean.vo.SysMenuRoleVO;
import com.roncoo.education.util.base.Page;

/**
 * 菜单角色关联表 
 *
 * @author wujing
 */
public interface BossSysMenuRole {
	
	@RequestMapping(value = "/boss/system/sysMenuRole/listForPage")
	Page<SysMenuRoleVO> listForPage(@RequestBody SysMenuRoleQO qo);
	
	@RequestMapping(value = "/boss/system/sysMenuRole/save")
	int save(@RequestBody SysMenuRoleQO qo);
	
	@RequestMapping(value = "/boss/system/sysMenuRole/deleteById")
	int deleteById(@RequestBody Long id);
	
	@RequestMapping(value = "/boss/system/sysMenuRole/updateById")
	int updateById(@RequestBody SysMenuRoleQO qo);
	
	@RequestMapping(value = "/boss/system/sysMenuRole/getById")
	SysMenuRoleVO getById(@RequestBody Long id);
	
}
