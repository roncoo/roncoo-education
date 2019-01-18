package com.roncoo.education.course.service.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.course.common.interfaces.gateway.auth.AuthApiCourse;
import com.roncoo.education.course.service.biz.auth.AuthApiCourseBiz;
import com.roncoo.education.course.service.common.bo.auth.AuthCourseSignBO;
import com.roncoo.education.course.service.common.bo.auth.AuthCourseViewBO;
import com.roncoo.education.course.service.common.dto.auth.AuthCourseSignDTO;
import com.roncoo.education.course.service.common.dto.auth.AuthCourseViewDTO;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;

/**
 * 课程信息-审核
 *
 * @author wujing
 */
@RestController
public class AuthApiCourseController extends BaseController implements AuthApiCourse {

	@Autowired
	private AuthApiCourseBiz biz;

	@Override
	public Result<AuthCourseViewDTO> view(@RequestBody AuthCourseViewBO authCourseViewBO) {
		return biz.view(authCourseViewBO);
	}

	@Override
	public Result<AuthCourseSignDTO> sign(@RequestBody AuthCourseSignBO authCourseSignBO) {
		return biz.sign(authCourseSignBO);
	}

}
