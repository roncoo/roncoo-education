package com.roncoo.education.course.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.interfaces.qo.CourseAuditQO;
import com.roncoo.education.course.feign.interfaces.vo.CourseAuditVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 课程信息-审核
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-course-service")
public interface IFeignCourseAudit {

    @RequestMapping(value = "/feign/course/courseAudit/listForPage", method = RequestMethod.POST)
    Page<CourseAuditVO> listForPage(@RequestBody CourseAuditQO qo);

    @RequestMapping(value = "/feign/course/courseAudit/save", method = RequestMethod.POST)
    int save(@RequestBody CourseAuditQO qo);

    @RequestMapping(value = "/feign/course/courseAudit/delete/{id}", method = RequestMethod.DELETE)
    int deleteById(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/feign/course/courseAudit/update", method = RequestMethod.PUT)
    int updateById(@RequestBody CourseAuditQO qo);

    @RequestMapping(value = "/feign/course/courseAudit/get/{id}", method = RequestMethod.GET)
    CourseAuditVO getById(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/feign/course/courseAudit/audit", method = RequestMethod.PUT)
    int audit(@RequestBody CourseAuditQO qo);

    /**
     * 更新状态信息
     *
     * @param qo
     * @return
     * @author wuyun
     */
    @RequestMapping(value = "/feign/course/courseAudit/updateStatusId", method = RequestMethod.PUT)
    int updateStatusId(CourseAuditQO qo);
}
