package com.roncoo.education.course.feign;

import com.roncoo.education.common.service.BaseController;
import com.roncoo.education.course.feign.biz.FeignCourseBiz;
import com.roncoo.education.course.feign.interfaces.IFeignCourse;
import com.roncoo.education.course.feign.interfaces.vo.CourseViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 课程信息
 *
 * @author wujing
 * @date 2022-08-27
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/course")
public class FeignCourseController extends BaseController implements IFeignCourse {

    @NotNull
    private final FeignCourseBiz biz;

    @Override
    public CourseViewVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }

    @Override
    public List<CourseViewVO> listByIds(@RequestBody List<Long> courseIds) {
        return biz.listByIds(courseIds);
    }
}
