package com.roncoo.education.user.service.api.pc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.user.service.api.pc.biz.PcApiLecturerBiz;
import com.roncoo.education.user.common.req.LecturerPageREQ;
import com.roncoo.education.user.common.req.LecturerUpdateREQ;
import com.roncoo.education.user.common.req.LecturerViewREQ;
import com.roncoo.education.user.common.resq.LecturerPageRESQ;
import com.roncoo.education.user.common.resq.LecturerViewRESQ;
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
@RequestMapping(value = "/user/pc/lecturer")
public class PcApiLecturerController extends BaseController {

	@Autowired
	private PcApiLecturerBiz biz;

	/**
	 * 讲师分页列表接口
	 *
	 * @param lecturerViewPageREQ
	 */
	@ApiOperation(value = "讲师分页列表接口", notes = "讲师分页列表接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<LecturerPageRESQ>> listForPage(@RequestBody LecturerPageREQ lecturerPageREQ) {
		return biz.listForPage(lecturerPageREQ);
	}

	/**
	 * 讲师信息查看接口
	 *
	 * @param lecturerViewREQ
	 */
	@ApiOperation(value = "讲师查看接口", notes = "根据讲师用户编号查看讲师信息")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<LecturerViewRESQ> view(@RequestBody LecturerViewREQ lecturerViewREQ) {
		return biz.view(lecturerViewREQ);
	}

	/**
	 * 更新讲师信息(更新直接生效)接口
	 *
	 * @param lecturerViewUpdateREQ
	 */
	@ApiOperation(value = "更新讲师信息(更新直接生效)接口", notes = "更新讲师信息(更新直接生效)接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody LecturerUpdateREQ lecturerViewUpdateREQ) {
		return biz.update(lecturerViewUpdateREQ);
	}

}
