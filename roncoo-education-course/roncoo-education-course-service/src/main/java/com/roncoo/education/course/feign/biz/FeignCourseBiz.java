package com.roncoo.education.course.feign.biz;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.BaseException;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.enums.IsFreeEnum;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.course.dao.*;
import com.roncoo.education.course.dao.impl.mapper.entity.*;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseExample.Criteria;
import com.roncoo.education.course.feign.interfaces.qo.CourseQO;
import com.roncoo.education.course.feign.interfaces.vo.CourseChapterPeriodVO;
import com.roncoo.education.course.feign.interfaces.vo.CourseChapterVO;
import com.roncoo.education.course.feign.interfaces.vo.CourseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 课程信息
 *
 * @author wujing
 */
@Component
public class FeignCourseBiz {

    @Autowired
    private CourseDao dao;
    @Autowired
    private ZoneCourseDao zoneCourseDao;
    @Autowired
    private CourseChapterDao courseChapterDao;
    @Autowired
    private CourseIntroduceDao courseIntroduceDao;
    @Autowired
    private CourseCategoryDao courseCategoryDao;
    @Autowired
    private CourseChapterPeriodDao courseChapterPeriodDao;

    public Page<CourseVO> listForPage(CourseQO qo) {
        CourseExample example = new CourseExample();
        Criteria c = example.createCriteria();
        if (qo.getZoneId() != null) {
            List<ZoneCourse> list = zoneCourseDao.listByZoneId(qo.getZoneId());
            if (CollectionUtil.isNotEmpty(list)) {
                List<Long> notInCourseNoList = new ArrayList<>();
                for (ZoneCourse zoneCourse : list) {
                    notInCourseNoList.add(zoneCourse.getCourseId());
                }
                qo.setNotInCourseNoList(notInCourseNoList);
            }
        }
        if (qo.getNotInCourseNoList() != null && !qo.getNotInCourseNoList().isEmpty()) {
            c.andIdNotIn(qo.getNotInCourseNoList());
        }

        if (qo.getCategoryId1() != null) {
            c.andCategoryId1EqualTo(qo.getCategoryId1());
        }
        if (!StringUtils.isEmpty(qo.getCourseName())) {
            c.andCourseNameLike(PageUtil.rightLike(qo.getCourseName()));
        }
        if (qo.getStatusId() != null) {
            c.andStatusIdEqualTo(qo.getStatusId());
        }
        if (qo.getIsFree() != null) {
            c.andIsFreeEqualTo(qo.getIsFree());
        }
        if (qo.getIsPutaway() != null) {
            c.andIsPutawayEqualTo(qo.getIsPutaway());
        }
        example.setOrderByClause(" status_id desc, is_putaway desc, course_sort desc, id desc ");
        Page<Course> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        Page<CourseVO> courseInfoVoPage = PageUtil.transform(page, CourseVO.class);
        // 获取分类名称
        for (CourseVO courseVO : courseInfoVoPage.getList()) {
            if (courseVO.getCategoryId1() != null && courseVO.getCategoryId1() != 0) {
                CourseCategory courseCategory = courseCategoryDao.getById(courseVO.getCategoryId1());
                if (!StringUtils.isEmpty(courseCategory)) {
                    courseVO.setCategoryName1(courseCategory.getCategoryName());
                }
            }
            if (courseVO.getCategoryId2() != null && courseVO.getCategoryId2() != 0) {
                CourseCategory courseCategory = courseCategoryDao.getById(courseVO.getCategoryId2());
                if (!StringUtils.isEmpty(courseCategory)) {
                    courseVO.setCategoryName2(courseCategory.getCategoryName());
                }
            }
            if (courseVO.getCategoryId3() != null && courseVO.getCategoryId3() != 0) {
                CourseCategory courseCategory = courseCategoryDao.getById(courseVO.getCategoryId3());
                if (!StringUtils.isEmpty(courseCategory)) {
                    courseVO.setCategoryName3(courseCategory.getCategoryName());
                }
            }
        }
        return courseInfoVoPage;
    }

    public int save(CourseQO qo) {
        Course record = BeanUtil.copyProperties(qo, Course.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public CourseVO getById(Long id) {
        // 根据id查找课程信息
        Course record = dao.getById(id);
        CourseVO course = BeanUtil.copyProperties(record, CourseVO.class);
        if (ObjectUtil.isNotNull(course)) {
            // 根据id查找课程简介信息
            CourseIntroduce courseIntroduce = courseIntroduceDao.getById(record.getIntroduceId());
            // 把课程简介带回课程信息
            if (ObjectUtil.isNotNull(courseIntroduce)) {
                course.setIntroduce(courseIntroduce.getIntroduce());
            }
        }
        return course;
    }

    @Transactional
    public int updateById(CourseQO qo) {
        if (IsFreeEnum.FREE.getCode().equals(qo.getIsFree())) {
            qo.setCourseOriginal(BigDecimal.ZERO);
            qo.setCourseDiscount(BigDecimal.ZERO);
        }
        Course record = BeanUtil.copyProperties(qo, Course.class);
        int result = dao.updateById(record);
        if (result < 1) {
            throw new BaseException("课程信息表更新失败");
        }
        CourseIntroduce courseIntroduce = courseIntroduceDao.getById(record.getIntroduceId());
        if (ObjectUtil.isNull(courseIntroduce)) {
            throw new BaseException("找不到课程简介信息");
        }
        courseIntroduce.setId(qo.getIntroduceId());
        courseIntroduce.setIntroduce(qo.getIntroduce());
        return courseIntroduceDao.updateById(courseIntroduce);
    }

    public CourseVO getByCourseId(Long id) {
        Course record = dao.getById(id);
        CourseVO courseVO = BeanUtil.copyProperties(record, CourseVO.class);
        // 获取分类名称
        if (courseVO.getCategoryId1() != null && courseVO.getCategoryId1() != 0) {
            CourseCategory courseCategory = courseCategoryDao.getById(courseVO.getCategoryId1());
            courseVO.setCategoryName1(courseCategory.getCategoryName());
        }
        if (courseVO.getCategoryId2() != null && courseVO.getCategoryId2() != 0) {
            CourseCategory courseCategory = courseCategoryDao.getById(courseVO.getCategoryId2());
            courseVO.setCategoryName2(courseCategory.getCategoryName());
        }
        if (courseVO.getCategoryId3() != null && courseVO.getCategoryId3() != 0) {
            CourseCategory courseCategory = courseCategoryDao.getById(courseVO.getCategoryId3());
            courseVO.setCategoryName3(courseCategory.getCategoryName());
        }
        // 章节
        List<CourseChapter> ChapterList = courseChapterDao.listByCourseIdAndStatusId(courseVO.getId(), StatusIdEnum.YES.getCode());
        if (CollectionUtil.isNotEmpty(ChapterList)) {
            List<CourseChapterVO> courseChapterVOList = new ArrayList<>();
            for (CourseChapter courseChapter : ChapterList) {
                // 课时
                List<CourseChapterPeriod> periodList = courseChapterPeriodDao.listByChapterIdAndStatusId(courseChapter.getId(), StatusIdEnum.YES.getCode());
                CourseChapterVO courseChapterVO = BeanUtil.copyProperties(courseChapter, CourseChapterVO.class);
                courseChapterVO.setCourseChapterPeriodVOList(PageUtil.copyList(periodList, CourseChapterPeriodVO.class));
                courseChapterVOList.add(courseChapterVO);
            }
            courseVO.setCourseChapterVOList(courseChapterVOList);
        }
        return courseVO;
    }
}
