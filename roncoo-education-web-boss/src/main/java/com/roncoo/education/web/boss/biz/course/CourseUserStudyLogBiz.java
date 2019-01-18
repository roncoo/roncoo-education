package com.roncoo.education.web.boss.biz.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.course.common.bean.qo.CourseUserStudyLogQO;
import com.roncoo.education.course.common.bean.vo.CourseUserStudyLogVO;
import com.roncoo.education.course.feign.IBossCourseUserStudyLog;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Page;

/**
 * 课程用户学习日志 
 *
 * @author wujing
 */
@Component
public class CourseUserStudyLogBiz extends BaseBiz{

	@Autowired
	private IBossCourseUserStudyLog bossCourseUserStudyLog;

	public Page<CourseUserStudyLogVO> listForPage(CourseUserStudyLogQO qo) {
        return bossCourseUserStudyLog.listForPage(qo);
	}

	public int save(CourseUserStudyLogQO qo) {
		return bossCourseUserStudyLog.save(qo);
	}

	public int deleteById(Long id) {
		return bossCourseUserStudyLog.deleteById(id);
	}

	public CourseUserStudyLogVO getById(Long id) {
		return bossCourseUserStudyLog.getById(id);
	}

	public int updateById(CourseUserStudyLogQO qo) {
		return bossCourseUserStudyLog.updateById(qo);
	}
	
}
