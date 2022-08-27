package com.roncoo.education.course.feign.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.interfaces.qo.UserCoursePageQO;
import com.roncoo.education.course.feign.interfaces.qo.UserCourseSaveQO;
import com.roncoo.education.course.feign.interfaces.qo.UserCourseEditQO;
import com.roncoo.education.course.feign.interfaces.vo.UserCoursePageVO;
import com.roncoo.education.course.feign.interfaces.vo.UserCourseViewVO;

/**
 * 课程用户关联表 接口
 *
 * @author wujing
 * @date 2022-08-27
 */
@FeignClient(value = "course-service", path = "/course/user/course")
public interface IFeignUserCourse {

    /**
     * 分页列出-课程用户关联表
     *
     * @param qo 课程用户关联表
     * @return 分页结果
     */
    @PostMapping(value = "/page")
    Page<UserCoursePageVO> page(@RequestBody UserCoursePageQO qo);

    /**
     * 保存-课程用户关联表
     *
     * @param qo 课程用户关联表
     * @return 影响记录数
     */
    @PostMapping(value = "/save")
    int save(@RequestBody UserCourseSaveQO qo);

    /**
     * 根据ID删除-课程用户关联表
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    @DeleteMapping(value = "/delete/{id}")
    int deleteById(@PathVariable(value = "id") Long id);

    /**
     * 修改课程用户关联表
     *
     * @param qo 课程用户关联表
     * @return 影响记录数
     */
    @PutMapping(value = "/edit")
    int updateById(@RequestBody UserCourseEditQO qo);

    /**
     * 根据ID获取课程用户关联表
     *
     * @param id 主键ID
     * @return 课程用户关联表
     */
    @GetMapping(value = "/get/{id}")
    UserCourseViewVO getById(@PathVariable(value = "id") Long id);

}
