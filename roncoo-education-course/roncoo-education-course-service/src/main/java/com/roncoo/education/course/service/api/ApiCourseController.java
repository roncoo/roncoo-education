package com.roncoo.education.course.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.course.service.api.biz.ApiCourseBiz;
import com.roncoo.education.course.common.bo.CourseInfoPageBO;
import com.roncoo.education.course.common.bo.CourseInfoSearchBO;
import com.roncoo.education.course.common.bo.CourseVideoBO;
import com.roncoo.education.course.common.dto.CourseInfoPageDTO;
import com.roncoo.education.course.common.dto.CourseInfoSearchPageDTO;
import com.roncoo.education.course.common.dto.CourseViewDTO;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 课程信息
 *
 * @author wujing
 */
@RestController
@RequestMapping(value = "/course/api/course")
public class ApiCourseController extends BaseController {

	@Autowired
	private ApiCourseBiz biz;

	/**
	 * 课程信息列表接口
	 *
	 * @param courseInfoPageBO
	 * @return
	 * @author wuyun
	 */
	@ApiOperation(value = "课程列表接口", notes = "根据条件分页列出课程信息")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<CourseInfoPageDTO>> list(@RequestBody CourseInfoPageBO courseInfoPageBO) {
		return biz.list(courseInfoPageBO);
	}

	/**
	 * 课程详情接口
	 *
	 * @param courseVideoBO
	 * @return
	 */
	@ApiOperation(value = "课程详情接口", notes = "根据课程ID获取课程信息")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<CourseViewDTO> view(@RequestBody CourseVideoBO courseVideoBO) {
		return biz.view(courseVideoBO);
	}

	/**
	 * 搜索课程接口
	 *
	 * @param courseVideoBO
	 * @return
	 */
	@ApiOperation(value = "课程搜索列表接口", notes = "根据课程名称，进行模糊搜索")
	@RequestMapping(value = "/search/list", method = RequestMethod.POST)
	public Result<Page<CourseInfoSearchPageDTO>> view(@RequestBody CourseInfoSearchBO courseInfoSearchBO) {
		return biz.searchList(courseInfoSearchBO);
	}
}
