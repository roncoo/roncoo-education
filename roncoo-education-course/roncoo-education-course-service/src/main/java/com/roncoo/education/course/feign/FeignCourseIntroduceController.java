package com.roncoo.education.course.feign;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.biz.FeignCourseIntroduceBiz;
import com.roncoo.education.course.feign.interfaces.IFeignCourseIntroduce;
import com.roncoo.education.course.feign.interfaces.qo.CourseIntroduceQO;
import com.roncoo.education.course.feign.interfaces.vo.CourseIntroduceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
