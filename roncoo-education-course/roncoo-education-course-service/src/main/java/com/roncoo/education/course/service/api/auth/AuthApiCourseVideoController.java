package com.roncoo.education.course.service.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.course.common.interfaces.gateway.auth.AuthApiCourseVideo;
import com.roncoo.education.course.service.biz.auth.AuthApiCourseVideoBiz;
import com.roncoo.education.course.service.common.bo.auth.AuthCourseVideoBO;
import com.roncoo.education.course.service.common.bo.auth.AuthCourseVideoDeleteBO;
import com.roncoo.education.course.service.common.bo.auth.AuthCourseVideoSaveBO;
import com.roncoo.education.course.service.common.bo.auth.AuthCourseVideoUpdateBO;
import com.roncoo.education.course.service.common.bo.auth.AuthPeriodIdVideoBO;
import com.roncoo.education.course.service.common.dto.auth.AuthCourseVideoListDTO;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;

/**
 * 课程视频信息
 *
 * @author wuyun
 */
@RestController
public class AuthApiCourseVideoController extends BaseController implements AuthApiCourseVideo {

	@Autowired
	private AuthApiCourseVideoBiz biz;

	@Override
	public Result<Integer> save(@RequestBody AuthCourseVideoSaveBO authCourseVideoSaveBO) {
		return biz.save(authCourseVideoSaveBO);
	}

	@Override
	public Result<AuthCourseVideoListDTO> listByChapterId(@RequestBody AuthCourseVideoBO authCourseVideoBO) {
		return biz.listByChapterId(authCourseVideoBO);
	}

	@Override
	public Result<AuthCourseVideoListDTO> listByPeriodId(@RequestBody AuthPeriodIdVideoBO authCourseVideoBO) {
		return biz.listByPeriodId(authCourseVideoBO);
	}

	@Override
	public Result<Integer> update(@RequestBody AuthCourseVideoUpdateBO authCourseVideoUpdateBO) {
		return biz.update(authCourseVideoUpdateBO);
	}

	@Override
	public Result<Integer> udpateById(@RequestBody AuthCourseVideoDeleteBO authCourseVideoDeleteBO) {
		return biz.udpateById(authCourseVideoDeleteBO);
	}

}
