package com.roncoo.education.course.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.interfaces.qo.CourseChapterQO;
import com.roncoo.education.course.feign.interfaces.vo.CourseChapterVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 章节信息
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-course-service")
public interface IFeignCourseChapter {

    @RequestMapping(value = "/feign/course/courseChapter/listForPage", method = RequestMethod.POST)
    Page<CourseChapterVO> listForPage(@RequestBody CourseChapterQO qo);

    @RequestMapping(value = "/feign/course/courseChapter/save", method = RequestMethod.POST)
    int save(@RequestBody CourseChapterQO qo);

    @RequestMapping(value = "/feign/course/courseChapter/delete/{id}", method = RequestMethod.DELETE)
    int deleteById(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/feign/course/courseChapter/update", method = RequestMethod.PUT)
    int updateById(@RequestBody CourseChapterQO qo);

    @RequestMapping(value = "/feign/course/courseChapter/get/{id}", method = RequestMethod.GET)
    CourseChapterVO getById(@PathVariable(value = "id") Long id);

}
