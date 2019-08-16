package com.roncoo.education.user.service.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.roncoo.education.user.service.dao.PlatformDao;
import com.roncoo.education.user.service.dao.impl.mapper.PlatformMapper;
import com.roncoo.education.user.service.dao.impl.mapper.entity.Platform;
import com.roncoo.education.user.service.dao.impl.mapper.entity.PlatformExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;

@Repository
public class PlatformDaoImpl implements PlatformDao {
	@Autowired
	private PlatformMapper platformMapper;

	@Override
	public int save(Platform record) {
		record.setId(IdWorker.getId());
		return this.platformMapper.insertSelective(record);
	}

	@Override
	public int deleteById(Long id) {
		return this.platformMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateById(Platform record) {
		return this.platformMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Platform getById(Long id) {
		return this.platformMapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<Platform> listForPage(int pageCurrent, int pageSize, PlatformExample example) {
		int count = this.platformMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<Platform>(count, totalPage, pageCurrent, pageSize,
				this.platformMapper.selectByExample(example));
	}

	@Override
	public Platform getByClientId(String clientId) {
		PlatformExample example = new PlatformExample();
		example.createCriteria().andClientIdEqualTo(clientId);
		List<Platform> list = this.platformMapper.selectByExample(example);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public Platform getByClientName(String clientName) {
		PlatformExample example = new PlatformExample();
		example.createCriteria().andClientNameEqualTo(clientName);
		List<Platform> list = this.platformMapper.selectByExample(example);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
}