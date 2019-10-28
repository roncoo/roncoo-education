package com.roncoo.education.course.service.feign;

import com.roncoo.education.course.feign.interfaces.IFeignCourseIntroduce;
import com.roncoo.education.course.feign.qo.CourseIntroduceQO;
import com.roncoo.education.course.feign.vo.CourseIntroduceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.roncoo.education.course.service.feign.biz.FeignCourseIntroduceBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

/**
 * 课程介绍信息
 *
 * @author wujing
 */
@RestController
public class FeignCourseIntroduceController extends BaseController implements IFeignCourseIntroduce {

    @Autowired
    private FeignCourseIntroduceBiz biz;

    @Override
    public Page<CourseIntroduceVO> listForPage(@RequestBody CourseIntroduceQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody CourseIntroduceQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody CourseIntroduceQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public CourseIntroduceVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }

}
