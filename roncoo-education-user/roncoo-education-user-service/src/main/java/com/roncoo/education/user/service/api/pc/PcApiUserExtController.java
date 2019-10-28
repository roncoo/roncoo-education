package com.roncoo.education.user.service.api.pc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.user.service.api.pc.biz.PcApiUserExtBiz;
import com.roncoo.education.user.common.req.UserExtPageREQ;
import com.roncoo.education.user.common.req.UserExtUpdateREQ;
import com.roncoo.education.user.common.req.UserExtViewREQ;
import com.roncoo.education.user.common.resq.UserExtPageRESQ;
import com.roncoo.education.user.common.resq.UserExtViewRESQ;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 用户教育信息
 *
 */
@RestController
@RequestMapping(value = "/user/pc/user/ext")
public class PcApiUserExtController extends BaseController {

	@Autowired
	private PcApiUserExtBiz biz;

	/**
	 * 用戶教育分页列出接口
	 */
	@ApiOperation(value = "用戶教育分页列出接口", notes = "用戶教育分页列出接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<UserExtPageRESQ>> listForPage(@RequestBody UserExtPageREQ userExtREQ) {
		return biz.listForPage(userExtREQ);
	}

	/**
	 * 用戶教育更新接口
	 */
	@ApiOperation(value = "用戶教育更新接口", notes = "用戶教育更新接口")
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public Result<Integer> update(@RequestBody UserExtUpdateREQ userExtUpdateREQ) {
		return biz.update(userExtUpdateREQ);
	}

	/**
	 * 用戶教育分页列出接口
	 */
	@ApiOperation(value = "查看用户教育信息接口", notes = "查看用户教育信息接口")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<UserExtViewRESQ> view(@RequestBody UserExtViewREQ userExtViewREQ) {
		return biz.view(userExtViewREQ);
	}

}
