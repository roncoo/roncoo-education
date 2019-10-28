package com.roncoo.education.course.service.feign.biz;

import com.roncoo.education.course.feign.qo.CourseUserStudyQO;
import com.roncoo.education.course.feign.vo.CourseUserStudyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.course.service.dao.CourseUserStudyDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseUserStudy;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseUserStudyExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.BeanUtil;

/**
 * 课程用户关联表
 *
 * @author wujing
 */
@Component
public class FeignCourseUserStudyBiz {

    @Autowired
    private CourseUserStudyDao dao;

    public Page<CourseUserStudyVO> listForPage(CourseUserStudyQO qo) {
        CourseUserStudyExample example = new CourseUserStudyExample();
        example.setOrderByClause(" id desc ");
        Page<CourseUserStudy> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, CourseUserStudyVO.class);
    }

    public int save(CourseUserStudyQO qo) {
        CourseUserStudy record = BeanUtil.copyProperties(qo, CourseUserStudy.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public CourseUserStudyVO getById(Long id) {
        CourseUserStudy record = dao.getById(id);
        return BeanUtil.copyProperties(record, CourseUserStudyVO.class);
    }

    public int updateById(CourseUserStudyQO qo) {
        CourseUserStudy record = BeanUtil.copyProperties(qo, CourseUserStudy.class);
        return dao.updateById(record);
    }

}
