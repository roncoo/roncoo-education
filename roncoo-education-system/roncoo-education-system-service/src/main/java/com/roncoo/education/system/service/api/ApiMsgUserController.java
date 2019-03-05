package com.roncoo.education.system.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.system.service.biz.ApiMsgUserBiz;
import com.roncoo.education.util.base.BaseController;


/**
 * 站内信用户记录表
 *
 * @author LHR
 */
@RestController
public class ApiMsgUserController extends BaseController{

	@Autowired
	private ApiMsgUserBiz biz;

}
