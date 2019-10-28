package com.roncoo.education.course.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.course.service.api.biz.ApiZoneBiz;
import com.roncoo.education.course.common.bo.ZoneBO;
import com.roncoo.education.course.common.dto.ZoneDTO;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 专区课程关联表
 *
 * @author wuyun
 */
@RestController
@RequestMapping(value = "/course/api/zone/course")
public class ApiZoneController extends BaseController {

	@Autowired
	private ApiZoneBiz biz;

	/**
	 * 专区课程分页列表接口
	 *
	 * @param bo
	 * @return
	 */
	@ApiOperation(value = "专区课程列表接口", notes = "分页列出专区课程信息")
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public Result<Page<ZoneDTO>> listForPage(@RequestBody ZoneBO zoneBO) {
		return biz.listForPage(zoneBO);
	}

}
