package com.roncoo.education.course.feign.biz;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.course.dao.CourseChapterPeriodDao;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterPeriod;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterPeriodExample;
import com.roncoo.education.course.feign.interfaces.qo.CourseChapterPeriodQO;
import com.roncoo.education.course.feign.interfaces.vo.CourseChapterPeriodVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 课时信息
 *
 * @author wujing
 */
@Component
public class FeignCourseChapterPeriodBiz {

    @Autowired
    private CourseChapterPeriodDao dao;

    public Page<CourseChapterPeriodVO> listForPage(CourseChapterPeriodQO qo) {
        CourseChapterPeriodExample example = new CourseChapterPeriodExample();
        example.setOrderByClause(" id desc ");
        Page<CourseChapterPeriod> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, CourseChapterPeriodVO.class);
    }

    public int save(CourseChapterPeriodQO qo) {
        CourseChapterPeriod record = BeanUtil.copyProperties(qo, CourseChapterPeriod.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public CourseChapterPeriodVO getById(Long id) {
        CourseChapterPeriod record = dao.getById(id);
        return BeanUtil.copyProperties(record, CourseChapterPeriodVO.class);
    }

    public int updateById(CourseChapterPeriodQO qo) {
        CourseChapterPeriod record = BeanUtil.copyProperties(qo, CourseChapterPeriod.class);
        return dao.updateById(record);
    }

    /**
     * 根据视频编号查询课时正式表信息
     *
     * @param videoNo
     * @return
     * @author wuyun
     */
    public CourseChapterPeriodVO getByVideoNo(Long videoNo) {
        CourseChapterPeriod record = dao.getByVideoNo(videoNo);
        return BeanUtil.copyProperties(record, CourseChapterPeriodVO.class);
    }

}
