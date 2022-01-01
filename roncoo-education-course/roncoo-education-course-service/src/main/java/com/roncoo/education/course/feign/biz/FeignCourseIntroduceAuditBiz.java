package com.roncoo.education.course.feign.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.course.dao.CourseIntroduceAuditDao;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseIntroduceAudit;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseIntroduceAuditExample;
import com.roncoo.education.course.feign.interfaces.qo.CourseIntroduceAuditQO;
import com.roncoo.education.course.feign.interfaces.vo.CourseIntroduceAuditVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 课程介绍信息
 *
 * @author wujing
 */
@Component
public class FeignCourseIntroduceAuditBiz {

    @Autowired
    private CourseIntroduceAuditDao dao;

    public Page<CourseIntroduceAuditVO> listForPage(CourseIntroduceAuditQO qo) {
        CourseIntroduceAuditExample example = new CourseIntroduceAuditExample();
        example.setOrderByClause(" id desc ");
        Page<CourseIntroduceAudit> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, CourseIntroduceAuditVO.class);
    }

    public int save(CourseIntroduceAuditQO qo) {
        CourseIntroduceAudit record = BeanUtil.copyProperties(qo, CourseIntroduceAudit.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public CourseIntroduceAuditVO getById(Long id) {
        CourseIntroduceAudit record = dao.getById(id);
        return BeanUtil.copyProperties(record, CourseIntroduceAuditVO.class);
    }

    public int updateById(CourseIntroduceAuditQO qo) {
        CourseIntroduceAudit record = BeanUtil.copyProperties(qo, CourseIntroduceAudit.class);
        return dao.updateById(record);
    }

}
