package com.roncoo.education.course.common.interfaces.gateway;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.course.service.common.dto.CourseCategoryListDTO;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 课程分类
 *
 * @author wujing
 */
public interface ApiCourseCategory {

	/**
	 * 普通课程分类列表接口
	 * 
	 * @author wuyun
	 */
	@ApiOperation(value = "课程分类列表接口", notes = "课程分类列表")
	@RequestMapping(value = "/course/api/course/category/list", method = RequestMethod.POST)
	Result<CourseCategoryListDTO> list();

}
