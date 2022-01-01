package com.roncoo.education.course.feign;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.biz.FeignCourseChapterPeriodAuditBiz;
import com.roncoo.education.course.feign.interfaces.IFeignCourseChapterPeriodAudit;
import com.roncoo.education.course.feign.interfaces.qo.CourseChapterPeriodAuditQO;
import com.roncoo.education.course.feign.interfaces.vo.CourseChapterPeriodAuditVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 课时信息-审核
 *
 * @author wujing
 */
@RestController
public class FeignCourseChapterPeriodAuditController extends BaseController implements IFeignCourseChapterPeriodAudit {

    @Autowired
    private FeignCourseChapterPeriodAuditBiz biz;

    @Override
    public Page<CourseChapterPeriodAuditVO> listForPage(@RequestBody CourseChapterPeriodAuditQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody CourseChapterPeriodAuditQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody CourseChapterPeriodAuditQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public CourseChapterPeriodAuditVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }

    @Override
    public CourseChapterPeriodAuditVO getByVideoNo(@RequestBody Long videoNo) {
        return biz.getByVideoNo(videoNo);
    }

}
