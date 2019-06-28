package com.roncoo.education.system.service.biz.pc;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.roncoo.education.system.service.common.req.SysRoleUserListREQ;
import com.roncoo.education.system.service.common.req.SysRoleUserSaveREQ;
import com.roncoo.education.system.service.common.resq.SysRoleUserListRESQ;
import com.roncoo.education.system.service.dao.SysRoleUserDao;
import com.roncoo.education.system.service.dao.impl.mapper.entity.SysRoleUser;
import com.roncoo.education.util.base.Result;

/**
 * 角色用户关联表
 *
 * @author wujing
 */
@Component
public class PcApiSysRoleUserBiz {

	@Autowired
	private SysRoleUserDao dao;

	public Result<SysRoleUserListRESQ> list(SysRoleUserListREQ req) {
		if (req.getUserId() == null) {
			return Result.error("用户ID不能为空");
		}
		SysRoleUserListRESQ resq = new SysRoleUserListRESQ();
		List<SysRoleUser> list = dao.listByUserId(req.getUserId());
		if (CollectionUtils.isNotEmpty(list)) {
			List<Long> roleIdList = new ArrayList<>();
			for (SysRoleUser sysRoleUser : list) {
				roleIdList.add(sysRoleUser.getRoleId());
			}
			resq.setRoleId(roleIdList);
		}
		return Result.success(resq);
	}

	@Transactional
	public Result<Integer> save(SysRoleUserSaveREQ req) {
		if (req.getUserId() == null) {
			return Result.error("用户ID不能为空");
		}
		if (CollectionUtils.isNotEmpty(req.getRoleId())) {
			// 先删除旧的，再添加新的
			dao.deleteByUserId(req.getUserId());
			// 拆分角色和平台拼接ID
			for (Long roleId : req.getRoleId()) {
				SysRoleUser sysRoleUser = new SysRoleUser();
				sysRoleUser.setRoleId(roleId);
				sysRoleUser.setUserId(req.getUserId());
				dao.save(sysRoleUser);
			}
		}
		return Result.success(1);
	}
}
