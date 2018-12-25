package com.roncoo.education.course.service.controller.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.course.common.bean.bo.CourseInfoPageBO;
import com.roncoo.education.course.common.bean.bo.CourseVideoBO;
import com.roncoo.education.course.common.bean.dto.CourseInfoPageDTO;
import com.roncoo.education.course.common.bean.dto.CourseViewDTO;
import com.roncoo.education.course.common.interfaces.gateway.ApiCourse;
import com.roncoo.education.course.service.biz.gateway.ApiCourseBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;

/**
 * 课程信息
 *
 * @author wujing
 */
@RestController
public class ApiCourseController extends BaseController implements ApiCourse {

	@Autowired
	private ApiCourseBiz biz;

	@Override
	public Result<Page<CourseInfoPageDTO>> list(@RequestBody CourseInfoPageBO courseBO) {
		return biz.list(courseBO);
	}

	@Override
	public Result<CourseViewDTO> view(@PathVariable CourseVideoBO courseView) {
		return biz.view(courseView);
	}
}
