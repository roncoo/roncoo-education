package com.roncoo.education.course.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.interfaces.qo.CourseIntroduceAuditQO;
import com.roncoo.education.course.feign.interfaces.vo.CourseIntroduceAuditVO;
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
public interface IFeignCourseIntroduceAudit {

    @RequestMapping(value = "/feign/course/courseIntroduceAudit/listForPage", method = RequestMethod.POST)
    Page<CourseIntroduceAuditVO> listForPage(@RequestBody CourseIntroduceAuditQO qo);

    @RequestMapping(value = "/feign/course/courseIntroduceAudit/save", method = RequestMethod.POST)
    int save(@RequestBody CourseIntroduceAuditQO qo);

    @RequestMapping(value = "/feign/course/courseIntroduceAudit/delete/{id}", method = RequestMethod.DELETE)
    int deleteById(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/feign/course/courseIntroduceAudit/update", method = RequestMethod.PUT)
    int updateById(@RequestBody CourseIntroduceAuditQO qo);

    @RequestMapping(value = "/feign/course/courseIntroduceAudit/get/{id}", method = RequestMethod.GET)
    CourseIntroduceAuditVO getById(@PathVariable(value = "id") Long id);

}
