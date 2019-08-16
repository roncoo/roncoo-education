/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.web.boss.service.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.web.boss.service.dao.SysUserDao;
import com.roncoo.education.web.boss.service.dao.impl.mapper.SysUserMapper;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysUser;
import com.roncoo.education.web.boss.service.dao.impl.mapper.entity.SysUserExample;

@Repository
public class SysUserDaoImpl implements SysUserDao {
	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public int save(SysUser record) {
		return this.sysUserMapper.insertSelective(record);
	}

	@Override
	public int deleteById(Long id) {
		return this.sysUserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateById(SysUser record) {
		return this.sysUserMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public SysUser getById(Long id) {
		return this.sysUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<SysUser> listForPage(int pageCurrent, int pageSize, SysUserExample example) {
		int count = this.sysUserMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<SysUser>(count, totalPage, pageCurrent, pageSize, this.sysUserMapper.selectByExample(example));
	}

	@Override
	public SysUser getByUserNo(Long userNo) {
		SysUserExample example = new SysUserExample();
		example.createCriteria().andUserNoEqualTo(userNo);
		List<SysUser> list = this.sysUserMapper.selectByExample(example);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public SysUser getByMobile(String mobile) {
		SysUserExample example = new SysUserExample();
		example.createCriteria().andMobileEqualTo(mobile);
		List<SysUser> list = this.sysUserMapper.selectByExample(example);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

}
