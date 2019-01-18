package com.roncoo.education.course.common.interfaces;

import java.io.File;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.roncoo.education.course.common.bean.qo.CourseVideoQO;
import com.roncoo.education.course.common.bean.vo.CourseVideoVO;
import com.roncoo.education.util.base.Page;

/**
 * 课程视频信息
 *
 * @author wuyun
 */
public interface BossCourseVideo {

	@RequestMapping(value = "/boss/course/courseVideo/listForPage")
	Page<CourseVideoVO> listForPage(@RequestBody CourseVideoQO qo);

	@RequestMapping(value = "/boss/course/courseVideo/save")
	int save(@RequestBody CourseVideoQO qo);

	@RequestMapping(value = "/boss/course/courseVideo/deleteById")
	int deleteById(@RequestBody Long id);

	@RequestMapping(value = "/boss/course/courseVideo/updateById")
	int updateById(@RequestBody CourseVideoQO qo);

	@RequestMapping(value = "/boss/course/courseVideo/getById")
	CourseVideoVO getById(@RequestBody Long id);

	/**
	 * 定时任务-视频处理
	 * 
	 * @param targetFile
	 * @author wuyun
	 */
	@RequestMapping(value = "/boss/course/courseVideo/gethandleScheduledTasksById")
	void handleScheduledTasks(@RequestBody File targetFile);

}
