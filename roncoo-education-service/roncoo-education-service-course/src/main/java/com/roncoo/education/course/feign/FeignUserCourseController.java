package com.roncoo.education.course.feign;

import com.roncoo.education.common.service.BaseController;
import com.roncoo.education.course.feign.biz.FeignUserCourseBiz;
import com.roncoo.education.course.feign.interfaces.IFeignUserCourse;
import com.roncoo.education.course.feign.interfaces.qo.UserCourseBindingQO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * 课程用户关联表
 *
 * @author wujing
 * @date 2022-08-27
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/user/course")
public class FeignUserCourseController extends BaseController implements IFeignUserCourse {

    @NotNull
    private final FeignUserCourseBiz biz;

    @Override
    public int binding(@RequestBody UserCourseBindingQO qo) {
        return biz.binding(qo);
    }
}
