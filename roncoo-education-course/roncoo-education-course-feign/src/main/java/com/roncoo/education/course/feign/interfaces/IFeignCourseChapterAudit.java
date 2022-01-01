package com.roncoo.education.course.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.interfaces.qo.CourseChapterAuditQO;
import com.roncoo.education.course.feign.interfaces.vo.CourseChapterAuditVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 章节信息-审核
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-course-service")
public interface IFeignCourseChapterAudit {

    @RequestMapping(value = "/feign/course/courseChapterAudit/listForPage", method = RequestMethod.POST)
    Page<CourseChapterAuditVO> listForPage(@RequestBody CourseChapterAuditQO qo);

    @RequestMapping(value = "/feign/course/courseChapterAudit/save", method = RequestMethod.POST)
    int save(@RequestBody CourseChapterAuditQO qo);

    @RequestMapping(value = "/feign/course/courseChapterAudit/delete/{id}", method = RequestMethod.DELETE)
    int deleteById(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/feign/course/courseChapterAudit/update", method = RequestMethod.PUT)
    int updateById(@RequestBody CourseChapterAuditQO qo);

    @RequestMapping(value = "/feign/course/courseChapterAudit/get/{id}", method = RequestMethod.GET)
    CourseChapterAuditVO getById(@PathVariable(value = "id") Long id);

}
