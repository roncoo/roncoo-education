package com.roncoo.education.user.service.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.user.service.api.auth.biz.AuthApiLecturerProfitBiz;
import com.roncoo.education.user.common.bo.auth.AuthLecturerProfitPageBO;
import com.roncoo.education.user.common.bo.auth.AuthLecturerProfitSaveBO;
import com.roncoo.education.user.common.dto.auth.AuthLecturerProfitPageDTO;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 讲师提现日志表
 *
 * @author wuyun
 */
@RestController
@RequestMapping(value = "/user/auth/lecturer/profit")
public class AuthApiLecturerProfitController extends BaseController {

	@Autowired
	private AuthApiLecturerProfitBiz biz;

	/**
	 * 讲师提现记录分页列出接口
	 *
	 * @param authLecturerProfitPageBO
	 * @author wuyun
	 */
	@ApiOperation(value = "讲师提现记录分页列出", notes = "讲师提现记录分页列出接口")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<AuthLecturerProfitPageDTO>> list(@RequestBody AuthLecturerProfitPageBO authLecturerProfitPageBO) {
		return biz.list(authLecturerProfitPageBO);
	}

	/**
	 * 讲师申请提现接口
	 *
	 * @param authLecturerProfitSaveBO
	 * @author wuyun
	 */
	@ApiOperation(value = "讲师申请提现", notes = "讲师申请提现接口")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public Result<Integer> save(@RequestBody AuthLecturerProfitSaveBO authLecturerProfitSaveBO) {
		return biz.save(authLecturerProfitSaveBO);
	}

}
