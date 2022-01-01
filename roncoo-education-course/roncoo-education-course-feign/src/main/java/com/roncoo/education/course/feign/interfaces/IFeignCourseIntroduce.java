package com.roncoo.education.course.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.interfaces.qo.CourseIntroduceQO;
import com.roncoo.education.course.feign.interfaces.vo.CourseIntroduceVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 课程介绍信息
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-course-service")
public interface IFeignCourseIntroduce {

    @RequestMapping(value = "/feign/course/courseIntroduce/listForPage", method = RequestMethod.POST)
    Page<CourseIntroduceVO> listForPage(@RequestBody CourseIntroduceQO qo);

    @RequestMapping(value = "/feign/course/courseIntroduce/save", method = RequestMethod.POST)
    int save(@RequestBody CourseIntroduceQO qo);

    @RequestMapping(value = "/feign/course/courseIntroduce/delete/{id}", method = RequestMethod.DELETE)
    int deleteById(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/feign/course/courseIntroduce/update", method = RequestMethod.PUT)
    int updateById(@RequestBody CourseIntroduceQO qo);

    @RequestMapping(value = "/feign/course/courseIntroduce/get/{id}", method = RequestMethod.GET)
    CourseIntroduceVO getById(@PathVariable(value = "id") Long id);

}
