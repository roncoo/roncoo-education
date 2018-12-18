package com.roncoo.education.course.service.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.roncoo.education.course.service.dao.CourseUserStudyLogDao;
import com.roncoo.education.course.service.dao.impl.mapper.CourseUserStudyLogMapper;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseUserStudyLog;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseUserStudyLogExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;

@Repository
public class CourseUserStudyLogDaoImpl implements CourseUserStudyLogDao {
	@Autowired
	private CourseUserStudyLogMapper courseUserStudyLogMapper;

	@Override
	public int save(CourseUserStudyLog record) {
		record.setId(IdWorker.getId());
		return this.courseUserStudyLogMapper.insertSelective(record);
	}

	@Override
	public int deleteById(Long id) {
		return this.courseUserStudyLogMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateById(CourseUserStudyLog record) {
		return this.courseUserStudyLogMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public CourseUserStudyLog getById(Long id) {
		return this.courseUserStudyLogMapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<CourseUserStudyLog> listForPage(int pageCurrent, int pageSize, CourseUserStudyLogExample example) {
		int count = this.courseUserStudyLogMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<CourseUserStudyLog>(count, totalPage, pageCurrent, pageSize, this.courseUserStudyLogMapper.selectByExample(example));
	}

	@Override
	public CourseUserStudyLog getByUserNoAndPeriodId(Long userNo, Long periodId) {
		CourseUserStudyLogExample example = new CourseUserStudyLogExample();
		example.createCriteria().andUserNoEqualTo(userNo).andPeriodIdEqualTo(periodId);
		List<CourseUserStudyLog> list = this.courseUserStudyLogMapper.selectByExample(example);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
}