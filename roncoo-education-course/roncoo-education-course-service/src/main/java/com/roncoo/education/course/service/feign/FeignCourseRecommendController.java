package com.roncoo.education.course.service.feign;

import com.roncoo.education.course.feign.interfaces.IFeignCourseRecommend;
import com.roncoo.education.course.feign.qo.CourseRecommendQO;
import com.roncoo.education.course.feign.vo.CourseRecommendVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.course.service.feign.biz.FeignCourseRecommendBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

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
