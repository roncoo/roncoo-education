package com.roncoo.education.course.service.feign;

import com.roncoo.education.course.feign.interfaces.IFeignZoneCourse;
import com.roncoo.education.course.feign.qo.ZoneCourseQO;
import com.roncoo.education.course.feign.vo.ZoneCourseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.roncoo.education.course.service.feign.biz.FeignZoneCourseBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

/**
 * 专区课程关联表
 *
 * @author wujing
 */
@RestController
public class FeignZoneCourseController extends BaseController implements IFeignZoneCourse {

    @Autowired
    private FeignZoneCourseBiz biz;

    @Override
    public Page<ZoneCourseVO> listForPage(@RequestBody ZoneCourseQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody ZoneCourseQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody ZoneCourseQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public ZoneCourseVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }

}
