package com.roncoo.education.course.feign;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.service.BaseController;
import com.roncoo.education.course.feign.biz.FeignCourseChapterPeriodBiz;
import com.roncoo.education.course.feign.interfaces.IFeignCourseChapterPeriod;
import com.roncoo.education.course.feign.interfaces.qo.CourseChapterPeriodEditQO;
import com.roncoo.education.course.feign.interfaces.qo.CourseChapterPeriodPageQO;
import com.roncoo.education.course.feign.interfaces.qo.CourseChapterPeriodSaveQO;
import com.roncoo.education.course.feign.interfaces.vo.CourseChapterPeriodPageVO;
import com.roncoo.education.course.feign.interfaces.vo.CourseChapterPeriodViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * 课时信息
 *
 * @author wujing
 * @date 2022-08-27
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/course/chapter/period")
public class FeignCourseChapterPeriodController extends BaseController implements IFeignCourseChapterPeriod {

    @NotNull
    private final FeignCourseChapterPeriodBiz biz;

    @Override
    public Page<CourseChapterPeriodPageVO> page(@RequestBody CourseChapterPeriodPageQO qo) {
        return biz.page(qo);
    }

    @Override
    public int save(@RequestBody CourseChapterPeriodSaveQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody CourseChapterPeriodEditQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public CourseChapterPeriodViewVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }
}
