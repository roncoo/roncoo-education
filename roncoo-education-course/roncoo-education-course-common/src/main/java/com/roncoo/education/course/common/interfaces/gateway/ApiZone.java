package com.roncoo.education.course.common.interfaces.gateway;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.course.common.bean.bo.ZoneBO;
import com.roncoo.education.course.common.bean.dto.ZoneDTO;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 课程专区信息
 *
 * @author wuyun
 */
public interface ApiZone {

	/**
	 * 专区课程分页列表接口
	 * 
	 * @param bo
	 * @return
	 */
	@ApiOperation(value = "专区课程列表接口", notes = "分页列出专区课程信息")
	@RequestMapping(value = "/course/api/zone/course/list", method = RequestMethod.POST)
	Result<Page<ZoneDTO>> listForPage(@RequestBody ZoneBO bo);

}
