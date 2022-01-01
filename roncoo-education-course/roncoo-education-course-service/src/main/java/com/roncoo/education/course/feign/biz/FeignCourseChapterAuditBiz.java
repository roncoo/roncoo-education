package com.roncoo.education.course.feign.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.course.dao.CourseChapterAuditDao;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterAudit;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterAuditExample;
import com.roncoo.education.course.feign.interfaces.qo.CourseChapterAuditQO;
import com.roncoo.education.course.feign.interfaces.vo.CourseChapterAuditVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 章节信息-审核
 *
 * @author wujing
 */
@Component
public class FeignCourseChapterAuditBiz {

    @Autowired
    private CourseChapterAuditDao dao;

    public Page<CourseChapterAuditVO> listForPage(CourseChapterAuditQO qo) {
        CourseChapterAuditExample example = new CourseChapterAuditExample();
        example.setOrderByClause(" id desc ");
        Page<CourseChapterAudit> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, CourseChapterAuditVO.class);
    }

    public int save(CourseChapterAuditQO qo) {
        CourseChapterAudit record = BeanUtil.copyProperties(qo, CourseChapterAudit.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public CourseChapterAuditVO getById(Long id) {
        CourseChapterAudit record = dao.getById(id);
        return BeanUtil.copyProperties(record, CourseChapterAuditVO.class);
    }

    public int updateById(CourseChapterAuditQO qo) {
        CourseChapterAudit record = BeanUtil.copyProperties(qo, CourseChapterAudit.class);
        return dao.updateById(record);
    }

}
