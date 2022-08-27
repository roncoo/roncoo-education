package com.roncoo.education.course.feign.biz;


import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.CourseDao;
import com.roncoo.education.course.dao.impl.mapper.entity.Course;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseExample;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseExample.Criteria;
import com.roncoo.education.course.feign.interfaces.qo.CourseEditQO;
import com.roncoo.education.course.feign.interfaces.qo.CoursePageQO;
import com.roncoo.education.course.feign.interfaces.qo.CourseSaveQO;
import com.roncoo.education.course.feign.interfaces.vo.CoursePageVO;
import com.roncoo.education.course.feign.interfaces.vo.CourseViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * 课程信息
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class FeignCourseBiz extends BaseBiz {

    @NotNull
    private final CourseDao dao;

    public Page<CoursePageVO> page(CoursePageQO qo) {
        CourseExample example = new CourseExample();
        Criteria c = example.createCriteria();
        example.setOrderByClause(" id desc ");
        Page<Course> page = dao.page(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, CoursePageVO.class);
    }

    public int save(CourseSaveQO qo) {
        Course record = BeanUtil.copyProperties(qo, Course.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public int updateById(CourseEditQO qo) {
        Course record = BeanUtil.copyProperties(qo, Course.class);
        return dao.updateById(record);
    }

    public CourseViewVO getById(Long id) {
        Course record = dao.getById(id);
        return BeanUtil.copyProperties(record, CourseViewVO.class);
    }

    public List<CourseViewVO> listByIds(List<Long> courseIds) {
        List<Course> list = dao.listByIds(courseIds);
        return BeanUtil.copyProperties(list, CourseViewVO.class);
    }
}
