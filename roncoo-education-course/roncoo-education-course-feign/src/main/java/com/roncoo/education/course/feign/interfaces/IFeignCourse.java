package com.roncoo.education.course.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.interfaces.qo.CourseQO;
import com.roncoo.education.course.feign.interfaces.vo.CourseVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 课程信息
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-course-service")
public interface IFeignCourse {

    @RequestMapping(value = "/feign/course/course/listForPage", method = RequestMethod.POST)
    Page<CourseVO> listForPage(@RequestBody CourseQO qo);

    @RequestMapping(value = "/feign/course/course/save", method = RequestMethod.POST)
    int save(@RequestBody CourseQO qo);

    @RequestMapping(value = "/feign/course/course/delete/{id}", method = RequestMethod.DELETE)
    int deleteById(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/feign/course/course/update", method = RequestMethod.PUT)
    int updateById(@RequestBody CourseQO qo);

    @RequestMapping(value = "/feign/course/course/get/{id}", method = RequestMethod.GET)
    CourseVO getById(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/feign/course/course/getByCourseId/{id}", method = RequestMethod.GET)
    CourseVO getByCourseId(@PathVariable(value = "id") Long id);

}
