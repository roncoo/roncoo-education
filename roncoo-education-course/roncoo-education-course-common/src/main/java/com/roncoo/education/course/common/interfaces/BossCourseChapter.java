package com.roncoo.education.course.common.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.course.common.bean.qo.CourseChapterQO;
import com.roncoo.education.course.common.bean.vo.CourseChapterVO;
import com.roncoo.education.util.base.Page;

/**
 * 章节信息 
 *
 * @author wujing
 */
public interface BossCourseChapter {
	
	@RequestMapping(value = "/boss/course/courseChapter/listForPage", method = RequestMethod.POST)
	Page<CourseChapterVO> listForPage(@RequestBody CourseChapterQO qo);
	
	@RequestMapping(value = "/boss/course/courseChapter/save", method = RequestMethod.POST)
	int save(@RequestBody CourseChapterQO qo);
	
	@RequestMapping(value = "/boss/course/courseChapter/delete/{id}", method = RequestMethod.DELETE)
	int deleteById(@PathVariable(value = "id") Long id);
	
	@RequestMapping(value = "/boss/course/courseChapter/update", method = RequestMethod.PUT)
	int updateById(@RequestBody CourseChapterQO qo);
	
	@RequestMapping(value = "/boss/course/courseChapter/get/{id}", method = RequestMethod.GET)
	CourseChapterVO getById(@PathVariable(value = "id") Long id);
	
}
