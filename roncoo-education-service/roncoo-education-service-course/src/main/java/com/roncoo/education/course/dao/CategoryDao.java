package com.roncoo.education.course.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.dao.impl.mapper.entity.Category;
import com.roncoo.education.course.dao.impl.mapper.entity.CategoryExample;

import java.util.List;

/**
 * 分类 服务类
 *
 * @author wujing
 * @date 2022-08-25
 */
public interface CategoryDao {

    /**
     * 保存分类
     *
     * @param record 分类
     * @return 影响记录数
     */
    int save(Category record);

    /**
     * 根据ID删除分类
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    int deleteById(Long id);

    /**
     * 修改分类
     *
     * @param record 分类
     * @return 影响记录数
     */
    int updateById(Category record);

    /**
     * 根据ID获取分类
     *
     * @param id 主键ID
     * @return 分类
     */
    Category getById(Long id);

    /**
     * 分类--分页查询
     *
     * @param pageCurrent 当前页
     * @param pageSize    分页大小
     * @param example     查询条件
     * @return 分页结果
     */
    Page<Category> page(int pageCurrent, int pageSize, CategoryExample example);

    /**
     * 分类--条件列出
     *
     * @param example 查询条件
     * @return 分类列表
     */
    List<Category> listByExample(CategoryExample example);

    /**
     * 分类--条件统计
     *
     * @param example 统计条件
     * @return 分类数量
     */
    int countByExample(CategoryExample example);

    List<Category> listByIds(List<Long> categoryIdList);
}
