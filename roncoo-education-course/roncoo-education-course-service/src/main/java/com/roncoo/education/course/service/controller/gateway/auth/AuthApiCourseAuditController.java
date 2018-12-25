package com.roncoo.education.course.service.controller.gateway.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.course.common.bean.bo.auth.AuthCourseAuditDeleteBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthCourseAuditListBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthCourseAuditSaveBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthCourseAuditStandBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthCourseAuditUpdateBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthCourseAuditViewBO;
import com.roncoo.education.course.common.bean.dto.auth.AuthCourseAuditListDTO;
import com.roncoo.education.course.common.bean.dto.auth.AuthCourseAuditSaveDTO;
import com.roncoo.education.course.common.bean.dto.auth.AuthCourseAuditViewDTO;
import com.roncoo.education.course.common.interfaces.gateway.auth.AuthApiCourseAudit;
import com.roncoo.education.course.service.biz.gateway.auth.AuthApiCourseAuditBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;

/**
 * 课程信息-审核
 *
 * @author wujing
 */
@RestController
public class AuthApiCourseAuditController extends BaseController implements AuthApiCourseAudit {

	@Autowired
	private AuthApiCourseAuditBiz biz;

	@Override
	public Result<AuthCourseAuditViewDTO> view(@RequestBody AuthCourseAuditViewBO authCourseAuditViewBO) {
		return biz.view(authCourseAuditViewBO);
	}

	@Override
	public Result<Page<AuthCourseAuditListDTO>> listForPage(@RequestBody AuthCourseAuditListBO authCourseAuditListBO) {
		return biz.listForPage(authCourseAuditListBO);
	}

	@Override
	public Result<AuthCourseAuditSaveDTO> save(@RequestBody AuthCourseAuditSaveBO authCourseAuditSaveBO) {
		return biz.save(authCourseAuditSaveBO);
	}

	@Override
	public Result<Integer> update(@RequestBody AuthCourseAuditUpdateBO authCourseAuditUpdateBO) {
		return biz.update(authCourseAuditUpdateBO);
	}

	@Override
	public Result<Integer> stand(@RequestBody AuthCourseAuditStandBO authCourseAuditStandBO) {
		return biz.stand(authCourseAuditStandBO);
	}

	@Override
	public Result<Integer> delete(@RequestBody AuthCourseAuditDeleteBO authCourseAuditDeleteBO) {
		return biz.delete(authCourseAuditDeleteBO);
	}

}
