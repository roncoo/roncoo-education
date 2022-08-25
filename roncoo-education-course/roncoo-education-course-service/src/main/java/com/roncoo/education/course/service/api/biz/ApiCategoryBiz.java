package com.roncoo.education.course.service.api.biz;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.course.dao.CategoryDao;
import com.roncoo.education.course.service.api.resp.ApiCategoryResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 课程分类
 *
 * @author wujing
 */
@Component
public class ApiCategoryBiz {

    @Autowired
    private CategoryDao dao;

    /**
     * 获取课程分类列表
     *
     * @return
     * @author wuyun
     */
    public Result<List<ApiCategoryResp>> list() {
        // 根据分类类型、层级查询可用状态的课程分类集合

        return Result.success(null);
    }

}
