package com.roncoo.education.user.service.controller.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.user.common.bean.bo.LecturerViewBO;
import com.roncoo.education.user.common.bean.dto.LecturerViewDTO;
import com.roncoo.education.user.common.interfaces.gateway.ApiLecturer;
import com.roncoo.education.user.service.biz.gateway.ApiLecturerBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;

/**
 * 讲师信息
 *
 * @author wujing
 */
@RestController
public class ApiLecturerController extends BaseController implements ApiLecturer {

	@Autowired
	private ApiLecturerBiz biz;

	@Override
	public Result<LecturerViewDTO> view(@RequestBody LecturerViewBO lecturerViewBO) {
		return biz.view(lecturerViewBO);
	}

}
