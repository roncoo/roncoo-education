package com.roncoo.education.course.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.course.service.api.biz.ApiAdvBiz;
import com.roncoo.education.course.common.bo.AdvBO;
import com.roncoo.education.course.common.dto.AdvListDTO;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 广告信息
 *
 * @author wujing
 */
@RestController
@RequestMapping(value = "/course/api/adv")
public class ApiAdvController extends BaseController {

	@Autowired
	private ApiAdvBiz biz;

	@ApiOperation(value = "广告列表接口", notes = "首页轮播广告列出")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<AdvListDTO> list(@RequestBody AdvBO advBO) {
		return biz.list(advBO);
	}

}
