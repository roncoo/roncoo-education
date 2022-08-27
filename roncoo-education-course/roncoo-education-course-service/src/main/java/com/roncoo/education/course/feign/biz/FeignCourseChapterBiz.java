package com.roncoo.education.course.feign.biz;


import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.dao.CourseChapterDao;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapter;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterExample;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterExample.Criteria;
import com.roncoo.education.course.feign.interfaces.qo.CourseChapterEditQO;
import com.roncoo.education.course.feign.interfaces.qo.CourseChapterPageQO;
import com.roncoo.education.course.feign.interfaces.qo.CourseChapterSaveQO;
import com.roncoo.education.course.feign.interfaces.vo.CourseChapterPageVO;
import com.roncoo.education.course.feign.interfaces.vo.CourseChapterViewVO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;

/**
 * 章节信息
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class FeignCourseChapterBiz extends BaseBiz {

    @NotNull
    private final CourseChapterDao dao;

    public Page<CourseChapterPageVO> page(CourseChapterPageQO qo) {
        CourseChapterExample example = new CourseChapterExample();
        Criteria c = example.createCriteria();
        example.setOrderByClause(" id desc ");
        Page<CourseChapter> page = dao.page(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, CourseChapterPageVO.class);
    }

    public int save(CourseChapterSaveQO qo) {
        CourseChapter record = BeanUtil.copyProperties(qo, CourseChapter.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public int updateById(CourseChapterEditQO qo) {
        CourseChapter record = BeanUtil.copyProperties(qo, CourseChapter.class);
        return dao.updateById(record);
    }

    public CourseChapterViewVO getById(Long id) {
        CourseChapter record = dao.getById(id);
        return BeanUtil.copyProperties(record, CourseChapterViewVO.class);
    }
}
