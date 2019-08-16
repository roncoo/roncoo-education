/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.web.boss.service.dao;

import java.util.List;

import com.roncoo.education.util.base.Page;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysRole;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysRoleExample;

public interface SysRoleDao {
	int save(SysRole record);

	int deleteById(Long id);

	int updateById(SysRole record);

	SysRole getById(Long id);

	Page<SysRole> listForPage(int pageCurrent, int pageSize, SysRoleExample example);

	List<SysRole> listByExample(SysRoleExample example);
}
