/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.web.boss.service.dao;

import java.util.List;

import com.roncoo.education.util.base.Page;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysRoleUser;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysRoleUserExample;

public interface SysRoleUserDao {
	int save(SysRoleUser record);

	int deleteById(Long id);

	int updateById(SysRoleUser record);

	SysRoleUser getById(Long id);

	Page<SysRoleUser> listForPage(int pageCurrent, int pageSize, SysRoleUserExample example);

	List<SysRoleUser> listByUserId(Long userId);

	int deleteByUserId(Long userId);

}
