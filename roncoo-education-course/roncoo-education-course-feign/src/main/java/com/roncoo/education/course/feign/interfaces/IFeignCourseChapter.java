package com.roncoo.education.course.feign.interfaces;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.interfaces.qo.CourseChapterPageQO;
import com.roncoo.education.course.feign.interfaces.qo.CourseChapterSaveQO;
import com.roncoo.education.course.feign.interfaces.qo.CourseChapterEditQO;
import com.roncoo.education.course.feign.interfaces.vo.CourseChapterPageVO;
import com.roncoo.education.course.feign.interfaces.vo.CourseChapterViewVO;

/**
 * 章节信息 接口
 *
 * @author wujing
 * @date 2022-08-27
 */
@FeignClient(value = "course-service", path = "/course/course/chapter")
public interface IFeignCourseChapter {

    /**
     * 分页列出-章节信息
     *
     * @param qo 章节信息
     * @return 分页结果
     */
    @PostMapping(value = "/page")
    Page<CourseChapterPageVO> page(@RequestBody CourseChapterPageQO qo);

    /**
     * 保存-章节信息
     *
     * @param qo 章节信息
     * @return 影响记录数
     */
    @PostMapping(value = "/save")
    int save(@RequestBody CourseChapterSaveQO qo);

    /**
     * 根据ID删除-章节信息
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    @DeleteMapping(value = "/delete/{id}")
    int deleteById(@PathVariable(value = "id") Long id);

    /**
     * 修改章节信息
     *
     * @param qo 章节信息
     * @return 影响记录数
     */
    @PutMapping(value = "/edit")
    int updateById(@RequestBody CourseChapterEditQO qo);

    /**
     * 根据ID获取章节信息
     *
     * @param id 主键ID
     * @return 章节信息
     */
    @GetMapping(value = "/get/{id}")
    CourseChapterViewVO getById(@PathVariable(value = "id") Long id);

}
