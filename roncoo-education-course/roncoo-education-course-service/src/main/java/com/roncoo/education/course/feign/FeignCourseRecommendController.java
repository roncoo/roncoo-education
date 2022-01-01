package com.roncoo.education.course.feign;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.biz.FeignCourseRecommendBiz;
import com.roncoo.education.course.feign.interfaces.IFeignCourseRecommend;
import com.roncoo.education.course.feign.interfaces.qo.CourseRecommendQO;
import com.roncoo.education.course.feign.interfaces.vo.CourseRecommendVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 课程推荐
 *
 * @author wujing
 */
@RestController
public class FeignCourseRecommendController extends BaseController implements IFeignCourseRecommend {

    @Autowired
    private FeignCourseRecommendBiz biz;

    @Override
    public Page<CourseRecommendVO> listForPage(@RequestBody CourseRecommendQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody CourseRecommendQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody CourseRecommendQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public CourseRecommendVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }

}
