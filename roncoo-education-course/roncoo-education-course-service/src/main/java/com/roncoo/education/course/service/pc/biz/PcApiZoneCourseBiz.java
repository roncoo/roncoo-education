package com.roncoo.education.course.service.pc.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.ResultEnum;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.course.dao.CourseCategoryDao;
import com.roncoo.education.course.dao.CourseDao;
import com.roncoo.education.course.dao.ZoneCourseDao;
import com.roncoo.education.course.dao.ZoneDao;
import com.roncoo.education.course.dao.impl.mapper.entity.*;
import com.roncoo.education.course.dao.impl.mapper.entity.ZoneCourseExample.Criteria;
import com.roncoo.education.course.service.pc.req.*;
import com.roncoo.education.course.service.pc.resq.ZoneCoursePageRESQ;
import com.roncoo.education.course.service.pc.resq.ZoneCourseViewRESQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * 专区课程关联表
 */
@Component
public class PcApiZoneCourseBiz {
    @Autowired
    private ZoneDao zoneDao;
    @Autowired
    private ZoneCourseDao dao;
    @Autowired
    private CourseCategoryDao courseCategoryDao;
    @Autowired
    private CourseDao courseDao;

    /**
     * 分页列出
     *
     * @param zoneCoursePageREQ
     * @return
     */
    public Result<Page<ZoneCoursePageRESQ>> listForPage(ZoneCoursePageREQ req) {
        if (StringUtils.isEmpty(req.getZoneId())) {
            return Result.error("专区ID不能为空");
        }
        ZoneCourseExample example = new ZoneCourseExample();
        Criteria c = example.createCriteria();
        c.andZoneIdEqualTo(req.getZoneId());
        if (!StringUtils.isEmpty(req.getCourseName())) {
            Course course = courseDao.getByCourseName(req.getCourseName());
            if (ObjectUtil.isNull(course)) {
                c.andCourseIdEqualTo(0L);
            } else {
                c.andCourseIdEqualTo(course.getId());
            }
        }
        example.setOrderByClause("status_id desc, sort desc, id desc");
        Page<ZoneCourse> listForPage = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
        Page<ZoneCoursePageRESQ> page = PageUtil.transform(listForPage, ZoneCoursePageRESQ.class);
        // 根据课程ID获取课程信息
        for (ZoneCoursePageRESQ resq : page.getList()) {
            List<Course> course = courseDao.listByCategoryId(resq.getCourseId());
            for (Course courseinfo : course) {
                // 获取课程所属分类
                if (courseinfo.getCategoryId1() != null && courseinfo.getCategoryId1() != 0) {
                    CourseCategory courseCategory = courseCategoryDao.getById(courseinfo.getCategoryId1());
                    if (courseCategory != null) {
                        resq.setCategoryName1(courseCategory.getCategoryName());
                    }
                }
                if (courseinfo.getCategoryId2() != null && courseinfo.getCategoryId2() != 0) {
                    CourseCategory courseCategory = courseCategoryDao.getById(courseinfo.getCategoryId2());
                    if (courseCategory != null) {
                        resq.setCategoryName2(courseCategory.getCategoryName());
                    }
                }
                if (courseinfo.getCategoryId3() != null && courseinfo.getCategoryId3() != 0) {
                    CourseCategory courseCategory = courseCategoryDao.getById(courseinfo.getCategoryId3());
                    if (courseCategory != null) {
                        resq.setCategoryName3(courseCategory.getCategoryName());
                    }
                }
                resq.setCourseName(courseinfo.getCourseName());
            }

        }
        return Result.success(page);
    }

    /**
     * 专区课程保存
     *
     * @param req
     * @return
     */
    public Result<Integer> save(ZoneCourseSaveREQ req) {
        if (ObjectUtil.isNull(req.getCourseId())) {
            return Result.error("课程ID不能为空");
        }
        if (ObjectUtil.isNull(req.getZoneId())) {
            return Result.error("专区ID不能为空");
        }

        Zone zone = zoneDao.getById(req.getZoneId());
        if (ObjectUtil.isNull(zone) && !StatusIdEnum.YES.getCode().equals(zone.getStatusId())) {
            return Result.error("找不到专区信息");
        }

        // 根据课程编号获取课程信息
        Course course = courseDao.getByCourseIdAndStatusId(req.getCourseId(), StatusIdEnum.YES.getCode());
        if (ObjectUtil.isNull(course) && !StatusIdEnum.YES.getCode().equals(course.getStatusId())) {
            return Result.error("找不到课程信息");
        }

        ZoneCourse zoneCourse = dao.getZoneIdAndCourseId(req.getCourseId(), req.getZoneId());
        if (ObjectUtil.isNotNull(zoneCourse)) {
            return Result.error("已存在专区课程");
        }

        // 保存分区关联课程信息
        ZoneCourse result = new ZoneCourse();
        result.setCourseId(course.getId());
        result.setZoneId(req.getZoneId());
        result.setSort(1);
        int results = dao.save(result);
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.COURSE_SAVE_FAIL);
    }

    /**
     * 专区课程删除
     *
     * @param zoneCourseDeleteREQ
     * @return
     */
    public Result<Integer> delete(ZoneCourseDeleteREQ zoneCourseDeleteREQ) {
        if (StringUtils.isEmpty(zoneCourseDeleteREQ.getId())) {
            return Result.error("ID不能为空");
        }
        int results = dao.deleteById(zoneCourseDeleteREQ.getId());
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.COURSE_DELETE_FAIL);
    }

    /**
     * 专区课程更新
     *
     * @param zoneCourseUpdateREQ
     * @return
     */
    public Result<Integer> update(ZoneCourseUpdateREQ zoneCourseUpdateREQ) {
        if (StringUtils.isEmpty(zoneCourseUpdateREQ.getId())) {
            return Result.error("ID不能为空");
        }
        ZoneCourse record = BeanUtil.copyProperties(zoneCourseUpdateREQ, ZoneCourse.class);
        int results = dao.updateById(record);
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.COURSE_DELETE_FAIL);
    }

    /**
     * 专区课程查看
     *
     * @param zoneCourseViewREQ
     * @return
     */
    public Result<ZoneCourseViewRESQ> view(ZoneCourseViewREQ zoneCourseViewREQ) {
        if (StringUtils.isEmpty(zoneCourseViewREQ.getId())) {
            return Result.error("ID不能为空");
        }
        ZoneCourse record = dao.getById(zoneCourseViewREQ.getId());
        if (ObjectUtil.isNull(record)) {
            return Result.error("找不到专区信息");
        }
        return Result.success(BeanUtil.copyProperties(record, ZoneCourseViewRESQ.class));
    }

}
