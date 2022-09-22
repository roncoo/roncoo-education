package com.roncoo.education.course.service.api.biz;

import cn.hutool.core.collection.CollUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.course.dao.CategoryDao;
import com.roncoo.education.course.dao.impl.mapper.entity.Category;
import com.roncoo.education.course.dao.impl.mapper.entity.CategoryExample;
import com.roncoo.education.course.service.api.resp.ApiCategoryResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 课程分类
 *
 * @author wujing
 */
@Component
@CacheConfig(cacheNames = {"course"})
public class ApiCategoryBiz {

    @Autowired
    private CategoryDao dao;

    /**
     * 获取课程分类列表
     *
     * @return
     * @author wuyun
     */
    @Cacheable
    public Result<List<ApiCategoryResp>> list() {
        // 根据分类类型、层级查询可用状态的课程分类集合
        CategoryExample example = new CategoryExample();
        example.createCriteria().andStatusIdEqualTo(StatusIdEnum.YES.getCode());
        example.setOrderByClause(" sort asc, id desc ");
        List<Category> categories = dao.listByExample(example);
        return Result.success(filter(categories, Long.valueOf(0)));
    }

    private List<ApiCategoryResp> filter(List<Category> categories, Long parentId) {
        List<Category> list = categories.stream().filter(item -> item.getParentId().compareTo(parentId) == 0).collect(Collectors.toList());
        if (CollUtil.isNotEmpty(list)) {
            List<ApiCategoryResp> resps = BeanUtil.copyProperties(list, ApiCategoryResp.class);
            for (ApiCategoryResp resp : resps) {
                resp.setList(filter(categories, resp.getId()));
            }
            return resps;
        }
        return new ArrayList<>();
    }

}
