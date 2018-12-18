package com.roncoo.education.course.service.biz.web;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.roncoo.education.course.common.bean.qo.CourseQO;
import com.roncoo.education.course.common.bean.vo.CourseChapterPeriodVO;
import com.roncoo.education.course.common.bean.vo.CourseChapterVO;
import com.roncoo.education.course.common.bean.vo.CourseVO;
import com.roncoo.education.course.service.dao.CourseCategoryDao;
import com.roncoo.education.course.service.dao.CourseChapterDao;
import com.roncoo.education.course.service.dao.CourseChapterPeriodDao;
import com.roncoo.education.course.service.dao.CourseDao;
import com.roncoo.education.course.service.dao.ZoneCourseDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.Course;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseCategory;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseChapter;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseChapterPeriod;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseExample;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseExample.Criteria;
import com.roncoo.education.course.service.dao.impl.mapper.entity.ZoneCourse;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.enums.StatusIdEnum;
import com.roncoo.education.util.tools.BeanUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;

/**
 * 课程信息
 *
 * @author wujing
 */
@Component
public class BossCourseBiz {

	@Autowired
	private CourseDao dao;
	@Autowired
	private CourseCategoryDao courseCategoryDao;
	@Autowired
	private CourseChapterDao courseChapterDao;
	@Autowired
	private CourseChapterPeriodDao courseChapterPeriodDao;
	@Autowired
	private ZoneCourseDao zoneCourseDao;

	public Page<CourseVO> listForPage(CourseQO qo) {
		CourseExample example = new CourseExample();
		Criteria c = example.createCriteria();
		if (CollectionUtils.isNotEmpty(qo.getNotInCourseNoList())) {
			if (ObjectUtil.isNull(qo.getZoneId())) {
				List<ZoneCourse> list = zoneCourseDao.listByZoneId(qo.getZoneId());
				if (CollectionUtils.isNotEmpty(list)) {
					List<Long> notInCourseNoList = new ArrayList<>();
					for (ZoneCourse zoneCourse : list) {
						notInCourseNoList.add(zoneCourse.getCourseId());
					}
					qo.setNotInCourseNoList(notInCourseNoList);
				}
			}
		}
		if (qo.getNotInCourseNoList() != null && !qo.getNotInCourseNoList().isEmpty()) {
			c.andIdNotIn(qo.getNotInCourseNoList());
		}
		if (!StringUtils.isEmpty(qo.getCourseName())) {
			c.andCourseNameLike(PageUtil.rightLike(qo.getCourseName()));
		}
		if (qo.getStatusId() != null) {
			c.andStatusIdEqualTo(qo.getStatusId());
		}
		if (qo.getIsFree() != null) {
			c.andIsFreeEqualTo(qo.getIsFree());
		}
		if (qo.getIsPutaway() != null) {
			c.andIsPutawayEqualTo(qo.getIsPutaway());
		}
		example.setOrderByClause(" status_id desc, is_putaway desc, course_sort desc, id desc ");
		Page<Course> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
		Page<CourseVO> courseInfoVoPage = PageUtil.transform(page, CourseVO.class);
		// 获取分类名称
		for (CourseVO courseVO : courseInfoVoPage.getList()) {
			if (courseVO.getCategoryId1() != null && courseVO.getCategoryId1() != 0) {
				CourseCategory courseCategory = courseCategoryDao.getById(courseVO.getCategoryId1());
				if (!StringUtils.isEmpty(courseCategory)) {
					courseVO.setCategoryName1(courseCategory.getCategoryName());
				}
			}
			if (courseVO.getCategoryId2() != null && courseVO.getCategoryId2() != 0) {
				CourseCategory courseCategory = courseCategoryDao.getById(courseVO.getCategoryId2());
				if (!StringUtils.isEmpty(courseCategory)) {
					courseVO.setCategoryName2(courseCategory.getCategoryName());
				}
			}
			if (courseVO.getCategoryId3() != null && courseVO.getCategoryId3() != 0) {
				CourseCategory courseCategory = courseCategoryDao.getById(courseVO.getCategoryId3());
				if (!StringUtils.isEmpty(courseCategory)) {
					courseVO.setCategoryName3(courseCategory.getCategoryName());
				}
			}
		}
		return courseInfoVoPage;
	}

	public int save(CourseQO qo) {
		Course record = BeanUtil.copyProperties(qo, Course.class);
		return dao.save(record);
	}

	public int deleteById(Long id) {
		return dao.deleteById(id);
	}

	public CourseVO getById(Long id) {
		Course record = dao.getById(id);
		return BeanUtil.copyProperties(record, CourseVO.class);
	}

	public int updateById(CourseQO qo) {
		Course record = BeanUtil.copyProperties(qo, Course.class);
		return dao.updateById(record);
	}

	public CourseVO getByCourseId(Long id) {
		Course record = dao.getById(id);
		CourseVO courseVO = BeanUtil.copyProperties(record, CourseVO.class);
		// 获取分类名称
		if (courseVO.getCategoryId1() != null && courseVO.getCategoryId1() != 0) {
			CourseCategory courseCategory = courseCategoryDao.getById(courseVO.getCategoryId1());
			courseVO.setCategoryName1(courseCategory.getCategoryName());
		}
		if (courseVO.getCategoryId2() != null && courseVO.getCategoryId2() != 0) {
			CourseCategory courseCategory = courseCategoryDao.getById(courseVO.getCategoryId2());
			courseVO.setCategoryName2(courseCategory.getCategoryName());
		}
		if (courseVO.getCategoryId3() != null && courseVO.getCategoryId3() != 0) {
			CourseCategory courseCategory = courseCategoryDao.getById(courseVO.getCategoryId3());
			courseVO.setCategoryName3(courseCategory.getCategoryName());
		}
		// 章节
		List<CourseChapter> ChapterList = courseChapterDao.listByCourseIdAndStatusId(courseVO.getId(), StatusIdEnum.YES.getCode());
		if (CollectionUtils.isNotEmpty(ChapterList)) {
			List<CourseChapterVO> courseChapterVOList = new ArrayList<>();
			for (CourseChapter courseChapter : ChapterList) {
				// 课时
				List<CourseChapterPeriod> periodList = courseChapterPeriodDao.listByChapterIdAndStatusId(courseChapter.getId(), StatusIdEnum.YES.getCode());
				CourseChapterVO courseChapterVO = BeanUtil.copyProperties(courseChapter, CourseChapterVO.class);
				courseChapterVO.setCourseChapterPeriodVOList(PageUtil.copyList(periodList, CourseChapterPeriodVO.class));
				courseChapterVOList.add(courseChapterVO);
			}
			courseVO.setCourseChapterVOList(courseChapterVOList);
		}
		return courseVO;
	}
}
