package com.roncoo.education.course.service.controller.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.course.common.bean.bo.AdvBO;
import com.roncoo.education.course.common.bean.dto.AdvListDTO;
import com.roncoo.education.course.common.interfaces.gateway.ApiAdv;
import com.roncoo.education.course.service.biz.gateway.ApiAdvBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;

/**
 * 广告信息
 *
 * @author wujing
 */
@RestController
public class ApiAdvController extends BaseController implements ApiAdv {

	@Autowired
	private ApiAdvBiz biz;

	@Override
	public Result<AdvListDTO> list(@RequestBody AdvBO advBO) {
		return biz.list(advBO);
	}

}
