package com.roncoo.education.course.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.dao.impl.mapper.entity.Course;
import com.roncoo.education.course.dao.impl.mapper.entity.CourseExample;

import java.util.List;

/**
 * 课程信息 服务类
 *
 * @author wujing
 * @date 2022-08-25
 */
public interface CourseDao {

    /**
     * 保存课程信息
     *
     * @param record 课程信息
     * @return 影响记录数
     */
    int save(Course record);

    /**
     * 根据ID删除课程信息
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    int deleteById(Long id);

    /**
     * 修改课程信息
     *
     * @param record 课程信息
     * @return 影响记录数
     */
    int updateById(Course record);

    /**
     * 根据ID获取课程信息
     *
     * @param id 主键ID
     * @return 课程信息
     */
    Course getById(Long id);

    /**
     * 课程信息--分页查询
     *
     * @param pageCurrent 当前页
     * @param pageSize    分页大小
     * @param example     查询条件
     * @return 分页结果
     */
    Page<Course> page(int pageCurrent, int pageSize, CourseExample example);

    /**
     * 课程信息--条件列出
     *
     * @param example 查询条件
     * @return 课程信息列表
     */
    List<Course> listByExample(CourseExample example);

    /**
     * 课程信息--条件统计
     *
     * @param example 统计条件
     * @return 课程信息数量
     */
    int countByExample(CourseExample example);

    List<Course> listByIds(List<Long> courseIds);

    void addCountBuy(int countBuy, Long id);

    void addCountStudy(int countStudy, Long id);
}
