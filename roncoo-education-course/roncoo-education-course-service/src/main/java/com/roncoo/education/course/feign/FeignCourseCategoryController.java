package com.roncoo.education.course.feign;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.biz.FeignCourseCategoryBiz;
import com.roncoo.education.course.feign.interfaces.IFeignCourseCategory;
import com.roncoo.education.course.feign.interfaces.qo.CourseCategoryQO;
import com.roncoo.education.course.feign.interfaces.vo.CourseCategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 课程分类
 *
 * @author wujing
 */
@RestController
public class FeignCourseCategoryController extends BaseController implements IFeignCourseCategory {

    @Autowired
    private FeignCourseCategoryBiz biz;

    @Override
    public Page<CourseCategoryVO> listForPage(@RequestBody CourseCategoryQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody CourseCategoryQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody CourseCategoryQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public CourseCategoryVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }

    @Override
    public List<CourseCategoryVO> listByFloor(@PathVariable(value = "floor") Integer floor) {
        return biz.listByFloor(floor);
    }

    @Override
    public List<CourseCategoryVO> listByParentId(@PathVariable(value = "parentId") Long parentId) {
        return biz.listByParentId(parentId);
    }

    @Override
    public List<CourseCategoryVO> listByFloorAndCategoryId(@RequestBody CourseCategoryQO qo) {
        return biz.listByFloorAndCategoryId(qo);
    }
}
