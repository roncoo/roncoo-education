package com.roncoo.education.course.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapter;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseChapterExample;

import java.util.List;

/**
 * 章节信息 服务类
 *
 * @author wujing
 * @date 2022-08-25
 */
public interface CourseChapterDao {

    /**
     * 保存章节信息
     *
     * @param record 章节信息
     * @return 影响记录数
     */
    int save(CourseChapter record);

    /**
     * 根据ID删除章节信息
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    int deleteById(Long id);

    /**
     * 修改章节信息
     *
     * @param record 章节信息
     * @return 影响记录数
     */
    int updateById(CourseChapter record);

    /**
     * 根据ID获取章节信息
     *
     * @param id 主键ID
     * @return 章节信息
     */
    CourseChapter getById(Long id);

    /**
     * 章节信息--分页查询
     *
     * @param pageCurrent 当前页
     * @param pageSize    分页大小
     * @param example     查询条件
     * @return 分页结果
     */
    Page<CourseChapter> page(int pageCurrent, int pageSize, CourseChapterExample example);

    /**
     * 章节信息--条件列出
     *
     * @param example 查询条件
     * @return 章节信息列表
     */
    List<CourseChapter> listByExample(CourseChapterExample example);

    /**
     * 章节信息--条件统计
     *
     * @param example 统计条件
     * @return 章节信息数量
     */
    int countByExample(CourseChapterExample example);

    List<CourseChapter> listByCourseId(Long courseId);

    List<CourseChapter> listByCourseIdAndStatusId(Long id, Integer statusId);
}
