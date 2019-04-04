package com.roncoo.education.course.service.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.roncoo.education.course.service.dao.CourseChapterPeriodDao;
import com.roncoo.education.course.service.dao.impl.mapper.CourseChapterPeriodMapper;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseChapterPeriod;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseChapterPeriodExample;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseChapterPeriodExample.Criteria;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;

@Repository
public class CourseChapterPeriodDaoImpl implements CourseChapterPeriodDao {
	@Autowired
	private CourseChapterPeriodMapper courseChapterPeriodMapper;

	@Override
	public int save(CourseChapterPeriod record) {
		return this.courseChapterPeriodMapper.insertSelective(record);
	}

	@Override
	public int deleteById(Long id) {
		return this.courseChapterPeriodMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateById(CourseChapterPeriod record) {
		record.setGmtCreate(null);
		record.setGmtModified(null);
		return this.courseChapterPeriodMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public CourseChapterPeriod getById(Long id) {
		return this.courseChapterPeriodMapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<CourseChapterPeriod> listForPage(int pageCurrent, int pageSize, CourseChapterPeriodExample example) {
		int count = this.courseChapterPeriodMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<CourseChapterPeriod>(count, totalPage, pageCurrent, pageSize,
				this.courseChapterPeriodMapper.selectByExample(example));
	}

	@Override
	public List<CourseChapterPeriod> listByChapterId(Long chapterId) {
		CourseChapterPeriodExample example = new CourseChapterPeriodExample();
		example.createCriteria().andChapterIdEqualTo(chapterId);
		return this.courseChapterPeriodMapper.selectByExample(example);
	}

	@Override
	public List<CourseChapterPeriod> listByChapterIdAndStatusId(Long chapterId, Integer statusId) {
		CourseChapterPeriodExample example = new CourseChapterPeriodExample();
		Criteria c = example.createCriteria();
		c.andChapterIdEqualTo(chapterId);
		c.andStatusIdEqualTo(statusId);
		example.setOrderByClause(" sort asc ,id asc");
		return this.courseChapterPeriodMapper.selectByExample(example);
	}

	@Override
	public CourseChapterPeriod getByVideoNo(Long videoNo) {
		CourseChapterPeriodExample example = new CourseChapterPeriodExample();
		Criteria c = example.createCriteria();
		c.andVideoNoEqualTo(videoNo);
		example.setOrderByClause(" sort asc ,id asc");
		List<CourseChapterPeriod> list = this.courseChapterPeriodMapper.selectByExample(example);
		if (list.isEmpty() || list.size() < 1) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public List<CourseChapterPeriod> listByVideoNo(Long videoNo) {
		CourseChapterPeriodExample example = new CourseChapterPeriodExample();
		Criteria c = example.createCriteria();
		c.andVideoNoEqualTo(videoNo);
		return this.courseChapterPeriodMapper.selectByExample(example);
	}
}