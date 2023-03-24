package com.roncoo.education.course.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourseCollect;
import com.roncoo.education.course.dao.impl.mapper.entity.UserCourseCollectExample;

import java.util.List;

/**
 * 课程收藏 服务类
 *
 * @author wujing
 * @date 2023-03-24
 */
public interface UserCourseCollectDao {

    /**
     * 保存课程收藏
     *
     * @param record 课程收藏
     * @return 影响记录数
     */
    int save(UserCourseCollect record);

    /**
     * 根据ID删除课程收藏
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    int deleteById(Long id);

    /**
     * 修改课程收藏
     *
     * @param record 课程收藏
     * @return 影响记录数
     */
    int updateById(UserCourseCollect record);

    /**
     * 根据ID获取课程收藏
     *
     * @param id 主键ID
     * @return 课程收藏
     */
    UserCourseCollect getById(Long id);

    /**
     * 课程收藏--分页查询
     *
     * @param pageCurrent 当前页
     * @param pageSize    分页大小
     * @param example     查询条件
     * @return 分页结果
     */
    Page<UserCourseCollect> page(int pageCurrent, int pageSize, UserCourseCollectExample example);

    /**
     * 课程收藏--条件列出
     *
     * @param example 查询条件
     * @return 课程收藏列表
     */
    List<UserCourseCollect> listByExample(UserCourseCollectExample example);

    /**
     * 课程收藏--条件统计
     *
     * @param example 统计条件
     * @return 课程收藏数量
     */
    int countByExample(UserCourseCollectExample example);

    UserCourseCollect getByCouserIdAndUserId(Long courseId, Long userId);
}
