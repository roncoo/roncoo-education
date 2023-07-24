package com.roncoo.education.course.feign.interfaces;

import com.roncoo.education.course.feign.interfaces.qo.UserCourseBindingQO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 课程用户关联表 接口
 *
 * @author wujing
 * @date 2022-08-27
 */
@FeignClient(value = "service-course", path = "/course/user/course")
public interface IFeignUserCourse {

    @PutMapping(value = "/binding")
    int binding(@RequestBody UserCourseBindingQO qo);
}
