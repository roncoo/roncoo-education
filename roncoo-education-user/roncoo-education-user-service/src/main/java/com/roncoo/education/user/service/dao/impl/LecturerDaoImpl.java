package com.roncoo.education.user.service.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.roncoo.education.user.service.dao.LecturerDao;
import com.roncoo.education.user.service.dao.impl.mapper.LecturerMapper;
import com.roncoo.education.user.service.dao.impl.mapper.entity.Lecturer;
import com.roncoo.education.user.service.dao.impl.mapper.entity.LecturerExample;
import com.roncoo.education.user.service.dao.impl.mapper.entity.LecturerExample.Criteria;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;

@Repository
public class LecturerDaoImpl implements LecturerDao {
	@Autowired
	private LecturerMapper lecturerMapper;

	@Override
	public int save(Lecturer record) {
		return this.lecturerMapper.insertSelective(record);
	}

	@Override
	public int deleteById(Long id) {
		return this.lecturerMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateById(Lecturer record) {
		record.setGmtCreate(null);
		record.setGmtModified(null);
		return this.lecturerMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public Lecturer getById(Long id) {
		return this.lecturerMapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<Lecturer> listForPage(int pageCurrent, int pageSize, LecturerExample example) {
		int count = this.lecturerMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<Lecturer>(count, totalPage, pageCurrent, pageSize, this.lecturerMapper.selectByExample(example));
	}

	@Override
	public Lecturer getByLecturerUserNo(Long lecturerUserNo) {
		LecturerExample example = new LecturerExample();
		Criteria criteria = example.createCriteria();
		criteria.andLecturerUserNoEqualTo(lecturerUserNo);
		List<Lecturer> resultList = this.lecturerMapper.selectByExample(example);
		if (resultList.isEmpty()) {
			return null;
		}
		return resultList.get(0);
	}

	@Override
	public List<Lecturer> listByStatusId(Integer statusId) {
		LecturerExample example = new LecturerExample();
		Criteria criteria = example.createCriteria();
		criteria.andStatusIdEqualTo(statusId);
		return this.lecturerMapper.selectByExample(example);
	}

	@Override
	public List<Lecturer> getByLecturerName(String LecturerName) {
		LecturerExample example = new LecturerExample();
		Criteria criteria = example.createCriteria();
		criteria.andLecturerNameLike(PageUtil.rightLike(LecturerName));
		List<Lecturer> resultList = this.lecturerMapper.selectByExample(example);
		if (resultList.isEmpty()) {
			return null;
		}
		return resultList;
	}

	@Override
	public Lecturer getByLecturerUserNoAndStatusId(Long lecturerUserNo, Integer statusId) {
		LecturerExample example = new LecturerExample();
		Criteria criteria = example.createCriteria();
		criteria.andLecturerUserNoEqualTo(lecturerUserNo);
		criteria.andStatusIdEqualTo(statusId);
		List<Lecturer> resultList = this.lecturerMapper.selectByExample(example);
		if (resultList.isEmpty()) {
			return null;
		}
		return resultList.get(0);
	}
}