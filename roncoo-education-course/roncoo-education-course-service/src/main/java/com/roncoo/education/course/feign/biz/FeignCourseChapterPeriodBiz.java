package com.roncoo.education.course.feign.biz;


import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.CourseChapterPeriodDao;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterPeriod;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterPeriodExample;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterPeriodExample.Criteria;
import com.roncoo.education.course.feign.interfaces.qo.CourseChapterPeriodEditQO;
import com.roncoo.education.course.feign.interfaces.qo.CourseChapterPeriodPageQO;
import com.roncoo.education.course.feign.interfaces.qo.CourseChapterPeriodSaveQO;
import com.roncoo.education.course.feign.interfaces.vo.CourseChapterPeriodPageVO;
import com.roncoo.education.course.feign.interfaces.vo.CourseChapterPeriodViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 课时信息
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class FeignCourseChapterPeriodBiz extends BaseBiz {

    @NotNull
    private final CourseChapterPeriodDao dao;

    public Page<CourseChapterPeriodPageVO> page(CourseChapterPeriodPageQO qo) {
        CourseChapterPeriodExample example = new CourseChapterPeriodExample();
        Criteria c = example.createCriteria();
        example.setOrderByClause(" id desc ");
        Page<CourseChapterPeriod> page = dao.page(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, CourseChapterPeriodPageVO.class);
    }

    public int save(CourseChapterPeriodSaveQO qo) {
        CourseChapterPeriod record = BeanUtil.copyProperties(qo, CourseChapterPeriod.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public int updateById(CourseChapterPeriodEditQO qo) {
        CourseChapterPeriod record = BeanUtil.copyProperties(qo, CourseChapterPeriod.class);
        return dao.updateById(record);
    }

    public CourseChapterPeriodViewVO getById(Long id) {
        CourseChapterPeriod record = dao.getById(id);
        return BeanUtil.copyProperties(record, CourseChapterPeriodViewVO.class);
    }
}
