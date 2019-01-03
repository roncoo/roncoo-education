package com.roncoo.education.user.service.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.roncoo.education.user.service.dao.LecturerExtDao;
import com.roncoo.education.user.service.dao.impl.mapper.LecturerExtMapper;
import com.roncoo.education.user.service.dao.impl.mapper.entity.LecturerExt;
import com.roncoo.education.user.service.dao.impl.mapper.entity.LecturerExtExample;
import com.roncoo.education.user.service.dao.impl.mapper.entity.LecturerExtExample.Criteria;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;

@Repository
public class LecturerExtDaoImpl implements LecturerExtDao {
	@Autowired
	private LecturerExtMapper lecturerExtMapper;

	@Override
	public int save(LecturerExt record) {
		record.setId(IdWorker.getId());
		return this.lecturerExtMapper.insertSelective(record);
	}

	@Override
	public int deleteById(Long id) {
		return this.lecturerExtMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateById(LecturerExt record) {
		return this.lecturerExtMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public LecturerExt getById(Long id) {
		return this.lecturerExtMapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<LecturerExt> listForPage(int pageCurrent, int pageSize, LecturerExtExample example) {
		int count = this.lecturerExtMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<LecturerExt>(count, totalPage, pageCurrent, pageSize, this.lecturerExtMapper.selectByExample(example));
	}

	/**
	 * 根据传入讲师用户编号获取讲师信息
	 * 
	 */
	@Override
	public LecturerExt getByLecturerUserNo(Long lecturerUserNo) {
		LecturerExtExample example = new LecturerExtExample();
		Criteria criteria = example.createCriteria();
		criteria.andLecturerUserNoEqualTo(lecturerUserNo);
		List<LecturerExt> resultList = this.lecturerExtMapper.selectByExample(example);
		if (resultList.isEmpty()) {
			return null;
		}
		return resultList.get(0);
	}

	@Override
	public int updateByLecturerUserNo(LecturerExt record) {
		record.setGmtCreate(null);
		record.setGmtModified(null);
		LecturerExtExample example = new LecturerExtExample();
		Criteria c = example.createCriteria();
		c.andLecturerUserNoEqualTo(record.getLecturerUserNo());
		return this.lecturerExtMapper.updateByExampleSelective(record, example);
	}
}