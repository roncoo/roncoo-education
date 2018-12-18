package com.roncoo.education.course.service.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.roncoo.education.course.service.dao.CourseChapterDao;
import com.roncoo.education.course.service.dao.impl.mapper.CourseChapterMapper;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseChapter;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseChapterExample;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseChapterExample.Criteria;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;

@Repository
public class CourseChapterDaoImpl implements CourseChapterDao {
	@Autowired
	private CourseChapterMapper courseChapterMapper;

	@Override
	public int save(CourseChapter record) {
		return this.courseChapterMapper.insertSelective(record);
	}

	@Override
	public int deleteById(Long id) {
		return this.courseChapterMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int updateById(CourseChapter record) {
		record.setGmtCreate(null);
		record.setGmtModified(null);
		return this.courseChapterMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public CourseChapter getById(Long id) {
		return this.courseChapterMapper.selectByPrimaryKey(id);
	}

	@Override
	public Page<CourseChapter> listForPage(int pageCurrent, int pageSize, CourseChapterExample example) {
		int count = this.courseChapterMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<CourseChapter>(count, totalPage, pageCurrent, pageSize, this.courseChapterMapper.selectByExample(example));
	}

	/**
	 * 根据章节编号、状态ID查找章节信息
	 */
	@Override
	public List<CourseChapter> listByCourseIdAndStatusId(Long courseId, Integer statusId) {
		CourseChapterExample example = new CourseChapterExample();
		Criteria c = example.createCriteria();
		c.andCourseIdEqualTo(courseId);
		c.andStatusIdEqualTo(statusId);
		example.setOrderByClause("sort asc,id desc");
		return this.courseChapterMapper.selectByExample(example);
	}
}