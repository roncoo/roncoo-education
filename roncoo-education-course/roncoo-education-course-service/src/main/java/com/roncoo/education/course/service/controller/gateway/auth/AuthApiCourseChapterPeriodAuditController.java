package com.roncoo.education.course.service.controller.gateway.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.course.common.bean.bo.auth.AuthCourseChapterPeriodAuditBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthCourseChapterPeriodAuditDeleteBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthCourseChapterPeriodAuditSaveBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthCourseChapterPeriodAuditSortBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthCourseChapterPeriodAuditUpdateBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthCourseChapterPeriodAuditViewBO;
import com.roncoo.education.course.common.bean.dto.auth.AuthCourseChapterPeriodAuditSaveDTO;
import com.roncoo.education.course.common.bean.dto.auth.AuthPeriodAuditListDTO;
import com.roncoo.education.course.common.bean.dto.auth.AuthPeriodAuditViewDTO;
import com.roncoo.education.course.common.interfaces.gateway.auth.AuthApiCourseChapterPeriodAudit;
import com.roncoo.education.course.service.biz.gateway.auth.AuthApiCourseChapterPeriodAuditBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;

/**
 * 课时信息-审核
 *
 * @author wujing
 */
@RestController
public class AuthApiCourseChapterPeriodAuditController extends BaseController implements AuthApiCourseChapterPeriodAudit {

	@Autowired
	private AuthApiCourseChapterPeriodAuditBiz biz;

	@Override
	public Result<AuthPeriodAuditListDTO> listByChapterId(@RequestBody AuthCourseChapterPeriodAuditBO authCourseChapterPeriodAuditBO) {
		return biz.listByChapterId(authCourseChapterPeriodAuditBO);
	}

	@Override
	public Result<AuthPeriodAuditViewDTO> view(@RequestBody AuthCourseChapterPeriodAuditViewBO authCourseChapterPeriodAuditViewBO) {
		return biz.view(authCourseChapterPeriodAuditViewBO);
	}

	@Override
	public Result<Integer> delete(@RequestBody AuthCourseChapterPeriodAuditDeleteBO authCourseChapterPeriodAuditDeleteBO) {
		return biz.delete(authCourseChapterPeriodAuditDeleteBO);
	}

	@Override
	public Result<AuthCourseChapterPeriodAuditSaveDTO> save(@RequestBody AuthCourseChapterPeriodAuditSaveBO authCourseChapterPeriodAuditSaveBO) {
		return biz.save(authCourseChapterPeriodAuditSaveBO);
	}

	@Override
	public Result<Integer> update(@RequestBody AuthCourseChapterPeriodAuditUpdateBO authCourseChapterPeriodAuditUpdateBO) {
		return biz.update(authCourseChapterPeriodAuditUpdateBO);
	}

	@Override
	public Result<Integer> sort(@RequestBody AuthCourseChapterPeriodAuditSortBO authCourseChapterPeriodAuditSortBO) {
		return biz.sort(authCourseChapterPeriodAuditSortBO);
	}

}
