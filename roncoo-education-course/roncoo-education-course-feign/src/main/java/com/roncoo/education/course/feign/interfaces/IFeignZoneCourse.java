package com.roncoo.education.course.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.interfaces.qo.ZoneCourseQO;
import com.roncoo.education.course.feign.interfaces.vo.ZoneCourseVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 专区课程关联表
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-course-service")
public interface IFeignZoneCourse {

    @RequestMapping(value = "/feign/course/zoneCourse/listForPage", method = RequestMethod.POST)
    Page<ZoneCourseVO> listForPage(@RequestBody ZoneCourseQO qo);

    @RequestMapping(value = "/feign/course/zoneCourse/save", method = RequestMethod.POST)
    int save(@RequestBody ZoneCourseQO qo);

    @RequestMapping(value = "/feign/course/zoneCourse/delete/{id}", method = RequestMethod.DELETE)
    int deleteById(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/feign/course/zoneCourse/update", method = RequestMethod.PUT)
    int updateById(@RequestBody ZoneCourseQO qo);

    @RequestMapping(value = "/feign/course/zoneCourse/get/{id}", method = RequestMethod.GET)
    ZoneCourseVO getById(@PathVariable(value = "id") Long id);

}
