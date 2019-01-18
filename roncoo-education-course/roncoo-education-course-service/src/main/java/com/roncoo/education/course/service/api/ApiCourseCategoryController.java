package com.roncoo.education.course.service.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.course.common.interfaces.gateway.ApiCourseCategory;
import com.roncoo.education.course.service.biz.ApiCourseCategoryBiz;
import com.roncoo.education.course.service.common.dto.CourseCategoryListDTO;
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
