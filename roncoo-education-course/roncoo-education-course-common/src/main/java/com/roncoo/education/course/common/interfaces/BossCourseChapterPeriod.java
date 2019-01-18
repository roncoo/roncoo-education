package com.roncoo.education.course.common.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.course.common.bean.qo.CourseChapterPeriodQO;
import com.roncoo.education.course.common.bean.vo.CourseChapterPeriodVO;
import com.roncoo.education.util.base.Page;

/**
 * 课时信息 
 *
 * @author wujing
 */
public interface BossCourseChapterPeriod {
	
	@RequestMapping(value = "/boss/course/courseChapterPeriod/listForPage", method = RequestMethod.POST)
	Page<CourseChapterPeriodVO> listForPage(@RequestBody CourseChapterPeriodQO qo);
	
	@RequestMapping(value = "/boss/course/courseChapterPeriod/save", method = RequestMethod.POST)
	int save(@RequestBody CourseChapterPeriodQO qo);
	
	@RequestMapping(value = "/boss/course/courseChapterPeriod/delete/{id}", method = RequestMethod.DELETE)
	int deleteById(@PathVariable(value = "id") Long id);
	
	@RequestMapping(value = "/boss/course/courseChapterPeriod/update", method = RequestMethod.PUT)
	int updateById(@RequestBody CourseChapterPeriodQO qo);
	
	@RequestMapping(value = "/boss/course/courseChapterPeriod/get/{id}", method = RequestMethod.GET)
	CourseChapterPeriodVO getById(@PathVariable(value = "id") Long id);
	
	/**
	 * 根据视频编号查询课时正式表信息
	 * 
	 * @param videoNo
	 * @return
	 * @author wuyun
	 */
	@RequestMapping(value = "/boss/course/courseChapterPeriod/getByVideoNo", method = RequestMethod.GET)
	CourseChapterPeriodVO getByVideoNo(@RequestBody Long videoNo);
}
