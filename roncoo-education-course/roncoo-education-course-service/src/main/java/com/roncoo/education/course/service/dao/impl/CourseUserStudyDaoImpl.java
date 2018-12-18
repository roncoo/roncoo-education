package com.roncoo.education.course.service.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.roncoo.education.course.service.dao.CourseUserStudyDao;
import com.roncoo.education.course.service.dao.impl.mapper.CourseUserStudyMapper;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseUserStudy;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseUserStudyExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;

@Repository
public class CourseUserStudyDaoImpl implements CourseUserStudyDao {
	@Autowired
	private CourseUserStudyMapper courseUserStudyMapper;

	@Override
	public int save(CourseUserStudy record) {
		record.setId(IdWorker.getId());
		return this.courseUserStudyMapper.insertSelective(record);
	}

	@Override
	public int deleteById(Long id) {
		return this.courseUserStudyMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateById(CourseUserStudy record) {
		return this.courseUserStudyMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public CourseUserStudy getById(Long id) {
		return this.courseUserStudyMapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<CourseUserStudy> listForPage(int pageCurrent, int pageSize, CourseUserStudyExample example) {
		int count = this.courseUserStudyMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<CourseUserStudy>(count, totalPage, pageCurrent, pageSize, this.courseUserStudyMapper.selectByExample(example));
	}

	@Override
	public CourseUserStudy getByUserNoAndCourseId(Long userNo, Long courseId) {
		CourseUserStudyExample example = new CourseUserStudyExample();
		example.createCriteria().andUserNoEqualTo(userNo).andCourseIdEqualTo(courseId);
		List<CourseUserStudy> list = this.courseUserStudyMapper.selectByExample(example);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
}