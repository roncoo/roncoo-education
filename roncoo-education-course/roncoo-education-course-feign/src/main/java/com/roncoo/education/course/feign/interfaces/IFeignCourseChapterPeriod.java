package com.roncoo.education.course.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.interfaces.qo.CourseChapterPeriodQO;
import com.roncoo.education.course.feign.interfaces.vo.CourseChapterPeriodVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 课时信息
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-course-service")
public interface IFeignCourseChapterPeriod {

    @RequestMapping(value = "/feign/course/courseChapterPeriod/listForPage", method = RequestMethod.POST)
    Page<CourseChapterPeriodVO> listForPage(@RequestBody CourseChapterPeriodQO qo);

    @RequestMapping(value = "/feign/course/courseChapterPeriod/save", method = RequestMethod.POST)
    int save(@RequestBody CourseChapterPeriodQO qo);

    @RequestMapping(value = "/feign/course/courseChapterPeriod/delete/{id}", method = RequestMethod.DELETE)
    int deleteById(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/feign/course/courseChapterPeriod/update", method = RequestMethod.PUT)
    int updateById(@RequestBody CourseChapterPeriodQO qo);

    @RequestMapping(value = "/feign/course/courseChapterPeriod/get/{id}", method = RequestMethod.GET)
    CourseChapterPeriodVO getById(@PathVariable(value = "id") Long id);

    /**
     * 根据视频编号查询课时正式表信息
     *
     * @param videoNo
     * @return
     * @author wuyun
     */
    @RequestMapping(value = "/feign/course/courseChapterPeriod/getByVideoNo", method = RequestMethod.GET)
    CourseChapterPeriodVO getByVideoNo(@RequestBody Long videoNo);
}
