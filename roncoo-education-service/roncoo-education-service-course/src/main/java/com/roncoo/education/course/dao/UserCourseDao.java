package com.roncoo.education.course.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourse;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourseExample;

import java.util.List;

/**
 * 课程用户关联表 服务类
 *
 * @author wujing
 * @date 2022-08-25
 */
public interface UserCourseDao {

    /**
     * 保存课程用户关联表
     *
     * @param record 课程用户关联表
     * @return 影响记录数
     */
    int save(UserCourse record);

    /**
     * 根据ID删除课程用户关联表
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    int deleteById(Long id);

    /**
     * 修改课程用户关联表
     *
     * @param record 课程用户关联表
     * @return 影响记录数
     */
    int updateById(UserCourse record);

    /**
     * 根据ID获取课程用户关联表
     *
     * @param id 主键ID
     * @return 课程用户关联表
     */
    UserCourse getById(Long id);

    /**
     * 课程用户关联表--分页查询
     *
     * @param pageCurrent 当前页
     * @param pageSize    分页大小
     * @param example     查询条件
     * @return 分页结果
     */
    Page<UserCourse> page(int pageCurrent, int pageSize, UserCourseExample example);

    /**
     * 课程用户关联表--条件列出
     *
     * @param example 查询条件
     * @return 课程用户关联表列表
     */
    List<UserCourse> listByExample(UserCourseExample example);

    /**
     * 课程用户关联表--条件统计
     *
     * @param example 统计条件
     * @return 课程用户关联表数量
     */
    int countByExample(UserCourseExample example);

    UserCourse getByCourseIdAndUserId(Long courseId, Long userId);
}
