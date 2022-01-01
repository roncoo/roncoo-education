package com.roncoo.education.course.feign;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.biz.FeignCourseIntroduceAuditBiz;
import com.roncoo.education.course.feign.interfaces.IFeignCourseIntroduceAudit;
import com.roncoo.education.course.feign.interfaces.qo.CourseIntroduceAuditQO;
import com.roncoo.education.course.feign.interfaces.vo.CourseIntroduceAuditVO;
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
public class FeignCourseIntroduceAuditController extends BaseController implements IFeignCourseIntroduceAudit {

    @Autowired
    private FeignCourseIntroduceAuditBiz biz;

    @Override
    public Page<CourseIntroduceAuditVO> listForPage(@RequestBody CourseIntroduceAuditQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody CourseIntroduceAuditQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody CourseIntroduceAuditQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public CourseIntroduceAuditVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }

}
