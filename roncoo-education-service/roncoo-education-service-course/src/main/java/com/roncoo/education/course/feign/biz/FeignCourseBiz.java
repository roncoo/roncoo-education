package com.roncoo.education.course.feign.biz;


import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.CourseDao;
import com.roncoo.education.course.dao.impl.mapper.entity.Course;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseExample;
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

    public CourseViewVO getById(Long id) {
        Course record = dao.getById(id);
        return BeanUtil.copyProperties(record, CourseViewVO.class);
    }

    public List<CourseViewVO> listByIds(List<Long> courseIds) {
        List<Course> list = dao.listByIds(courseIds);
        return BeanUtil.copyProperties(list, CourseViewVO.class);
    }

    public Integer count() {
        return dao.countByExample(new CourseExample());
    }

    public List<CourseViewVO> listByLecturerId(Long lecturerId) {
        CourseExample example = new CourseExample();
        example.createCriteria().andLecturerIdEqualTo(lecturerId);
        return BeanUtil.copyProperties(dao.listByExample(example), CourseViewVO.class);
    }
}
