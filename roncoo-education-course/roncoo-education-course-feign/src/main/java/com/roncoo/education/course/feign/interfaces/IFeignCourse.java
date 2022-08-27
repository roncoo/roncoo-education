package com.roncoo.education.course.feign.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.interfaces.qo.CoursePageQO;
import com.roncoo.education.course.feign.interfaces.qo.CourseSaveQO;
import com.roncoo.education.course.feign.interfaces.qo.CourseEditQO;
import com.roncoo.education.course.feign.interfaces.vo.CoursePageVO;
import com.roncoo.education.course.feign.interfaces.vo.CourseViewVO;

/**
 * 课程信息 接口
 *
 * @author wujing
 * @date 2022-08-27
 */
@FeignClient(value = "course-service", path = "/course/course")
public interface IFeignCourse {

    /**
     * 分页列出-课程信息
     *
     * @param qo 课程信息
     * @return 分页结果
     */
    @PostMapping(value = "/page")
    Page<CoursePageVO> page(@RequestBody CoursePageQO qo);

    /**
     * 保存-课程信息
     *
     * @param qo 课程信息
     * @return 影响记录数
     */
    @PostMapping(value = "/save")
    int save(@RequestBody CourseSaveQO qo);

    /**
     * 根据ID删除-课程信息
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    @DeleteMapping(value = "/delete/{id}")
    int deleteById(@PathVariable(value = "id") Long id);

    /**
     * 修改课程信息
     *
     * @param qo 课程信息
     * @return 影响记录数
     */
    @PutMapping(value = "/edit")
    int updateById(@RequestBody CourseEditQO qo);

    /**
     * 根据ID获取课程信息
     *
     * @param id 主键ID
     * @return 课程信息
     */
    @GetMapping(value = "/get/{id}")
    CourseViewVO getById(@PathVariable(value = "id") Long id);

}
