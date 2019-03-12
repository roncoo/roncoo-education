package com.roncoo.education.system.service.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.roncoo.education.system.service.dao.MsgUserDao;
import com.roncoo.education.system.service.dao.impl.mapper.MsgUserMapper;
import com.roncoo.education.system.service.dao.impl.mapper.entity.MsgUser;
import com.roncoo.education.system.service.dao.impl.mapper.entity.MsgUserExample;
import com.roncoo.education.system.service.dao.impl.mapper.entity.MsgUserExample.Criteria;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;

@Repository
public class MsgUserDaoImpl implements MsgUserDao {
	@Autowired
	private MsgUserMapper msgUserMapper;

	@Override
	public int save(MsgUser record) {
		record.setId(IdWorker.getId());
		return this.msgUserMapper.insertSelective(record);
	}

	@Override
	public int deleteById(Long id) {
		return this.msgUserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateById(MsgUser record) {
		return this.msgUserMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public MsgUser getById(Long id) {
		return this.msgUserMapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<MsgUser> listForPage(int pageCurrent, int pageSize, MsgUserExample example) {
		int count = this.msgUserMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<MsgUser>(count, totalPage, pageCurrent, pageSize, this.msgUserMapper.selectByExample(example));
	}

	@Override
	public int deleteByMsgId(Long id) {
		MsgUserExample example = new MsgUserExample();
		Criteria c = example.createCriteria();
		c.andMsgIdEqualTo(id);
		return this.msgUserMapper.deleteByExample(example);
	}

	@Override
	public int countByUserNoAndIsRead(Long userNo, Integer isRead) {
		MsgUserExample example = new MsgUserExample();
		Criteria c = example.createCriteria();
		c.andUserNoEqualTo(userNo);
		c.andIsReadEqualTo(isRead);
		return this.msgUserMapper.countByExample(example);
	}
}