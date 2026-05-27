package com.roncoo.education.course.service.api;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.course.service.api.biz.ApiCategoryBiz;
import com.roncoo.education.course.service.api.resp.ApiCategoryResp;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * 课程分类
 *
 * @author wujing
 */
@Tag(name = "api-分类")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/course/api/category")
public class ApiCategoryController {

    @NotNull
    private final ApiCategoryBiz biz;

    /**
     * 普通课程分类列表接口
     *
     * @author wuyun
     */
    @Operation(summary = "课程分类列表接口", description = "课程分类列表")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public Result<List<ApiCategoryResp>> list() {
        return biz.list();
    }

}
