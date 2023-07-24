package com.roncoo.education.course.feign.interfaces;

import com.roncoo.education.course.feign.interfaces.vo.CourseViewVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * 课程信息 接口
 *
 * @author wujing
 * @date 2022-08-27
 */
@FeignClient(value = "service-course", path = "/course/course")
public interface IFeignCourse {

    /**
     * 根据ID获取课程信息
     *
     * @param id 主键ID
     * @return 课程信息
     */
    @GetMapping(value = "/get/{id}")
    CourseViewVO getById(@PathVariable(value = "id") Long id);

    @PostMapping(value = "/listByIds")
    List<CourseViewVO> listByIds(@RequestBody List<Long> courseIds);
}
