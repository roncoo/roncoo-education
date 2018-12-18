package com.roncoo.education.user.service.controller.gateway.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.user.common.interfaces.gateway.auth.AuthApiLecturer;
import com.roncoo.education.user.service.biz.gateway.auth.AuthApiLecturerBiz;
import com.roncoo.education.util.base.BaseController;

/**
 * 讲师信息-审核
 *
 * @author wujing
 */
@RestController
public class AuthApiLecturerController extends BaseController implements AuthApiLecturer {

	@Autowired
	private AuthApiLecturerBiz biz;

}
