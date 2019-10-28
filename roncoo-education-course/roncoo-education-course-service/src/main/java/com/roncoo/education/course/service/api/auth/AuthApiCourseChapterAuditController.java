package com.roncoo.education.course.service.api.auth;

import com.roncoo.education.course.service.api.auth.biz.AuthApiCourseChapterAuditBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.course.common.bo.auth.AuthCourseChapterAuditBO;
import com.roncoo.education.course.common.bo.auth.AuthCourseChapterAuditDeleteBO;
import com.roncoo.education.course.common.bo.auth.AuthCourseChapterAuditSaveBO;
import com.roncoo.education.course.common.bo.auth.AuthCourseChapterAuditSortBO;
import com.roncoo.education.course.common.bo.auth.AuthCourseChapterAuditUpdateBO;
import com.roncoo.education.course.common.bo.auth.AuthCourseChapterAuditViewBO;
import com.roncoo.education.course.common.dto.auth.AuthCourseChapterAuditListDTO;
import com.roncoo.education.course.common.dto.auth.AuthCourseChapterAuditSaveDTO;
import com.roncoo.education.course.common.dto.auth.AuthCourseChapterAuditUpdateDTO;
import com.roncoo.education.course.common.dto.auth.AuthCourseChapterAuditViewDTO;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 章节信息-审核
 *
 * @author wujing
 */
@RestController
@RequestMapping(value = "/course/auth/course/chapter/audit")
public class AuthApiCourseChapterAuditController extends BaseController {

	@Autowired
	private AuthApiCourseChapterAuditBiz biz;

	/**
	 * 章节查看接口
	 */
	@ApiOperation(value = "章节查看接口", notes = "根据章节ID查看章节信息")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<AuthCourseChapterAuditViewDTO> view(@RequestBody AuthCourseChapterAuditViewBO authCourseChapterAuditViewBO) {
		return biz.view(authCourseChapterAuditViewBO);
	}

	/**
	 * 章节删除接口
	 */
	@ApiOperation(value = "章节删除接口", notes = "章节删除接口")
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public Result<Integer> delete(@RequestBody AuthCourseChapterAuditDeleteBO authCourseChapterAuditDeleteBO) {
		return biz.delete(authCourseChapterAuditDeleteBO);
	}

	/**
	 * 章节列出接口
	 */
	@ApiOperation(value = "章节列出接口", notes = "根据课程ID列出章节信息")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<AuthCourseChapterAuditListDTO> listByCourseNo(@RequestBody AuthCourseChapterAuditBO authCourseChapterAuditBO) {
		return biz.listByCourseNo(authCourseChapterAuditBO);
	}

	/**
	 * 章节添加接口
	 */
	@ApiOperation(value = "章节添加接口", notes = "章节添加接口")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<AuthCourseChapterAuditSaveDTO> save(@RequestBody AuthCourseChapterAuditSaveBO authCourseChapterAuditSaveBO) {
		return biz.save(authCourseChapterAuditSaveBO);
	}

	/**
	 * 章节更新接口
	 */
	@ApiOperation(value = "章节更新接口", notes = "章节更新接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<AuthCourseChapterAuditUpdateDTO> update(@RequestBody AuthCourseChapterAuditUpdateBO authCourseChapterAuditUpdateBO) {
		return biz.update(authCourseChapterAuditUpdateBO);
	}

	/**
	 * 更新章节排序接口
	 */
	@ApiOperation(value = "更新章节排序接口", notes = "更新章节排序接口")
	@RequestMapping(value = "/sort", method = RequestMethod.POST)
	public Result<Integer> sort(@RequestBody AuthCourseChapterAuditSortBO authCourseChapterAuditSortBO) {
		return biz.sort(authCourseChapterAuditSortBO);
	}

}
