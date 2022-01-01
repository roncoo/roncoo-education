package com.roncoo.education.course.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.interfaces.qo.CourseCategoryQO;
import com.roncoo.education.course.feign.interfaces.vo.CourseCategoryVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * 课程分类
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-course-service")
public interface IFeignCourseCategory {

    @RequestMapping(value = "/feign/course/courseCategory/listForPage", method = RequestMethod.POST)
    Page<CourseCategoryVO> listForPage(@RequestBody CourseCategoryQO qo);

    @RequestMapping(value = "/feign/course/courseCategory/save", method = RequestMethod.POST)
    int save(@RequestBody CourseCategoryQO qo);

    @RequestMapping(value = "/feign/course/courseCategory/delete/{id}", method = RequestMethod.DELETE)
    int deleteById(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/feign/course/courseCategory/update", method = RequestMethod.PUT)
    int updateById(@RequestBody CourseCategoryQO qo);

    @RequestMapping(value = "/feign/course/courseCategory/get/{id}", method = RequestMethod.GET)
    CourseCategoryVO getById(@PathVariable(value = "id") Long id);

    /**
     * 根据ID列出分类信息
     *
     * @author WY
     */
    @RequestMapping(value = "/feign/course/courseCategory/listByParentId/{parentId}", method = RequestMethod.GET)
    List<CourseCategoryVO> listByParentId(@PathVariable(value = "parentId") Long parentId);

    /**
     * 根据层级列出分类信息
     *
     * @author WY
     */
    @RequestMapping(value = "/feign/course/courseCategory/listByFloor/{floor}", method = RequestMethod.GET)
    List<CourseCategoryVO> listByFloor(@PathVariable(value = "floor") Integer floor);

    /**
     * 根据层级、父分类ID列出分类信息
     *
     * @author WY
     */
    @RequestMapping(value = "/feign/course/courseCategory/listByFloorAndCategoryId", method = RequestMethod.POST)
    List<CourseCategoryVO> listByFloorAndCategoryId(@RequestBody CourseCategoryQO qo);
}
