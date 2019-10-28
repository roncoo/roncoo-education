package com.roncoo.education.course.service.feign;

import com.roncoo.education.course.feign.interfaces.IFeignCourseChapter;
import com.roncoo.education.course.feign.qo.CourseChapterQO;
import com.roncoo.education.course.feign.vo.CourseChapterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.roncoo.education.course.service.feign.biz.FeignCourseChapterBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

/**
 * 章节信息
 *
 * @author wujing
 */
@RestController
public class FeignCourseChapterController extends BaseController implements IFeignCourseChapter {

    @Autowired
    private FeignCourseChapterBiz biz;

    @Override
    public Page<CourseChapterVO> listForPage(@RequestBody CourseChapterQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody CourseChapterQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody CourseChapterQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public CourseChapterVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }

}
