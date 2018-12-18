package com.roncoo.education.user.service.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.roncoo.education.user.service.dao.LecturerAuditDao;
import com.roncoo.education.user.service.dao.impl.mapper.LecturerAuditMapper;
import com.roncoo.education.user.service.dao.impl.mapper.entity.LecturerAudit;
import com.roncoo.education.user.service.dao.impl.mapper.entity.LecturerAuditExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;

@Repository
public class LecturerAuditDaoImpl implements LecturerAuditDao {
	@Autowired
	private LecturerAuditMapper lecturerAuditMapper;

	@Override
	public int save(LecturerAudit record) {
		record.setId(IdWorker.getId());
		return this.lecturerAuditMapper.insertSelective(record);
	}

	@Override
	public int deleteById(Long id) {
		return this.lecturerAuditMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateById(LecturerAudit record) {
		record.setGmtCreate(null);
		record.setGmtModified(null);
		return this.lecturerAuditMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public LecturerAudit getById(Long id) {
		return this.lecturerAuditMapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<LecturerAudit> listForPage(int pageCurrent, int pageSize, LecturerAuditExample example) {
		int count = this.lecturerAuditMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<LecturerAudit>(count, totalPage, pageCurrent, pageSize, this.lecturerAuditMapper.selectByExample(example));
	}

	@Override
	public LecturerAudit getByLecturerUserNo(Long lecturerUserNo) {
		LecturerAuditExample example = new LecturerAuditExample();
		example.createCriteria().andLecturerUserNoEqualTo(lecturerUserNo);
		List<LecturerAudit> list = this.lecturerAuditMapper.selectByExample(example);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
}