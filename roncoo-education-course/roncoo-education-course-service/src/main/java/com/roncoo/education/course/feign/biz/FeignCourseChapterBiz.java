package com.roncoo.education.course.feign.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.course.dao.CourseChapterDao;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapter;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterExample;
import com.roncoo.education.course.feign.interfaces.qo.CourseChapterQO;
import com.roncoo.education.course.feign.interfaces.vo.CourseChapterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 章节信息
 *
 * @author wujing
 */
@Component
public class FeignCourseChapterBiz {

    @Autowired
    private CourseChapterDao dao;

    public Page<CourseChapterVO> listForPage(CourseChapterQO qo) {
        CourseChapterExample example = new CourseChapterExample();
        example.setOrderByClause(" id desc ");
        Page<CourseChapter> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, CourseChapterVO.class);
    }

    public int save(CourseChapterQO qo) {
        CourseChapter record = BeanUtil.copyProperties(qo, CourseChapter.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public CourseChapterVO getById(Long id) {
        CourseChapter record = dao.getById(id);
        return BeanUtil.copyProperties(record, CourseChapterVO.class);
    }

    public int updateById(CourseChapterQO qo) {
        CourseChapter record = BeanUtil.copyProperties(qo, CourseChapter.class);
        return dao.updateById(record);
    }

}
