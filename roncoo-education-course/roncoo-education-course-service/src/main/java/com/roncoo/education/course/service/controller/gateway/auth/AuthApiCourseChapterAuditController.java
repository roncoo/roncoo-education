package com.roncoo.education.course.service.controller.gateway.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.course.common.bean.bo.auth.AuthCourseChapterAuditDeleteBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthCourseChapterAuditSaveBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthCourseChapterAuditSortBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthCourseChapterAuditUpdateBO;
import com.roncoo.education.course.common.bean.dto.auth.AuthCourseChapterAuditListDTO;
import com.roncoo.education.course.common.bean.dto.auth.AuthCourseChapterAuditSaveDTO;
import com.roncoo.education.course.common.bean.dto.auth.AuthCourseChapterAuditViewDTO;
import com.roncoo.education.course.common.interfaces.gateway.auth.AuthApiCourseChapterAudit;
import com.roncoo.education.course.common.bean.dto.auth.AuthCourseChapterAuditUpdateDTO;
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
	public Result<AuthCourseChapterAuditViewDTO> view(@PathVariable(name = "id") Long id) {
		return biz.view(id);
	}

	@Override
	public Result<Integer> delete(@RequestBody AuthCourseChapterAuditDeleteBO authCourseChapterAuditDeleteBO) {
		return biz.delete(authCourseChapterAuditDeleteBO);
	}

	@Override
	public Result<AuthCourseChapterAuditListDTO> listByCourseNo(@PathVariable(name = "courseId") Long courseId) {
		return biz.listByCourseNo(courseId);
	}

	@Override
	public Result<AuthCourseChapterAuditSaveDTO> save(@RequestBody AuthCourseChapterAuditSaveBO bo) {
		return biz.save(bo);
	}

	@Override
	public Result<AuthCourseChapterAuditUpdateDTO> update(@RequestBody AuthCourseChapterAuditUpdateBO bo) {
		return biz.update(bo);
	}

	@Override
	public Result<Integer> sort(@RequestBody AuthCourseChapterAuditSortBO bo) {
		return biz.sort(bo);
	}

}
