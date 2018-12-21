package com.roncoo.education.system.service.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.roncoo.education.system.service.dao.SysDao;
import com.roncoo.education.system.service.dao.impl.mapper.SysMapper;
import com.roncoo.education.system.service.dao.impl.mapper.entity.Sys;
import com.roncoo.education.system.service.dao.impl.mapper.entity.SysExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;

@Repository
public class SysDaoImpl implements SysDao {
	@Autowired
	private SysMapper sysMapper;

	@Override
	public int save(Sys record) {
		record.setId(IdWorker.getId());
		return this.sysMapper.insertSelective(record);
	}

	@Override
	public int deleteById(Long id) {
		return this.sysMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateById(Sys record) {
		return this.sysMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Sys getById(Long id) {
		return this.sysMapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<Sys> listForPage(int pageCurrent, int pageSize, SysExample example) {
		int count = this.sysMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<Sys>(count, totalPage, pageCurrent, pageSize, this.sysMapper.selectByExample(example));
	}

	@Override
	public Sys getSys() {
		SysExample example = new SysExample();
		List<Sys> list = this.sysMapper.selectByExample(example);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
}