package com.roncoo.education.course.common.interfaces.gateway;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.course.common.bean.bo.CourseInfoPageBO;
import com.roncoo.education.course.common.bean.bo.CourseVideoBO;
import com.roncoo.education.course.common.bean.dto.CourseInfoPageDTO;
import com.roncoo.education.course.common.bean.dto.CourseViewDTO;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 课程信息
 *
 * @author wujing
 */
public interface ApiCourse {

	/**
	 * 课程信息列表接口
	 * 
	 * @param courseBO
	 * @return
	 * @author wuyun
	 */
	@ApiOperation(value = "课程列表接口", notes = "根据条件分页列出课程信息")
	@RequestMapping(value = "/course/api/course/list", method = RequestMethod.POST)
	Result<Page<CourseInfoPageDTO>> list(@RequestBody CourseInfoPageBO courseBO);

	/**
	 * 课程详情接口
	 * 
	 * @param courseId
	 * @return
	 */
	@ApiOperation(value = "课程详情接口", notes = "根据课程ID获取课程信息")
	@RequestMapping(value = "/course/api/course/view", method = RequestMethod.POST)
	Result<CourseViewDTO> view(@RequestBody CourseVideoBO courseViewBO);

}
