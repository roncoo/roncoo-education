package com.roncoo.education.course.service.auth.biz;

import com.roncoo.education.common.core.base.BaseBiz;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.course.dao.CourseChapterPeriodDao;
import com.roncoo.education.course.dao.CourseDao;
import com.roncoo.education.course.dao.CourseUserStudyLogDao;
import com.roncoo.education.course.dao.impl.mapper.entity.Course;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterPeriod;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseUserStudyLog;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseUserStudyLogExample;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseUserStudyLogExample.Criteria;
import com.roncoo.education.course.service.auth.bo.AuthCourseUserStudyLogPageBO;
import com.roncoo.education.course.service.auth.dto.AuthCourseUserStudyLogPageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 课程信息
 *
 * @author wujing
 */
@Component
public class AuthApiCourseUserStudyLogBiz extends BaseBiz {

    @Autowired
    private CourseUserStudyLogDao courseUserStudyLogDao;
    @Autowired
    private CourseDao courseDao;
    @Autowired
    private CourseChapterPeriodDao periodDao;

    /**
     * 最近学习日志分页列出接口
     *
     * @param bo
     * @return
     * @author wuyun
     */
    public Result<Page<AuthCourseUserStudyLogPageDTO>> list(AuthCourseUserStudyLogPageBO authCourseUserStudyLogPageBO) {
        CourseUserStudyLogExample example = new CourseUserStudyLogExample();
        Criteria c = example.createCriteria();
        if (authCourseUserStudyLogPageBO.getUserNo() == null) {
            return Result.error("userNo不能为空");
        }
        c.andUserNoEqualTo(authCourseUserStudyLogPageBO.getUserNo());
        Page<CourseUserStudyLog> page = courseUserStudyLogDao.listForPage(authCourseUserStudyLogPageBO.getPageCurrent(), authCourseUserStudyLogPageBO.getPageSize(), example);
        Page<AuthCourseUserStudyLogPageDTO> dtoList = PageUtil.transform(page, AuthCourseUserStudyLogPageDTO.class);
        for (AuthCourseUserStudyLogPageDTO dto : dtoList.getList()) {
            // 查看课程信息
            Course course = courseDao.getById(dto.getCourseId());
            // 查看课时信息
            CourseChapterPeriod period = periodDao.getById(dto.getPeriodId());

            dto.setCourseName(course.getCourseName());
            dto.setPeriodName(period.getPeriodName());
        }
        return Result.success(dtoList);
    }

}
