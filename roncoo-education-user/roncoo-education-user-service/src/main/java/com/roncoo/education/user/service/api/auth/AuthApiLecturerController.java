package com.roncoo.education.user.service.api.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.user.service.api.auth.biz.AuthApiLecturerBiz;
import com.roncoo.education.user.common.bo.LecturerViewBO;
import com.roncoo.education.user.common.dto.LecturerViewDTO;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 讲师信息-审核
 *
 * @author wujing
 */
@RestController
@RequestMapping(value = "/user/auth/lecturer")
public class AuthApiLecturerController extends BaseController {

	@Autowired
	private AuthApiLecturerBiz biz;

	/**
	 * 讲师信息查看接口
	 *
	 * @param lecturerUserNo
	 * @author wuyun
	 */
	@ApiOperation(value = "讲师查看接口", notes = "根据讲师用户编号查看讲师信息")
	@RequestMapping(value = "/view", method = RequestMethod.POST)
	public Result<LecturerViewDTO> view(@RequestBody LecturerViewBO lecturerViewBO) {
		return biz.view(lecturerViewBO);
	}

}
