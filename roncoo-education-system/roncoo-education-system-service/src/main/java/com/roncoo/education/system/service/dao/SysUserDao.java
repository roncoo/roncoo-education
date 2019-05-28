package com.roncoo.education.system.service.dao;

import com.roncoo.education.system.service.dao.impl.mapper.entity.SysUser;
import com.roncoo.education.system.service.dao.impl.mapper.entity.SysUserExample;
import com.roncoo.education.util.base.Page;

public interface SysUserDao {
	int save(SysUser record);

	int deleteById(Long id);

	int updateById(SysUser record);

	int updateByExampleSelective(SysUser record, SysUserExample example);

	SysUser getById(Long id);

	Page<SysUser> listForPage(int pageCurrent, int pageSize, SysUserExample example);

	/**
	 * 根据用户编号获取管理员信息
	 * 
	 * @param userNo
	 * @return
	 */
	SysUser getByUserNo(Long userNo);
}