package com.roncoo.education.course.common.interfaces;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.course.common.bean.qo.CourseRecommendQO;
import com.roncoo.education.course.common.bean.vo.CourseRecommendVO;
import com.roncoo.education.util.base.Page;

/**
 * 课程推荐
 *
 * @author wujing
 */
public interface BossCourseRecommend {

	@RequestMapping(value = "/boss/course/courseRecommend/listForPage", method = RequestMethod.POST)
	Page<CourseRecommendVO> listForPage(@RequestBody CourseRecommendQO qo);

	@RequestMapping(value = "/boss/course/courseRecommend/save", method = RequestMethod.POST)
	int save(@RequestBody CourseRecommendQO qo);

	@RequestMapping(value = "/boss/course/courseRecommend/delete/{id}", method = RequestMethod.DELETE)
	int deleteById(@PathVariable(value = "id") Long id);

	@RequestMapping(value = "/boss/course/courseRecommend/update", method = RequestMethod.PUT)
	int updateById(@RequestBody CourseRecommendQO qo);

	@RequestMapping(value = "/boss/course/courseRecommend/get/{id}", method = RequestMethod.GET)
	CourseRecommendVO getById(@PathVariable(value = "id") Long id);

}
