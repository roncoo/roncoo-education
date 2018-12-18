package com.roncoo.education.course.service.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.roncoo.education.course.service.dao.CourseDao;
import com.roncoo.education.course.service.dao.impl.mapper.CourseMapper;
import com.roncoo.education.course.service.dao.impl.mapper.entity.Course;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseExample;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseExample.Criteria;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;

@Repository
public class CourseDaoImpl implements CourseDao {
    @Autowired
    private CourseMapper courseMapper;

    public int save(Course record) {
        return this.courseMapper.insertSelective(record);
    }

    public int deleteById(Long id) {
        return this.courseMapper.deleteByPrimaryKey(id);
    }

    public int updateById(Course record) {
    	record.setGmtCreate(null);
		record.setGmtModified(null);
        return this.courseMapper.updateByPrimaryKeySelective(record);
    }

    public Course getById(Long id) {
        return this.courseMapper.selectByPrimaryKey(id);
    }

    public Page<Course> listForPage(int pageCurrent, int pageSize, CourseExample example) {
        int count = this.courseMapper.countByExample(example);
        pageSize = PageUtil.checkPageSize(pageSize);
        pageCurrent = PageUtil.checkPageCurrent(count, pageSize, pageCurrent);
        int totalPage = PageUtil.countTotalPage(count, pageSize);
        example.setLimitStart(PageUtil.countOffset(pageCurrent, pageSize));
        example.setPageSize(pageSize);
        return new Page<Course>(count, totalPage, pageCurrent, pageSize, this.courseMapper.selectByExample(example));
    }

	@Override
	public List<Course> listByCategoryId(Long courseId) {
		CourseExample example = new CourseExample();
		Criteria c = example.createCriteria();
		c.andIdEqualTo(courseId);
		List<Course> list = this.courseMapper.selectByExample(example);
		return list;
	}

	@Override
	public Course getByCourseIdAndStatusId(Long courseId, Integer StatusId) {
		CourseExample example = new CourseExample();
		Criteria c = example.createCriteria();
		c.andIdEqualTo(courseId);
		c.andStatusIdEqualTo(StatusId);
		List<Course> list = this.courseMapper.selectByExample(example);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
	}

	@Override
	public Course getByCourseName(String courseName) {
		CourseExample example = new CourseExample();
		Criteria c = example.createCriteria();
		c.andCourseNameLike(PageUtil.rightLike(courseName));
		List<Course> courseList =  this.courseMapper.selectByExample(example);
		if (courseList.isEmpty()) {
			return null;
		}
		return courseList.get(0);
	}

	@Override
	public List<Course> listBycategoryId2AndStatusId(Long categoryId2, Integer statusId) {
		CourseExample example = new CourseExample();
		Criteria c = example.createCriteria();
		c.andCategoryId2EqualTo(categoryId2);
		c.andStatusIdEqualTo(statusId);
		example.setOrderByClause("sort desc,id desc");
		return this.courseMapper.selectByExample(example);
	}
}