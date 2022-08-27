package com.roncoo.education.course.feign;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.service.BaseController;
import com.roncoo.education.course.feign.biz.FeignCourseBiz;
import com.roncoo.education.course.feign.interfaces.IFeignCourse;
import com.roncoo.education.course.feign.interfaces.qo.CourseEditQO;
import com.roncoo.education.course.feign.interfaces.qo.CoursePageQO;
import com.roncoo.education.course.feign.interfaces.qo.CourseSaveQO;
import com.roncoo.education.course.feign.interfaces.vo.CoursePageVO;
import com.roncoo.education.course.feign.interfaces.vo.CourseViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

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
    public Page<CoursePageVO> page(@RequestBody CoursePageQO qo) {
        return biz.page(qo);
    }

    @Override
    public int save(@RequestBody CourseSaveQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody CourseEditQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public CourseViewVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }
}
