package com.roncoo.education.course.common.interfaces.gateway.auth;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.course.service.common.bo.auth.AuthCourseUserStudyLogPageBO;
import com.roncoo.education.course.service.common.dto.auth.AuthCourseUserStudyLogPageDTO;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 课程用户学习日志
 *
 * @author wuyun
 */
public interface AuthApiCourseUserStudyLog {

	/**
	 * 最近学习日志分页列出接口
	 * 
	 * @param authCourseUserStudyLogPageBO
	 * @return
	 * @author wuyun
	 */
	@ApiOperation(value = "分页列出接口", notes = "最近学习分页列出接口")
	@RequestMapping(value = "/auth/course/api/course/user/study/log/list", method = RequestMethod.POST)
	Result<Page<AuthCourseUserStudyLogPageDTO>> list(@RequestBody AuthCourseUserStudyLogPageBO authCourseUserStudyLogPageBO);
}
