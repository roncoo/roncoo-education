package com.roncoo.education.course.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.interfaces.qo.CourseUserStudyQO;
import com.roncoo.education.course.feign.interfaces.vo.CourseUserStudyVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 课程用户关联表
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-course-service")
public interface IFeignCourseUserStudy {

    @RequestMapping(value = "/feign/course/courseUserStudy/listForPage", method = RequestMethod.POST)
    Page<CourseUserStudyVO> listForPage(@RequestBody CourseUserStudyQO qo);

    @RequestMapping(value = "/feign/course/courseUserStudy/save", method = RequestMethod.POST)
    int save(@RequestBody CourseUserStudyQO qo);

    @RequestMapping(value = "/feign/course/courseUserStudy/delete/{id}", method = RequestMethod.DELETE)
    int deleteById(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/feign/course/courseUserStudy/update", method = RequestMethod.PUT)
    int updateById(@RequestBody CourseUserStudyQO qo);

    @RequestMapping(value = "/feign/course/courseUserStudy/get/{id}", method = RequestMethod.GET)
    CourseUserStudyVO getById(@PathVariable(value = "id") Long id);

}
