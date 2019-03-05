package com.roncoo.education.course.common.interfaces;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.course.common.bean.qo.CourseUserStudyLogQO;
import com.roncoo.education.course.common.bean.vo.CourseUserStudyLogVO;
import com.roncoo.education.util.base.Page;

/**
 * 课程用户学习日志
 *
 * @author wujing
 */
public interface BossCourseUserStudyLog {

	@RequestMapping(value = "/boss/course/courseUserStudyLog/listForPage", method = RequestMethod.POST)
	Page<CourseUserStudyLogVO> listForPage(@RequestBody CourseUserStudyLogQO qo);

	@RequestMapping(value = "/boss/course/courseUserStudyLog/save", method = RequestMethod.POST)
	int save(@RequestBody CourseUserStudyLogQO qo);

	@RequestMapping(value = "/boss/course/courseUserStudyLog/delete/{id}", method = RequestMethod.DELETE)
	int deleteById(@PathVariable(value = "id") Long id);

	@RequestMapping(value = "/boss/course/courseUserStudyLog/update", method = RequestMethod.PUT)
	int updateById(@RequestBody CourseUserStudyLogQO qo);

	@RequestMapping(value = "/boss/course/courseUserStudyLog/get/{id}", method = RequestMethod.GET)
	CourseUserStudyLogVO getById(@PathVariable(value = "id") Long id);

	/**
	 * 分页-课程播放量排行榜
	 * 
	 * @param qo
	 * @return
	 * @author YZJ
	 */
	@RequestMapping(value = "/boss/course/courseUserStudyLog/courseList", method = RequestMethod.POST)
	Page<CourseUserStudyLogVO> courseList(@RequestBody CourseUserStudyLogQO qo);

	/**
	 * 根据时间取得前五播放量的课程
	 * 
	 * @param qo
	 * @return
	 * @author YZJ
	 */
	@RequestMapping(value = "/boss/course/courseUserStudyLog/countCourseIdByGmtCreate", method = RequestMethod.POST)
	List<CourseUserStudyLogVO> countCourseIdByGmtCreate(@RequestBody CourseUserStudyLogQO qo);

	/**
	 * 分页-课时播放量排行榜
	 * 
	 * @param qo
	 * @return
	 * @author YZJ
	 */
	@RequestMapping(value = "/boss/course/courseUserStudyLog/periodList", method = RequestMethod.POST)
	Page<CourseUserStudyLogVO> periodList(@RequestBody CourseUserStudyLogQO qo);

	/**
	 * 根据时间和课程ID取得前五播放量的课时
	 * 
	 * @param qo
	 * @return
	 * @author YZJ
	 */
	@RequestMapping(value = "/boss/course/courseUserStudyLog/countPeriodNoByCourseIdAndGmtCreate", method = RequestMethod.POST)
	List<CourseUserStudyLogVO> countPeriodNoByCourseIdAndGmtCreate(@RequestBody CourseUserStudyLogQO qo);

}
