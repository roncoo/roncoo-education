package com.roncoo.education.course.service.feign;

import com.roncoo.education.course.feign.interfaces.IFeignCourseChapterAudit;
import com.roncoo.education.course.feign.qo.CourseChapterAuditQO;
import com.roncoo.education.course.feign.vo.CourseChapterAuditVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;

import com.roncoo.education.course.service.feign.biz.FeignCourseChapterAuditBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Page;

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
