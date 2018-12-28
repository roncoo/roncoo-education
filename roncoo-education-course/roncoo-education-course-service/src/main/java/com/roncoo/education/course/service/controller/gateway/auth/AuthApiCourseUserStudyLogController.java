package com.roncoo.education.course.service.controller.gateway.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.course.common.bean.bo.auth.AuthCourseUserStudyLogPageBO;
import com.roncoo.education.course.common.bean.dto.auth.AuthCourseUserStudyLogPageDTO;
import com.roncoo.education.course.common.interfaces.gateway.auth.AuthApiCourseUserStudyLog;
import com.roncoo.education.course.service.biz.gateway.auth.AuthApiCourseUserStudyLogBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;

/**
 * 课程信息-审核
 *
 * @author wujing
 */
@RestController
public class AuthApiCourseUserStudyLogController extends BaseController implements AuthApiCourseUserStudyLog {

	@Autowired
	private AuthApiCourseUserStudyLogBiz biz;

	@Override
	public Result<Page<AuthCourseUserStudyLogPageDTO>> list(@RequestBody AuthCourseUserStudyLogPageBO authCourseUserStudyLogPageBO) {
		return biz.list(authCourseUserStudyLogPageBO);
	}

}
