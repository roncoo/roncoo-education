package com.roncoo.education.course.service.api;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.course.service.api.biz.ApiCourseCategoryBiz;
import com.roncoo.education.course.service.api.dto.CourseCategoryListDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 课程分类
 *
 * @author wujing
 */
@RestController
@RequestMapping(value = "/course/api/course/category")
public class ApiCourseCategoryController extends BaseController {

    @Autowired
    private ApiCourseCategoryBiz biz;

    /**
     * 普通课程分类列表接口
     *
     * @author wuyun
     */
    @ApiOperation(value = "课程分类列表接口", notes = "课程分类列表")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public Result<CourseCategoryListDTO> list() {
        return biz.list();
    }

}
