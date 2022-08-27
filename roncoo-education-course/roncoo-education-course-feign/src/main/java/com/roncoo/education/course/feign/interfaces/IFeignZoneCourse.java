package com.roncoo.education.course.feign.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.interfaces.qo.ZoneCoursePageQO;
import com.roncoo.education.course.feign.interfaces.qo.ZoneCourseSaveQO;
import com.roncoo.education.course.feign.interfaces.qo.ZoneCourseEditQO;
import com.roncoo.education.course.feign.interfaces.vo.ZoneCoursePageVO;
import com.roncoo.education.course.feign.interfaces.vo.ZoneCourseViewVO;

/**
 * 专区课程关联表 接口
 *
 * @author wujing
 * @date 2022-08-27
 */
@FeignClient(value = "course-service", path = "/course/zone/course")
public interface IFeignZoneCourse {

    /**
     * 分页列出-专区课程关联表
     *
     * @param qo 专区课程关联表
     * @return 分页结果
     */
    @PostMapping(value = "/page")
    Page<ZoneCoursePageVO> page(@RequestBody ZoneCoursePageQO qo);

    /**
     * 保存-专区课程关联表
     *
     * @param qo 专区课程关联表
     * @return 影响记录数
     */
    @PostMapping(value = "/save")
    int save(@RequestBody ZoneCourseSaveQO qo);

    /**
     * 根据ID删除-专区课程关联表
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    @DeleteMapping(value = "/delete/{id}")
    int deleteById(@PathVariable(value = "id") Long id);

    /**
     * 修改专区课程关联表
     *
     * @param qo 专区课程关联表
     * @return 影响记录数
     */
    @PutMapping(value = "/edit")
    int updateById(@RequestBody ZoneCourseEditQO qo);

    /**
     * 根据ID获取专区课程关联表
     *
     * @param id 主键ID
     * @return 专区课程关联表
     */
    @GetMapping(value = "/get/{id}")
    ZoneCourseViewVO getById(@PathVariable(value = "id") Long id);

}
