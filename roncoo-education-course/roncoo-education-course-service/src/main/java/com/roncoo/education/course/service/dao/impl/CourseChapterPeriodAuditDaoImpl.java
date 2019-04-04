package com.roncoo.education.course.service.dao.impl;

import com.roncoo.education.course.service.dao.CourseChapterPeriodAuditDao;
import com.roncoo.education.course.service.dao.impl.mapper.CourseChapterPeriodAuditMapper;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseChapterPeriodAudit;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseChapterPeriodAuditExample;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseChapterPeriodAuditExample.Criteria;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CourseChapterPeriodAuditDaoImpl implements CourseChapterPeriodAuditDao {
	@Autowired
	private CourseChapterPeriodAuditMapper courseChapterPeriodAuditMapper;

	public int save(CourseChapterPeriodAudit record) {
		record.setId(IdWorker.getId());
		return this.courseChapterPeriodAuditMapper.insertSelective(record);
	}

	public int deleteById(Long id) {
		return this.courseChapterPeriodAuditMapper.deleteByPrimaryKey(id);
	}

	public int updateById(CourseChapterPeriodAudit record) {
		record.setGmtCreate(null);
		record.setGmtModified(null);
		return this.courseChapterPeriodAuditMapper.updateByPrimaryKeySelective(record);
	}

	public CourseChapterPeriodAudit getById(Long id) {
		return this.courseChapterPeriodAuditMapper.selectByPrimaryKey(id);
	}

	public Page<CourseChapterPeriodAudit> listForPage(int pageCurrent, int pageSize,
			CourseChapterPeriodAuditExample example) {
		int count = this.courseChapterPeriodAuditMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<CourseChapterPeriodAudit>(count, totalPage, pageCurrent, pageSize,
				this.courseChapterPeriodAuditMapper.selectByExample(example));
	}

	@Override
	public List<CourseChapterPeriodAudit> listByCourseId(Long CourseId) {
		CourseChapterPeriodAuditExample example = new CourseChapterPeriodAuditExample();
		Criteria c = example.createCriteria();
		c.andCourseIdEqualTo(CourseId);
		example.setOrderByClause("sort asc, id asc");
		return this.courseChapterPeriodAuditMapper.selectByExample(example);
	}

	@Override
	public int updateSortByPeriodId(int sort, Long periodId) {
		CourseChapterPeriodAuditExample example = new CourseChapterPeriodAuditExample();
		Criteria c = example.createCriteria();
		c.andIdEqualTo(periodId);
		CourseChapterPeriodAudit record = new CourseChapterPeriodAudit();
		record.setId(periodId);
		record.setSort(sort);
		record.setGmtCreate(null);
		record.setGmtModified(null);
		return this.courseChapterPeriodAuditMapper.updateByExampleSelective(record, example);
	}

	@Override
	public List<CourseChapterPeriodAudit> listByIsVideoAndPeriodId(String isVideo, Long periodId) {
		CourseChapterPeriodAuditExample example = new CourseChapterPeriodAuditExample();
		Criteria c = example.createCriteria();
		c.andIsVideoEqualTo(isVideo);
		c.andIdEqualTo(periodId);
		example.setOrderByClause("sort asc, id asc");
		return this.courseChapterPeriodAuditMapper.selectByExample(example);
	}

	@Override
	public List<CourseChapterPeriodAudit> listByIsDocAndPeriodId(Integer isDoc, Long periodId) {
		CourseChapterPeriodAuditExample example = new CourseChapterPeriodAuditExample();
		Criteria c = example.createCriteria();
		c.andIsDocEqualTo(isDoc);
		c.andIdEqualTo(periodId);
		example.setOrderByClause("sort asc, id asc");
		return this.courseChapterPeriodAuditMapper.selectByExample(example);
	}

	@Override
	public CourseChapterPeriodAudit getByVideoNo(Long videoNo) {
		CourseChapterPeriodAuditExample example = new CourseChapterPeriodAuditExample();
		Criteria c = example.createCriteria();
		c.andVideoNoEqualTo(videoNo);
		example.setOrderByClause("sort asc, id asc");
		List<CourseChapterPeriodAudit> list = this.courseChapterPeriodAuditMapper.selectByExample(example);
		if (list.isEmpty() || list.size() < 1) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public int deleteByCourseId(Long courseId) {
		CourseChapterPeriodAuditExample example = new CourseChapterPeriodAuditExample();
		Criteria c = example.createCriteria();
		c.andCourseIdEqualTo(courseId);
		return this.courseChapterPeriodAuditMapper.deleteByExample(example);
	}

	@Override
	public List<CourseChapterPeriodAudit> listByChapterIdAndStatusId(Long chapterId, Integer statusId) {
		CourseChapterPeriodAuditExample example = new CourseChapterPeriodAuditExample();
		Criteria c = example.createCriteria();
		c.andChapterIdEqualTo(chapterId);
		c.andStatusIdEqualTo(statusId);
		example.setOrderByClause("sort asc, id asc");
		return this.courseChapterPeriodAuditMapper.selectByExample(example);
	}

	@Override
	public List<CourseChapterPeriodAudit> listByVideoNo(Long videoNo) {
		CourseChapterPeriodAuditExample example = new CourseChapterPeriodAuditExample();
		Criteria c = example.createCriteria();
		c.andVideoNoEqualTo(videoNo);
		return this.courseChapterPeriodAuditMapper.selectByExample(example);
	}
}