package com.roncoo.education.course.feign;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.biz.FeignCourseBiz;
import com.roncoo.education.course.feign.interfaces.IFeignCourse;
import com.roncoo.education.course.feign.interfaces.qo.CourseQO;
import com.roncoo.education.course.feign.interfaces.vo.CourseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 课程信息
 *
 * @author wujing
 */
@RestController
public class FeignCourseController extends BaseController implements IFeignCourse {

    @Autowired
    private FeignCourseBiz biz;

    @Override
    public Page<CourseVO> listForPage(@RequestBody CourseQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody CourseQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody CourseQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public CourseVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }

    @Override
    public CourseVO getByCourseId(@PathVariable(value = "id") Long id) {
        return biz.getByCourseId(id);
    }

}
