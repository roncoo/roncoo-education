package com.roncoo.education.course.feign.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.interfaces.qo.CategoryPageQO;
import com.roncoo.education.course.feign.interfaces.qo.CategorySaveQO;
import com.roncoo.education.course.feign.interfaces.qo.CategoryEditQO;
import com.roncoo.education.course.feign.interfaces.vo.CategoryPageVO;
import com.roncoo.education.course.feign.interfaces.vo.CategoryViewVO;

/**
 * 分类 接口
 *
 * @author wujing
 * @date 2022-08-27
 */
@FeignClient(value = "course-service", path = "/course/category")
public interface IFeignCategory {

    /**
     * 分页列出-分类
     *
     * @param qo 分类
     * @return 分页结果
     */
    @PostMapping(value = "/page")
    Page<CategoryPageVO> page(@RequestBody CategoryPageQO qo);

    /**
     * 保存-分类
     *
     * @param qo 分类
     * @return 影响记录数
     */
    @PostMapping(value = "/save")
    int save(@RequestBody CategorySaveQO qo);

    /**
     * 根据ID删除-分类
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    @DeleteMapping(value = "/delete/{id}")
    int deleteById(@PathVariable(value = "id") Long id);

    /**
     * 修改分类
     *
     * @param qo 分类
     * @return 影响记录数
     */
    @PutMapping(value = "/edit")
    int updateById(@RequestBody CategoryEditQO qo);

    /**
     * 根据ID获取分类
     *
     * @param id 主键ID
     * @return 分类
     */
    @GetMapping(value = "/get/{id}")
    CategoryViewVO getById(@PathVariable(value = "id") Long id);

}
