package com.roncoo.education.course.service.dao.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.roncoo.education.course.service.dao.AdvDao;
import com.roncoo.education.course.service.dao.impl.mapper.AdvMapper;
import com.roncoo.education.course.service.dao.impl.mapper.entity.Adv;
import com.roncoo.education.course.service.dao.impl.mapper.entity.AdvExample;
import com.roncoo.education.course.service.dao.impl.mapper.entity.AdvExample.Criteria;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;

@Repository
public class AdvDaoImpl implements AdvDao {
	@Autowired
	private AdvMapper advMapper;

	@Override
	public int save(Adv record) {
		record.setId(IdWorker.getId());
		return this.advMapper.insertSelective(record);
	}

	@Override
	public int deleteById(Long id) {
		return this.advMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateById(Adv record) {
		return this.advMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Adv getById(Long id) {
		return this.advMapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<Adv> listForPage(int pageCurrent, int pageSize, AdvExample example) {
		int count = this.advMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<Adv>(count, totalPage, pageCurrent, pageSize, this.advMapper.selectByExample(example));
	}

	@Override
	public List<Adv> listByPlatShowAndStatusIdAndBeginTimeAndEndTime(Integer platShow, Integer statusId, Date beginTime, Date endTime) {
		AdvExample example = new AdvExample();
		Criteria c = example.createCriteria();
		c.andPlatShowEqualTo(platShow);
		c.andStatusIdEqualTo(statusId);
		c.andBeginTimeLessThan(beginTime);
		c.andEndTimeGreaterThan(endTime);
		return this.advMapper.selectByExample(example);
	}


}