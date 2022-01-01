package com.roncoo.education.course.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.interfaces.qo.CourseChapterPeriodAuditQO;
import com.roncoo.education.course.feign.interfaces.vo.CourseChapterPeriodAuditVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 课时信息-审核
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-course-service")
public interface IFeignCourseChapterPeriodAudit {

    @RequestMapping(value = "/feign/course/courseChapterPeriodAudit/listForPage", method = RequestMethod.POST)
    Page<CourseChapterPeriodAuditVO> listForPage(@RequestBody CourseChapterPeriodAuditQO qo);

    @RequestMapping(value = "/feign/course/courseChapterPeriodAudit/save", method = RequestMethod.POST)
    int save(@RequestBody CourseChapterPeriodAuditQO qo);

    @RequestMapping(value = "/feign/course/courseChapterPeriodAudit/delete/{id}", method = RequestMethod.DELETE)
    int deleteById(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/feign/course/courseChapterPeriodAudit/update", method = RequestMethod.PUT)
    int updateById(@RequestBody CourseChapterPeriodAuditQO qo);

    @RequestMapping(value = "/feign/course/courseChapterPeriodAudit/get/{id}", method = RequestMethod.GET)
    CourseChapterPeriodAuditVO getById(@PathVariable(value = "id") Long id);

    /**
     * 根据视频编号查询课时信息
     *
     * @param videoNo
     * @return
     * @author wuyun
     */
    @RequestMapping(value = "/feign/course/periodVideoInfoAudit/getByVideoNo", method = RequestMethod.GET)
    CourseChapterPeriodAuditVO getByVideoNo(@RequestBody Long videoNo);
}
