package com.roncoo.education.system.service.dao.impl;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.roncoo.education.system.service.dao.SysUserDao;
import com.roncoo.education.system.service.dao.impl.mapper.SysUserMapper;
import com.roncoo.education.system.service.dao.impl.mapper.entity.SysUser;
import com.roncoo.education.system.service.dao.impl.mapper.entity.SysUserExample;
import com.roncoo.education.system.service.dao.impl.mapper.entity.SysUserExample.Criteria;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;

@Repository
public class SysUserDaoImpl implements SysUserDao {
	@Autowired
	private SysUserMapper sysUserMapper;

	public int save(SysUser record) {
		record.setId(IdWorker.getId());
		return this.sysUserMapper.insertSelective(record);
	}

	public int deleteById(Long id) {
		return this.sysUserMapper.deleteByPrimaryKey(id);
	}

	public int updateById(SysUser record) {
		return this.sysUserMapper.updateByPrimaryKeySelective(record);
	}

	public int updateByExampleSelective(SysUser record, SysUserExample example) {
		return this.sysUserMapper.updateByExampleSelective(record, example);
	}

	public SysUser getById(Long id) {
		return this.sysUserMapper.selectByPrimaryKey(id);
	}

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
		Criteria c = example.createCriteria();
		c.andUserNoEqualTo(userNo);
		List<SysUser> list = sysUserMapper.selectByExample(example);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		return list.get(0);
	}
}