package com.roncoo.education.course.service.api;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.course.service.api.biz.ApiCategoryBiz;
import com.roncoo.education.course.service.api.resp.ApiCategoryResp;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 课程分类
 *
 * @author wujing
 */
@Api(tags = "api-分类")
@RestController
@RequestMapping(value = "/course/api/category")
public class ApiCategoryController {

    @Autowired
    private ApiCategoryBiz biz;

    /**
     * 普通课程分类列表接口
     *
     * @author wuyun
     */
    @ApiOperation(value = "课程分类列表接口", notes = "课程分类列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result<List<ApiCategoryResp>> list() {
        return biz.list();
    }

}
