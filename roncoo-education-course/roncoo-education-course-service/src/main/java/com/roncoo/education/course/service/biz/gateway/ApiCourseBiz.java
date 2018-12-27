package com.roncoo.education.course.service.biz.gateway;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.roncoo.education.course.common.bean.bo.CourseInfoPageBO;
import com.roncoo.education.course.common.bean.bo.CourseVideoBO;
import com.roncoo.education.course.common.bean.dto.CourseChapterDTO;
import com.roncoo.education.course.common.bean.dto.CourseChapterPeriodDTO;
import com.roncoo.education.course.common.bean.dto.CourseInfoPageDTO;
import com.roncoo.education.course.common.bean.dto.CourseIntroduceDTO;
import com.roncoo.education.course.common.bean.dto.CourseViewDTO;
import com.roncoo.education.course.service.dao.CourseChapterDao;
import com.roncoo.education.course.service.dao.CourseChapterPeriodDao;
import com.roncoo.education.course.service.dao.CourseDao;
import com.roncoo.education.course.service.dao.CourseIntroduceDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.Course;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseChapter;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseChapterPeriod;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseExample;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseExample.Criteria;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseIntroduce;
import com.roncoo.education.user.common.bean.dto.LecturerDTO;
import com.roncoo.education.user.common.bean.vo.LecturerVO;
import com.roncoo.education.user.feign.web.IBossLecturer;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.enums.IsPutawayEnum;
import com.roncoo.education.util.enums.StatusIdEnum;
import com.roncoo.education.util.tools.BeanUtil;
import com.roncoo.education.util.tools.SqlUtil;
import com.xiaoleilu.hutool.util.CollectionUtil;

/**
 * 课程信息
 *
 * @author wujing
 */
@Component
public class ApiCourseBiz {

	@Autowired
	private CourseDao courseDao;
	@Autowired
	private CourseIntroduceDao courseIntroduceDao;
	@Autowired
	private CourseChapterDao courseChapterDao;
	@Autowired
	private CourseChapterPeriodDao courseChapterPeriodDao;
	@Autowired
	private IBossLecturer bossLecturer;

	/**
	 * 课程详情接口
	 * 
	 * @param courseView
	 * @return
	 */
	public Result<CourseViewDTO> view(CourseVideoBO courseView) {
		if (courseView.getCourseId() == null) {
			return Result.error("课程ID不能为空");
		}
		// 课程信息
		Course course = courseDao.getById(courseView.getCourseId());
		if (course == null) {
			return Result.error("找不到该课程信息");
		}
		CourseViewDTO data = BeanUtil.copyProperties(course, CourseViewDTO.class);

		// 课程介绍
		CourseIntroduce courseIntroduce = courseIntroduceDao.getById(data.getIntroduceId());
		if (!StringUtils.isEmpty(courseIntroduce)) {
			data.setIntroduce(BeanUtil.copyProperties(courseIntroduce, CourseIntroduceDTO.class).getIntroduce());
		}

		// 讲师信息
		LecturerVO lecturerVO = bossLecturer.getByLecturerUserNo(data.getLecturerUserNo());
		if (StringUtils.isEmpty(lecturerVO)) {
			return Result.error("根据讲师用户编号没找到对应的讲师信息!");
		}
		data.setLecturerDTO(BeanUtil.copyProperties(lecturerVO, LecturerDTO.class));

		// 章节信息
		List<CourseChapter> courseChapterList = courseChapterDao.listByCourseIdAndStatusId(courseView.getCourseId(), StatusIdEnum.YES.getCode());
		if (CollectionUtil.isNotEmpty(courseChapterList)) {
			data.setChapterList(PageUtil.copyList(courseChapterList, CourseChapterDTO.class));
		}

		// 课时信息
		if (CollectionUtil.isNotEmpty(data.getChapterList())) {
			for (CourseChapterDTO courseChapterDTO : data.getChapterList()) {
				List<CourseChapterPeriod> courseChapterPeriodList = courseChapterPeriodDao.listByChapterIdAndStatusId(courseChapterDTO.getId(), StatusIdEnum.YES.getCode());
				courseChapterDTO.setCourseChapterPeriodList(PageUtil.copyList(courseChapterPeriodList, CourseChapterPeriodDTO.class));
			}
		}
		return Result.success(data);
	}

	/**
	 * 课程信息列表接口
	 * 
	 * @param courseBO
	 * @return
	 * @author wuyun
	 */
	public Result<Page<CourseInfoPageDTO>> list(CourseInfoPageBO courseBO) {
		CourseExample example = new CourseExample();
		Criteria c = example.createCriteria();
		c.andStatusIdEqualTo(StatusIdEnum.YES.getCode());
		c.andIsPutawayEqualTo(IsPutawayEnum.YES.getCode());
		if (!StringUtils.isEmpty(courseBO.getCategoryId1())) {
			c.andCategoryId1EqualTo(courseBO.getCategoryId1());
		}
		if (!StringUtils.isEmpty(courseBO.getCategoryId2())) {
			c.andCategoryId2EqualTo(courseBO.getCategoryId2());
		}
		if (!StringUtils.isEmpty(courseBO.getCategoryId3())) {
			c.andCategoryId3EqualTo(courseBO.getCategoryId3());
		}
		if (!StringUtils.isEmpty(courseBO.getIsFree())) {
			c.andIsFreeEqualTo(courseBO.getIsFree());
		}
		if (!StringUtils.isEmpty(courseBO.getCourseName())) {
			c.andCourseNameLike(PageUtil.rightLike(SqlUtil.checkSql(courseBO.getCourseName())));
		}
		example.setOrderByClause(" course_sort desc, id desc ");
		Page<Course> page = courseDao.listForPage(courseBO.getPageCurrent(), courseBO.getPageSize(), example);
		return Result.success(PageUtil.transform(page, CourseInfoPageDTO.class));
	}
}
