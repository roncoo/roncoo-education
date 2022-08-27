package com.roncoo.education.course.feign;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.service.BaseController;
import com.roncoo.education.course.feign.biz.FeignUserCourseBiz;
import com.roncoo.education.course.feign.interfaces.IFeignUserCourse;
import com.roncoo.education.course.feign.interfaces.qo.UserCourseEditQO;
import com.roncoo.education.course.feign.interfaces.qo.UserCoursePageQO;
import com.roncoo.education.course.feign.interfaces.qo.UserCourseSaveQO;
import com.roncoo.education.course.feign.interfaces.vo.UserCoursePageVO;
import com.roncoo.education.course.feign.interfaces.vo.UserCourseViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
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
    public Page<UserCoursePageVO> page(@RequestBody UserCoursePageQO qo) {
        return biz.page(qo);
    }

    @Override
    public int save(@RequestBody UserCourseSaveQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody UserCourseEditQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public UserCourseViewVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }
}
