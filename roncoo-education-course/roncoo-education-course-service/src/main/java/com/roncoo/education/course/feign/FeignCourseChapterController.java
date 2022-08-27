package com.roncoo.education.course.feign;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.service.BaseController;
import com.roncoo.education.course.feign.biz.FeignCourseChapterBiz;
import com.roncoo.education.course.feign.interfaces.IFeignCourseChapter;
import com.roncoo.education.course.feign.interfaces.qo.CourseChapterEditQO;
import com.roncoo.education.course.feign.interfaces.qo.CourseChapterPageQO;
import com.roncoo.education.course.feign.interfaces.qo.CourseChapterSaveQO;
import com.roncoo.education.course.feign.interfaces.vo.CourseChapterPageVO;
import com.roncoo.education.course.feign.interfaces.vo.CourseChapterViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * 章节信息
 *
 * @author wujing
 * @date 2022-08-27
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/course/course/chapter")
public class FeignCourseChapterController extends BaseController implements IFeignCourseChapter {

    @NotNull
    private final FeignCourseChapterBiz biz;

    @Override
    public Page<CourseChapterPageVO> page(@RequestBody CourseChapterPageQO qo) {
        return biz.page(qo);
    }

    @Override
    public int save(@RequestBody CourseChapterSaveQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody CourseChapterEditQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public CourseChapterViewVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }
}
