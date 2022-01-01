package com.roncoo.education.course.feign.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.course.dao.CourseChapterPeriodAuditDao;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterPeriodAudit;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterPeriodAuditExample;
import com.roncoo.education.course.feign.interfaces.qo.CourseChapterPeriodAuditQO;
import com.roncoo.education.course.feign.interfaces.vo.CourseChapterPeriodAuditVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 课时信息-审核
 *
 * @author wujing
 */
@Component
public class FeignCourseChapterPeriodAuditBiz {

    @Autowired
    private CourseChapterPeriodAuditDao dao;

    public Page<CourseChapterPeriodAuditVO> listForPage(CourseChapterPeriodAuditQO qo) {
        CourseChapterPeriodAuditExample example = new CourseChapterPeriodAuditExample();
        example.setOrderByClause(" id desc ");
        Page<CourseChapterPeriodAudit> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, CourseChapterPeriodAuditVO.class);
    }

    public int save(CourseChapterPeriodAuditQO qo) {
        CourseChapterPeriodAudit record = BeanUtil.copyProperties(qo, CourseChapterPeriodAudit.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public CourseChapterPeriodAuditVO getById(Long id) {
        CourseChapterPeriodAudit record = dao.getById(id);
        return BeanUtil.copyProperties(record, CourseChapterPeriodAuditVO.class);
    }

    public int updateById(CourseChapterPeriodAuditQO qo) {
        CourseChapterPeriodAudit record = BeanUtil.copyProperties(qo, CourseChapterPeriodAudit.class);
        return dao.updateById(record);
    }

    public CourseChapterPeriodAuditVO getByVideoNo(Long videoNo) {
        CourseChapterPeriodAudit record = dao.getByVideoNo(videoNo);
        return BeanUtil.copyProperties(record, CourseChapterPeriodAuditVO.class);
    }
}
