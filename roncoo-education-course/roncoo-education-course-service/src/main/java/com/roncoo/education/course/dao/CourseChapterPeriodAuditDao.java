package com.roncoo.education.course.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterPeriodAudit;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterPeriodAuditExample;

import java.util.List;

public interface CourseChapterPeriodAuditDao {
    int save(CourseChapterPeriodAudit record);

    int deleteById(Long id);

    int updateById(CourseChapterPeriodAudit record);

    CourseChapterPeriodAudit getById(Long id);

    Page<CourseChapterPeriodAudit> listForPage(int pageCurrent, int pageSize, CourseChapterPeriodAuditExample example);

    /**
     * 根据课程ID查询课时信息
     *
     * @param CourseId
     */
    List<CourseChapterPeriodAudit> listByCourseId(Long CourseId);

    /**
     * 根据章节ID修改课时排序
     *
     * @param sort
     * @param periodId
     */
    int updateSortByPeriodId(int sort, Long periodId);

    /**
     * 根据课时ID、是否存在视频(1存在，0否)查询课时审核信息
     *
     * @param isVideo
     * @param periodId
     */
    List<CourseChapterPeriodAudit> listByIsVideoAndPeriodId(String isVideo, Long periodId);

    /**
     * 根据课时ID、是否存在文档(1存在，0否)查询课时审核信息
     *
     * @param isDoc
     * @param periodId
     */
    List<CourseChapterPeriodAudit> listByIsDocAndPeriodId(Integer isDoc, Long periodId);

    /**
     * 根据视频编号查询课时审核信息
     *
     * @param videoNo
     * @author wuyun
     */
    CourseChapterPeriodAudit getByVideoNo(Long videoNo);

    /**
     * 根据课程编号删除课时信息
     *
     * @param courseId
     * @return int
     * @author wuyun
     */
    int deleteByCourseId(Long courseId);

    /**
     * 根据章节ID查询可用的课时信息集合
     *
     * @param chapterId
     * @return statusId
     * @author wuyun
     */
    List<CourseChapterPeriodAudit> listByChapterIdAndStatusId(Long chapterId, Integer statusId);

    /**
     * 根据视频编号获取课时信息
     *
     * @param videoNo
     * @author kyh
     */
    List<CourseChapterPeriodAudit> listByVideoNo(Long videoNo);
}
