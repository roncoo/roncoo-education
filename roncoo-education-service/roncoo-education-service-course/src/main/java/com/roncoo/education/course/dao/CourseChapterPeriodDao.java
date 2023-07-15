package com.roncoo.education.course.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterPeriod;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterPeriodExample;

import java.util.List;

/**
 * 课时信息 服务类
 *
 * @author wujing
 * @date 2022-08-25
 */
public interface CourseChapterPeriodDao {

    /**
     * 保存课时信息
     *
     * @param record 课时信息
     * @return 影响记录数
     */
    int save(CourseChapterPeriod record);

    /**
     * 根据ID删除课时信息
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    int deleteById(Long id);

    /**
     * 修改课时信息
     *
     * @param record 课时信息
     * @return 影响记录数
     */
    int updateById(CourseChapterPeriod record);

    /**
     * 根据ID获取课时信息
     *
     * @param id 主键ID
     * @return 课时信息
     */
    CourseChapterPeriod getById(Long id);

    /**
     * 课时信息--分页查询
     *
     * @param pageCurrent 当前页
     * @param pageSize    分页大小
     * @param example     查询条件
     * @return 分页结果
     */
    Page<CourseChapterPeriod> page(int pageCurrent, int pageSize, CourseChapterPeriodExample example);

    /**
     * 课时信息--条件列出
     *
     * @param example 查询条件
     * @return 课时信息列表
     */
    List<CourseChapterPeriod> listByExample(CourseChapterPeriodExample example);

    /**
     * 课时信息--条件统计
     *
     * @param example 统计条件
     * @return 课时信息数量
     */
    int countByExample(CourseChapterPeriodExample example);

    List<CourseChapterPeriod> listByCourseIdAndStatusId(Long courseId, Integer statusId);

    List<CourseChapterPeriod> listByChapterIds(List<Long> chapterIds);

    List<CourseChapterPeriod> listByCourseIds(List<Long> courseIdList);

    List<CourseChapterPeriod> listByResourceId(Long resourceId);

    List<CourseChapterPeriod> listByChapterId(Long id);
}
