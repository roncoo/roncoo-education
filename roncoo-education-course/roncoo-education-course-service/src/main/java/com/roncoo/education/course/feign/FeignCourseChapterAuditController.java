package com.roncoo.education.course.feign;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.feign.biz.FeignCourseChapterAuditBiz;
import com.roncoo.education.course.feign.interfaces.IFeignCourseChapterAudit;
import com.roncoo.education.course.feign.interfaces.qo.CourseChapterAuditQO;
import com.roncoo.education.course.feign.interfaces.vo.CourseChapterAuditVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 章节信息-审核
 *
 * @author wujing
 */
@RestController
public class FeignCourseChapterAuditController extends BaseController implements IFeignCourseChapterAudit {

    @Autowired
    private FeignCourseChapterAuditBiz biz;

    @Override
    public Page<CourseChapterAuditVO> listForPage(@RequestBody CourseChapterAuditQO qo) {
        return biz.listForPage(qo);
    }

    @Override
    public int save(@RequestBody CourseChapterAuditQO qo) {
        return biz.save(qo);
    }

    @Override
    public int deleteById(@PathVariable(value = "id") Long id) {
        return biz.deleteById(id);
    }

    @Override
    public int updateById(@RequestBody CourseChapterAuditQO qo) {
        return biz.updateById(qo);
    }

    @Override
    public CourseChapterAuditVO getById(@PathVariable(value = "id") Long id) {
        return biz.getById(id);
    }

}
