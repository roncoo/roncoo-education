package com.roncoo.education.course.service.controller.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.course.common.bean.dto.CourseCategoryListDTO;
import com.roncoo.education.course.common.interfaces.gateway.ApiCourseCategory;
import com.roncoo.education.course.service.biz.gateway.ApiCourseCategoryBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;

/**
 * 课程分类
 *
 * @author wujing
 */
@RestController
public class ApiCourseCategoryController extends BaseController implements ApiCourseCategory {

	@Autowired
	private ApiCourseCategoryBiz biz;

	@Override
	public Result<CourseCategoryListDTO> list() {
		return biz.list();
	}

}
