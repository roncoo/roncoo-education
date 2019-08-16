/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.web.boss.service.dao;

import com.roncoo.education.util.base.Page;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysUser;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysUserExample;

public interface SysUserDao {
	int save(SysUser record);

	int deleteById(Long id);

	int updateById(SysUser record);

	SysUser getById(Long id);

	Page<SysUser> listForPage(int pageCurrent, int pageSize, SysUserExample example);

	SysUser getByUserNo(Long userNo);

	SysUser getByMobile(String mobile);

}
