package com.roncoo.education.course.feign.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.course.dao.CourseIntroduceDao;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseIntroduce;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseIntroduceExample;
import com.roncoo.education.course.feign.interfaces.qo.CourseIntroduceQO;
import com.roncoo.education.course.feign.interfaces.vo.CourseIntroduceVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 课程介绍信息
 *
 * @author wujing
 */
@Component
public class FeignCourseIntroduceBiz {

    @Autowired
    private CourseIntroduceDao dao;

    public Page<CourseIntroduceVO> listForPage(CourseIntroduceQO qo) {
        CourseIntroduceExample example = new CourseIntroduceExample();
        example.setOrderByClause(" id desc ");
        Page<CourseIntroduce> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, CourseIntroduceVO.class);
    }

    public int save(CourseIntroduceQO qo) {
        CourseIntroduce record = BeanUtil.copyProperties(qo, CourseIntroduce.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public CourseIntroduceVO getById(Long id) {
        CourseIntroduce record = dao.getById(id);
        return BeanUtil.copyProperties(record, CourseIntroduceVO.class);
    }

    public int updateById(CourseIntroduceQO qo) {
        CourseIntroduce record = BeanUtil.copyProperties(qo, CourseIntroduce.class);
        return dao.updateById(record);
    }

}
