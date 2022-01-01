package com.roncoo.education.course.feign;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.biz.FeignCourseChapterPeriodBiz;
import com.roncoo.education.course.feign.interfaces.IFeignCourseChapterPeriod;
import com.roncoo.education.course.feign.interfaces.qo.CourseChapterPeriodQO;
import com.roncoo.education.course.feign.interfaces.vo.CourseChapterPeriodVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 课时信息
 *
 * @author wujing
 */
@RestController
public class FeignCourseChapterPeriodController extends BaseController implements IFeignCourseChapterPeriod {

    @Autowired
    private FeignCourseChapterPeriodBiz biz;

    @Override
    public Page<CourseChapterPeriodVO> listForPage(@RequestBody CourseChapterPeriodQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody CourseChapterPeriodQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody CourseChapterPeriodQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public CourseChapterPeriodVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }

    @Override
    public CourseChapterPeriodVO getByVideoNo(@RequestBody Long videoNo) {
        return biz.getByVideoNo(videoNo);
    }

}
