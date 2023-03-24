package com.roncoo.education.course.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourseComment;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourseCommentExample;

import java.util.List;

/**
 * 课程评论 服务类
 *
 * @author wujing
 * @date 2023-03-24
 */
public interface UserCourseCommentDao {

    /**
     * 保存课程评论
     *
     * @param record 课程评论
     * @return 影响记录数
     */
    int save(UserCourseComment record);

    /**
     * 根据ID删除课程评论
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    int deleteById(Long id);

    /**
     * 修改课程评论
     *
     * @param record 课程评论
     * @return 影响记录数
     */
    int updateById(UserCourseComment record);

    /**
     * 根据ID获取课程评论
     *
     * @param id 主键ID
     * @return 课程评论
     */
    UserCourseComment getById(Long id);

    /**
     * 课程评论--分页查询
     *
     * @param pageCurrent 当前页
     * @param pageSize    分页大小
     * @param example     查询条件
     * @return 分页结果
     */
    Page<UserCourseComment> page(int pageCurrent, int pageSize, UserCourseCommentExample example);

    /**
     * 课程评论--条件列出
     *
     * @param example 查询条件
     * @return 课程评论列表
     */
    List<UserCourseComment> listByExample(UserCourseCommentExample example);

    /**
     * 课程评论--条件统计
     *
     * @param example 统计条件
     * @return 课程评论数量
     */
    int countByExample(UserCourseCommentExample example);

    List<UserCourseComment> listByCourseIdAndStatusId(Long courseId, Integer statusId);
}
