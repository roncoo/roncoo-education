package com.roncoo.education.course.service.dao.impl;

import com.roncoo.education.course.service.dao.CourseChapterAuditDao;
import com.roncoo.education.course.service.dao.impl.mapper.CourseChapterAuditMapper;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseChapterAudit;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseChapterAuditExample;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseChapterAuditExample.Criteria;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class CourseChapterAuditDaoImpl implements CourseChapterAuditDao {
	@Autowired
	private CourseChapterAuditMapper courseChapterAuditMapper;

	public int save(CourseChapterAudit record) {
		record.setId(IdWorker.getId());
		return this.courseChapterAuditMapper.insertSelective(record);
	}

	public int deleteById(Long id) {
		return this.courseChapterAuditMapper.deleteByPrimaryKey(id);
	}

	public int updateById(CourseChapterAudit record) {
		record.setGmtCreate(null);
		record.setGmtModified(null);
		return this.courseChapterAuditMapper.updateByPrimaryKeySelective(record);
	}

	public CourseChapterAudit getById(Long id) {
		return this.courseChapterAuditMapper.selectByPrimaryKey(id);
	}

	public Page<CourseChapterAudit> listForPage(int pageCurrent, int pageSize, CourseChapterAuditExample example) {
		int count = this.courseChapterAuditMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<CourseChapterAudit>(count, totalPage, pageCurrent, pageSize, this.courseChapterAuditMapper.selectByExample(example));
	}

	@Override
	public List<CourseChapterAudit> listByCourseId(Long courseId) {
		CourseChapterAuditExample example = new CourseChapterAuditExample();
		Criteria c = example.createCriteria();
		c.andCourseIdEqualTo(courseId);
		example.setOrderByClause("sort asc, id asc");
		return this.courseChapterAuditMapper.selectByExample(example);
	}

	@Override
	public int updateSortByChapterId(Integer sort, Long chapterId) {
		CourseChapterAuditExample example = new CourseChapterAuditExample();
		Criteria c = example.createCriteria();
		c.andIdEqualTo(chapterId);
		CourseChapterAudit record = new CourseChapterAudit();
		record.setId(chapterId);
		record.setSort(sort);
		record.setGmtCreate(null);
		record.setGmtModified(null);
		return this.courseChapterAuditMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int updateAuditStatusByChapterNo(Integer auditStatus, Long chapterId) {
		CourseChapterAuditExample example = new CourseChapterAuditExample();
		Criteria c = example.createCriteria();
		c.andIdEqualTo(chapterId);
		CourseChapterAudit record = new CourseChapterAudit();
		record.setId(chapterId);
		record.setAuditStatus(auditStatus);
		record.setGmtCreate(null);
		record.setGmtModified(null);
		return this.courseChapterAuditMapper.updateByExampleSelective(record, example);
	}

	@Override
	public int deleteByCourseId(Long courseId) {
		CourseChapterAuditExample example = new CourseChapterAuditExample();
		Criteria c = example.createCriteria();
		c.andCourseIdEqualTo(courseId);
		return this.courseChapterAuditMapper.deleteByExample(example);
	}

	@Override
	public List<CourseChapterAudit> listByCourseIdAndStatusId(Long courseId, Integer statusId) {
		CourseChapterAuditExample example = new CourseChapterAuditExample();
		Criteria c = example.createCriteria();
		c.andCourseIdEqualTo(courseId);
		c.andStatusIdEqualTo(statusId);
		example.setOrderByClause("sort asc, id asc");
		return this.courseChapterAuditMapper.selectByExample(example);
	}

}