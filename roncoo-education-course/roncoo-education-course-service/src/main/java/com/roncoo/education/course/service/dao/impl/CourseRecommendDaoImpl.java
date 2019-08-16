package com.roncoo.education.course.service.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.roncoo.education.course.service.dao.CourseRecommendDao;
import com.roncoo.education.course.service.dao.impl.mapper.CourseRecommendMapper;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseRecommend;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseRecommendExample;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseRecommendExample.Criteria;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.IdWorker;

@Repository
public class CourseRecommendDaoImpl implements CourseRecommendDao {
	@Autowired
	private CourseRecommendMapper courseRecommendMapper;

	public int save(CourseRecommend record) {
		record.setId(IdWorker.getId());
		return this.courseRecommendMapper.insertSelective(record);
	}

	public int deleteById(Long id) {
		return this.courseRecommendMapper.deleteByPrimaryKey(id);
	}

	public int updateById(CourseRecommend record) {
		return this.courseRecommendMapper.updateByPrimaryKeySelective(record);
	}

	public CourseRecommend getById(Long id) {
		return this.courseRecommendMapper.selectByPrimaryKey(id);
	}

	public Page<CourseRecommend> listForPage(int pageCurrent, int pageSize, CourseRecommendExample example) {
		int count = this.courseRecommendMapper.countByExample(example);
		pageSize = PageUtil.checkPageSize(pageSize);
		pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
		int totalPage = PageUtil.countTotalPage(count, pageSize);
		example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
		example.setPageSize(pageSize);
		return new Page<CourseRecommend>(count, totalPage, pageCurrent, pageSize, this.courseRecommendMapper.selectByExample(example));
	}

	@Override
	public List<CourseRecommend> listByCategoryIdAndStatusId(Long categoryId, Integer StatusId) {
		CourseRecommendExample example = new CourseRecommendExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		criteria.andStatusIdEqualTo(StatusId);
		return this.courseRecommendMapper.selectByExample(example);
	}

	@Override
	public CourseRecommend getByCategoryIdAndCourseId(Long categoryId, Long courseId) {
		CourseRecommendExample example = new CourseRecommendExample();
		Criteria criteria = example.createCriteria();
		criteria.andCategoryIdEqualTo(categoryId);
		criteria.andCourseIdEqualTo(courseId);
		List<CourseRecommend> list = this.courseRecommendMapper.selectByExample(example);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}
}