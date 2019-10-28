package com.roncoo.education.user.service.api.pc;

import com.roncoo.education.user.common.req.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.user.service.api.pc.biz.PcApiLecturerAuditBiz;
import com.roncoo.education.user.common.resq.LecturerAuditPageRESQ;
import com.roncoo.education.user.common.resq.LecturerAuditViewRESQ;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 讲师信息-审核
 *
 * @author wujing
 */
@RestController
@RequestMapping(value = "/user/pc/lecturer/audit")
public class PcApiLecturerAuditController extends BaseController {

	@Autowired
	private PcApiLecturerAuditBiz biz;

	/**
	 * 讲师信息-审核分页列表接口
	 *
	 * @param lecturerAuditPageREQ
	 */
	@ApiOperation(value = "讲师信息-审核分页列表接口", notes = "讲师信息-审核分页列表接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<LecturerAuditPageRESQ>> listForPage(@RequestBody LecturerAuditPageREQ lecturerAuditPageREQ) {
		return biz.listForPage(lecturerAuditPageREQ);
	}

	/**
	 * 讲师信息-审核添加接口
	 *
	 * @param lecturerAuditSaveREQ
	 */
	@ApiOperation(value = "讲师信息-审核添加接口", notes = "讲师信息-审核添加接口")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<Integer> save(@RequestBody LecturerAuditSaveREQ lecturerAuditSaveREQ) {
		return biz.save(lecturerAuditSaveREQ);
	}

	/**
	 * 讲师信息-审核更新接口
	 *
	 * @param lecturerAuditUpdateREQ
	 */
	@ApiOperation(value = "讲师信息-审核更新接口", notes = "讲师信息-审核更新接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody LecturerAuditUpdateREQ lecturerAuditUpdateREQ) {
		return biz.update(lecturerAuditUpdateREQ);
	}

	/**
	 * 讲师信息-审核接口
	 *
	 * @param lecturerAuditAuditREQ
	 */
	@ApiOperation(value = "讲师信息-审核接口", notes = "讲师信息-审核接口")
	@RequestMapping(value = "/audit", method = RequestMethod.POST)
	public Result<Integer> audit(@RequestBody LecturerAuditAuditREQ lecturerAuditAuditREQ) {
		return biz.audit(lecturerAuditAuditREQ);
	}

	/**
	 * 讲师信息-审核查看接口
	 *
	 * @param lecturerAuditViewREQ
	 */
	@ApiOperation(value = "讲师信息-审核查看接口", notes = "讲师信息-审核查看接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<LecturerAuditViewRESQ> view(@RequestBody LecturerAuditViewREQ lecturerAuditViewREQ) {
		return biz.view(lecturerAuditViewREQ);
	}

	/**
	 * 讲师信息-审核校验手机号接口
	 *
	 * @param lecturerAuditCheckMobileREQ
	 */
	@ApiOperation(value = "讲师信息-审核校验手机号接口", notes = "讲师信息-审核校验手机号接口")
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public Result<Integer> check(@RequestBody LecturerAuditCheckMobileREQ lecturerAuditCheckMobileREQ) {
		return biz.check(lecturerAuditCheckMobileREQ);
	}

}
