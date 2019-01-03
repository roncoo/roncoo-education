package com.roncoo.education.course.service.controller.gateway.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.course.common.bean.bo.auth.AuthCourseChapterAuditBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthCourseChapterAuditDeleteBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthCourseChapterAuditSaveBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthCourseChapterAuditSortBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthCourseChapterAuditUpdateBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthCourseChapterAuditViewBO;
import com.roncoo.education.course.common.bean.dto.auth.AuthCourseChapterAuditListDTO;
import com.roncoo.education.course.common.bean.dto.auth.AuthCourseChapterAuditSaveDTO;
import com.roncoo.education.course.common.bean.dto.auth.AuthCourseChapterAuditUpdateDTO;
import com.roncoo.education.course.common.bean.dto.auth.AuthCourseChapterAuditViewDTO;
import com.roncoo.education.course.common.interfaces.gateway.auth.AuthApiCourseChapterAudit;
import com.roncoo.education.course.service.biz.gateway.auth.AuthApiCourseChapterAuditBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;

/**
 * 章节信息-审核
 *
 * @author wujing
 */
@RestController
public class AuthApiCourseChapterAuditController extends BaseController implements AuthApiCourseChapterAudit {

	@Autowired
	private AuthApiCourseChapterAuditBiz biz;

	@Override
	public Result<AuthCourseChapterAuditViewDTO> view(@RequestBody AuthCourseChapterAuditViewBO authCourseChapterAuditViewBO) {
		return biz.view(authCourseChapterAuditViewBO);
	}

	@Override
	public Result<Integer> delete(@RequestBody AuthCourseChapterAuditDeleteBO authCourseChapterAuditDeleteBO) {
		return biz.delete(authCourseChapterAuditDeleteBO);
	}

	@Override
	public Result<AuthCourseChapterAuditListDTO> listByCourseNo(@RequestBody AuthCourseChapterAuditBO authCourseChapterAuditBO) {
		return biz.listByCourseNo(authCourseChapterAuditBO);
	}

	@Override
	public Result<AuthCourseChapterAuditSaveDTO> save(@RequestBody AuthCourseChapterAuditSaveBO authCourseChapterAuditSaveBO) {
		return biz.save(authCourseChapterAuditSaveBO);
	}

	@Override
	public Result<AuthCourseChapterAuditUpdateDTO> update(@RequestBody AuthCourseChapterAuditUpdateBO authCourseChapterAuditUpdateBO) {
		return biz.update(authCourseChapterAuditUpdateBO);
	}

	@Override
	public Result<Integer> sort(@RequestBody AuthCourseChapterAuditSortBO authCourseChapterAuditSortBO) {
		return biz.sort(authCourseChapterAuditSortBO);
	}

}
