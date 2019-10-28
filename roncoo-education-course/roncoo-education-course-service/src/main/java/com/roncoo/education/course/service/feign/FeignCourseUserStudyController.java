package com.roncoo.education.course.service.feign;

import com.roncoo.education.course.feign.interfaces.IFeignCourseUserStudy;
import com.roncoo.education.course.feign.qo.CourseUserStudyQO;
import com.roncoo.education.course.feign.vo.CourseUserStudyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.roncoo.education.course.service.feign.biz.FeignCourseUserStudyBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

/**
 * 课程用户关联表
 *
 * @author wujing
 */
@RestController
public class FeignCourseUserStudyController extends BaseController implements IFeignCourseUserStudy {

    @Autowired
    private FeignCourseUserStudyBiz biz;

    @Override
    public Page<CourseUserStudyVO> listForPage(@RequestBody CourseUserStudyQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody CourseUserStudyQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody CourseUserStudyQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public CourseUserStudyVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }

}
