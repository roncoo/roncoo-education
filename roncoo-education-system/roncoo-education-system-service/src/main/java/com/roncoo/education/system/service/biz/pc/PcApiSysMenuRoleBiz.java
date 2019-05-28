package com.roncoo.education.system.service.biz.pc;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.roncoo.education.system.service.common.req.SysMenuRoleListREQ;
import com.roncoo.education.system.service.common.req.SysMenuRoleSaveREQ;
import com.roncoo.education.system.service.common.resq.SysMenuRoleListRESQ;
import com.roncoo.education.system.service.common.resq.SysMenuRoleRESQ;
import com.roncoo.education.system.service.dao.SysMenuRoleDao;
import com.roncoo.education.system.service.dao.impl.mapper.entity.SysMenuRole;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;

/**
 * 菜单角色关联表
 *
 * @author wujing
 */
@Component
public class PcApiSysMenuRoleBiz {

	@Autowired
	private SysMenuRoleDao dao;

	public Result<SysMenuRoleListRESQ> list(SysMenuRoleListREQ req) {
		if (req.getRoleId() == null) {
			return Result.error("角色ID不能为空");
		}
		SysMenuRoleListRESQ resq = new SysMenuRoleListRESQ();
		List<SysMenuRole> list = dao.listByRoleId(req.getRoleId());
		if (CollectionUtils.isNotEmpty(list)) {
			resq.setSysMenuRole(PageUtil.copyList(list, SysMenuRoleRESQ.class));
		}
		return Result.success(resq);
	}

	@Transactional
	public Result<Integer> save(SysMenuRoleSaveREQ req) {
		if (req.getRoleId() == null) {
			return Result.error("角色ID不能为空");
		}
		if (CollectionUtils.isNotEmpty(req.getMenuId())) {
			// 先删除角色下所有的关联菜单
			dao.deleteByRoleId(req.getRoleId());
			for (Long menuId : req.getMenuId()) {
				SysMenuRole entity = new SysMenuRole();
				entity.setMenuId(menuId);
				entity.setRoleId(req.getRoleId());
				dao.save(entity);
			}
		}
		return Result.success(1);
	}

}
