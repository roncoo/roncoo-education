package com.roncoo.education.course.feign.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.interfaces.qo.CourseChapterPeriodPageQO;
import com.roncoo.education.course.feign.interfaces.qo.CourseChapterPeriodSaveQO;
import com.roncoo.education.course.feign.interfaces.qo.CourseChapterPeriodEditQO;
import com.roncoo.education.course.feign.interfaces.vo.CourseChapterPeriodPageVO;
import com.roncoo.education.course.feign.interfaces.vo.CourseChapterPeriodViewVO;

/**
 * 课时信息 接口
 *
 * @author wujing
 * @date 2022-08-25
 */
@FeignClient(value = "course-service", path = "/course/course/chapter/period")
public interface IFeignCourseChapterPeriod {

    /**
     * 分页列出-课时信息
     *
     * @param qo 课时信息
     * @return 分页结果
     */
    @PostMapping(value = "/page")
    Page<CourseChapterPeriodPageVO> page(@RequestBody CourseChapterPeriodPageQO qo);

    /**
     * 保存-课时信息
     *
     * @param qo 课时信息
     * @return 影响记录数
     */
    @PostMapping(value = "/save")
    int save(@RequestBody CourseChapterPeriodSaveQO qo);

    /**
     * 根据ID删除-课时信息
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    @DeleteMapping(value = "/delete/{id}")
    int deleteById(@PathVariable(value = "id") Long id);

    /**
     * 修改课时信息
     *
     * @param qo 课时信息
     * @return 影响记录数
     */
    @PutMapping(value = "/edit")
    int updateById(@RequestBody CourseChapterPeriodEditQO qo);

    /**
     * 根据ID获取课时信息
     *
     * @param id 主键ID
     * @return 课时信息
     */
    @GetMapping(value = "/get/{id}")
    CourseChapterPeriodViewVO getById(@PathVariable(value = "id") Long id);

}
