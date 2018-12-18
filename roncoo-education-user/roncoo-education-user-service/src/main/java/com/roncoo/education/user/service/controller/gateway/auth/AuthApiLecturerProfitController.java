package com.roncoo.education.user.service.controller.gateway.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.user.common.bean.bo.auth.AuthLecturerProfitPageBO;
import com.roncoo.education.user.common.bean.bo.auth.AuthLecturerProfitSaveBO;
import com.roncoo.education.user.common.bean.dto.auth.AuthLecturerProfitPageDTO;
import com.roncoo.education.user.common.interfaces.gateway.auth.AuthApiLecturerProfit;
import com.roncoo.education.user.service.biz.gateway.auth.AuthApiLecturerProfitBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;

/**
 * 讲师提现日志表
 *
 * @author wuyun
 */
@RestController
public class AuthApiLecturerProfitController extends BaseController implements AuthApiLecturerProfit {

	@Autowired
	private AuthApiLecturerProfitBiz biz;

	@Override
	public Result<Page<AuthLecturerProfitPageDTO>> list(@RequestBody AuthLecturerProfitPageBO authLecturerProfitPageBO) {
		return biz.list(authLecturerProfitPageBO);
	}

	@Override
	public Result<Integer> save(@RequestBody AuthLecturerProfitSaveBO authLecturerProfitSaveBO) {
		return biz.save(authLecturerProfitSaveBO);
	}

}
