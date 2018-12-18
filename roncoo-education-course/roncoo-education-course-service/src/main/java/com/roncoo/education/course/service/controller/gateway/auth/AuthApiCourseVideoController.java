package com.roncoo.education.course.service.controller.gateway.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.course.common.bean.bo.auth.AuthCourseVideoDeleteBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthCourseVideoSaveBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthCourseVideoUpdateBO;
import com.roncoo.education.course.common.bean.dto.auth.AuthCourseVideoListDTO;
import com.roncoo.education.course.common.interfaces.gateway.auth.AuthApiCourseVideo;
import com.roncoo.education.course.service.biz.gateway.auth.AuthApiCourseVideoBiz;
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
	public Result<Integer> save(@RequestBody AuthCourseVideoSaveBO bo) {
		return biz.save(bo);
	}

	@Override
	public Result<AuthCourseVideoListDTO> listByChapterId(@PathVariable(name = "chapterId") Long chapterId) {
		return biz.listByChapterId(chapterId);
	}

	@Override
	public Result<AuthCourseVideoListDTO> listByPeriodId(@PathVariable(name = "periodId") Long periodId) {
		return biz.listByPeriodId(periodId);
	}

	@Override
	public Result<Integer> update(@RequestBody AuthCourseVideoUpdateBO bo) {
		return biz.update(bo);
	}

	@Override
	public Result<Integer> udpateById(@RequestBody AuthCourseVideoDeleteBO bo) {
		return biz.udpateById(bo);
	}

}
