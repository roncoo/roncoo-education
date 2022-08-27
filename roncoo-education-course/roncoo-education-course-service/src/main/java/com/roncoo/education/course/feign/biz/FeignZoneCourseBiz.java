package com.roncoo.education.course.feign.biz;


import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.ZoneCourseDao;
import com.roncoo.education.course.dao.impl.mapper.entity.ZoneCourse;
import com.roncoo.education.course.dao.impl.mapper.entity.ZoneCourseExample;
import com.roncoo.education.course.dao.impl.mapper.entity.ZoneCourseExample.Criteria;
import com.roncoo.education.course.feign.interfaces.qo.ZoneCourseEditQO;
import com.roncoo.education.course.feign.interfaces.qo.ZoneCoursePageQO;
import com.roncoo.education.course.feign.interfaces.qo.ZoneCourseSaveQO;
import com.roncoo.education.course.feign.interfaces.vo.ZoneCoursePageVO;
import com.roncoo.education.course.feign.interfaces.vo.ZoneCourseViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 专区课程关联表
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class FeignZoneCourseBiz extends BaseBiz {

    @NotNull
    private final ZoneCourseDao dao;

    public Page<ZoneCoursePageVO> page(ZoneCoursePageQO qo) {
        ZoneCourseExample example = new ZoneCourseExample();
        Criteria c = example.createCriteria();
        example.setOrderByClause(" id desc ");
        Page<ZoneCourse> page = dao.page(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, ZoneCoursePageVO.class);
    }

    public int save(ZoneCourseSaveQO qo) {
        ZoneCourse record = BeanUtil.copyProperties(qo, ZoneCourse.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public int updateById(ZoneCourseEditQO qo) {
        ZoneCourse record = BeanUtil.copyProperties(qo, ZoneCourse.class);
        return dao.updateById(record);
    }

    public ZoneCourseViewVO getById(Long id) {
        ZoneCourse record = dao.getById(id);
        return BeanUtil.copyProperties(record, ZoneCourseViewVO.class);
    }
}
