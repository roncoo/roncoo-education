package com.roncoo.education.course.service.feign.biz;

import com.roncoo.education.course.feign.qo.CourseChapterQO;
import com.roncoo.education.course.feign.vo.CourseChapterVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.roncoo.education.course.service.dao.CourseChapterDao;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseChapter;
import com.roncoo.education.course.service.dao.impl.mapper.entity.CourseChapterExample;
import com.roncoo.education.util.base.Page;
import com.roncoo.education.util.base.PageUtil;
import com.roncoo.education.util.tools.BeanUtil;

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
