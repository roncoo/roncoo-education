package com.roncoo.education.course.service.api.pc.biz;

import com.roncoo.education.course.common.req.CourseUserStudyLogPageREQ;
import com.roncoo.education.course.common.req.CourseUserStudyLogPlayREQ;
import com.roncoo.education.course.common.resq.CourseUserStudyLogPageRESQ;
import com.roncoo.education.course.common.resq.CourseUserStudyLogPlayRESQ;
import com.roncoo.education.course.service.dao.CourseChapterDao;
import com.roncoo.education.course.service.dao.CourseChapterPeriodDao;
import com.roncoo.education.course.service.dao.CourseDao;
import com.roncoo.education.course.service.dao.CourseUserStudyLogDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.*;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseUserStudyLogExample.Criteria;
import com.roncoo.education.user.feign.vo.UserExtVO;
import com.roncoo.education.user.feign.interfaces.IFeignUserExt;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.base.Result;
import com.roncoo.education.util.tools.DateUtil;
import com.xiaoleilu.hutool.util.ObjectUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 *
 * 课程用户学习日志
 *
 */
@Component
public class PcApiCourseUserStudyLogBiz {

	@Autowired
	private IFeignUserExt bossUserExt;

	@Autowired
	private CourseUserStudyLogDao dao;
	@Autowired
	private CourseDao courseDao;
	@Autowired
	private CourseChapterDao chapterDao;
	@Autowired
	private CourseChapterPeriodDao periodDao;

	public Result<Page<CourseUserStudyLogPageRESQ>> list(CourseUserStudyLogPageREQ req) {
		if (StringUtils.isEmpty(req.getUserExtNo())) {
			return Result.error("用户编号不能为空");
		}
		// 获取用户信息
		UserExtVO userExtVO = bossUserExt.getByUserNo(req.getUserExtNo());
		if (ObjectUtil.isNull(userExtVO)) {
			return Result.error("找不到用户信息");
		}
		CourseUserStudyLogExample example = new CourseUserStudyLogExample();
		Criteria c = example.createCriteria();
		c.andUserNoEqualTo(req.getUserExtNo());
		if (StringUtils.hasText(req.getBeginGmtCreate())) {
			c.andGmtCreateGreaterThanOrEqualTo(DateUtil.parseDate(req.getBeginGmtCreate(), "yyyy-MM-dd"));
		}
		if (StringUtils.hasText(req.getEndGmtCreate())) {
			c.andGmtCreateLessThanOrEqualTo(DateUtil.addDate(DateUtil.parseDate(req.getEndGmtCreate(), "yyyy-MM-dd"), 1));
		}
		example.setOrderByClause(" id desc ");
		Page<CourseUserStudyLog> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
		Page<CourseUserStudyLogPageRESQ> listForPage = PageUtil.transform(page, CourseUserStudyLogPageRESQ.class);
		for (CourseUserStudyLogPageRESQ resq : listForPage.getList()) {
			if (resq.getCourseId() != null || resq.getCourseId() != 0) {
				Course course = courseDao.getById(resq.getCourseId());
				resq.setCourseName(course.getCourseName());
			}
			if (resq.getChapterId() != null || resq.getChapterId() != 0) {
				CourseChapter chapter = chapterDao.getById(resq.getChapterId());
				resq.setChapterName(chapter.getChapterName());
			}
			if (resq.getPeriodId() != null || resq.getPeriodId() != 0) {
				CourseChapterPeriod period = periodDao.getById(resq.getPeriodId());
				resq.setPeriodName(period.getPeriodName());
			}
		}
		return Result.success(listForPage);
	}

	public Result<Page<CourseUserStudyLogPlayRESQ>> play(CourseUserStudyLogPlayREQ req) {
		Page<CourseUserStudyLog> list = dao.periodList(req.getCourseId(), req.getPageCurrent(), req.getPageSize(), req.getBeginGmtCreate(), req.getEndGmtCreate());
		return Result.success(PageUtil.transform(list, CourseUserStudyLogPlayRESQ.class));
	}

}
