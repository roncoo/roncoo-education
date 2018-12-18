package com.roncoo.education.course.service.controller.gateway.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.course.common.bean.bo.auth.AuthCourseChapterPeriodAuditDeleteBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthCourseChapterPeriodAuditSaveBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthCourseChapterPeriodAuditSortBO;
import com.roncoo.education.course.common.bean.bo.auth.AuthCourseChapterPeriodAuditUpdateBO;
import com.roncoo.education.course.common.bean.dto.auth.AuthCourseChapterPeriodAuditListDTO;
import com.roncoo.education.course.common.bean.dto.auth.AuthCourseChapterPeriodAuditSaveDTO;
import com.roncoo.education.course.common.bean.dto.auth.AuthCourseChapterPeriodAuditViewDTO;
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
	public Result<AuthCourseChapterPeriodAuditListDTO> listByChapterId(@PathVariable(name = "chapterId") Long chapterId) {
		return biz.listByChapterId(chapterId);
	}

	@Override
	public Result<AuthCourseChapterPeriodAuditViewDTO> view(@PathVariable(name = "id") Long id) {
		return biz.view(id);
	}

	@Override
	public Result<Integer> delete(@RequestBody AuthCourseChapterPeriodAuditDeleteBO bo) {
		return biz.delete(bo);
	}

	@Override
	public Result<AuthCourseChapterPeriodAuditSaveDTO> save(@RequestBody AuthCourseChapterPeriodAuditSaveBO bo) {
		return biz.save(bo);
	}

	@Override
	public Result<Integer> update(@RequestBody AuthCourseChapterPeriodAuditUpdateBO bo) {
		return biz.update(bo);
	}

	@Override
	public Result<Integer> sort(@RequestBody AuthCourseChapterPeriodAuditSortBO bo) {
		return biz.sort(bo);
	}

}
