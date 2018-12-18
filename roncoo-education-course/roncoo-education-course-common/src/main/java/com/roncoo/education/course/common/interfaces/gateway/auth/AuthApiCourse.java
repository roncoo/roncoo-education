package com.roncoo.education.course.common.interfaces.gateway.auth;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.course.common.bean.bo.auth.AuthCourseSignBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthCourseViewBO;
import com.roncoo.education.course.common.bean.dto.auth.AuthCourseSignDTO;
import com.roncoo.education.course.common.bean.dto.auth.AuthCourseViewDTO;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 课程信息-审核
 *
 * @author wujing
 */
public interface AuthApiCourse {

	/**
	 * 课程详情接口
	 */
	@ApiOperation(value = "课程详情接口", notes = "用户登录后获取课程详情信息接口")
	@RequestMapping(value = "/auth/course/api/course/view", method = RequestMethod.POST)
	Result<AuthCourseViewDTO> view(@RequestBody AuthCourseViewBO authCourseViewBO);

	/**
	 * 课时播放获取sign值接口
	 */
	@ApiOperation(value = "课时播放获取sign值接口", notes = "课时播放获取sign值接口")
	@RequestMapping(value = "/auth/course/api/course/sign", method = RequestMethod.POST)
	Result<AuthCourseSignDTO> sign(@RequestBody AuthCourseSignBO authCourseSignBO);
}
