package com.roncoo.education.course.feign.interfaces;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.interfaces.qo.CourseRecommendQO;
import com.roncoo.education.course.feign.interfaces.vo.CourseRecommendVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * 课程推荐
 *
 * @author wujing
 */
@FeignClient(value = "roncoo-education-course-service")
public interface IFeignCourseRecommend {

    @RequestMapping(value = "/feign/course/courseRecommend/listForPage", method = RequestMethod.POST)
    Page<CourseRecommendVO> listForPage(@RequestBody CourseRecommendQO qo);

    @RequestMapping(value = "/feign/course/courseRecommend/save", method = RequestMethod.POST)
    int save(@RequestBody CourseRecommendQO qo);

    @RequestMapping(value = "/feign/course/courseRecommend/delete/{id}", method = RequestMethod.DELETE)
    int deleteById(@PathVariable(value = "id") Long id);

    @RequestMapping(value = "/feign/course/courseRecommend/update", method = RequestMethod.PUT)
    int updateById(@RequestBody CourseRecommendQO qo);

    @RequestMapping(value = "/feign/course/courseRecommend/get/{id}", method = RequestMethod.GET)
    CourseRecommendVO getById(@PathVariable(value = "id") Long id);

}
