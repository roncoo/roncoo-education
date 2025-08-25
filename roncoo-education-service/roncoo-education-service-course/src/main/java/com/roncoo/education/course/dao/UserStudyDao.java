package com.roncoo.education.course.dao;

import com.roncoo.education.common.base.page.Page;
import com.roncoo.education.course.dao.impl.mapper.entity.UserStudy;
import com.roncoo.education.course.dao.impl.mapper.entity.UserStudyExample;
import com.roncoo.education.course.service.admin.resp.AdminUserStudyStatResp;

import java.util.List;

/**
 * 资源学习记录 服务类
 *
 * @author wujing
 * @date 2022-09-03
 */
public interface UserStudyDao {

    /**
     * 保存资源学习记录
     *
     * @param record 资源学习记录
     * @return 影响记录数
     */
    int save(UserStudy record);

    /**
     * 根据ID删除资源学习记录
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    int deleteById(Long id);

    /**
     * 修改资源学习记录
     *
     * @param record 资源学习记录
     * @return 影响记录数
     */
    int updateById(UserStudy record);

    /**
     * 根据ID获取资源学习记录
     *
     * @param id 主键ID
     * @return 资源学习记录
     */
    UserStudy getById(Long id);

    /**
     * 资源学习记录--分页查询
     *
     * @param pageCurrent 当前页
     * @param pageSize    分页大小
     * @param example     查询条件
     * @return 分页结果
     */
    Page<UserStudy> page(int pageCurrent, int pageSize, UserStudyExample example);

    /**
     * 资源学习记录--条件列出
     *
     * @param example 查询条件
     * @return 资源学习记录列表
     */
    List<UserStudy> listByExample(UserStudyExample example);

    /**
     * 资源学习记录--条件统计
     *
     * @param example 统计条件
     * @return 资源学习记录数量
     */
    int countByExample(UserStudyExample example);

    /**
     * 获取
     *
     * @param periodId
     * @param userId
     * @return
     */
    UserStudy getByPeriodIdAndUserId(Long periodId, Long userId);

    /**
     * 获取指定课程的最新学习课时记录
     *
     * @param userId
     * @param courseIdList
     * @return
     */
    List<UserStudy> listByUserIdAndCourseIdsForMax(Long userId, List<Long> courseIdList);

    /**
     * 获取当前学习的课程的最新课时
     *
     * @param userId
     * @param courseId
     * @return
     */
    UserStudy getByCourseIdForLast(Long userId, Long courseId);

    /**
     * 获取指定课程的课时进度总和
     *
     * @param userId
     * @param courseIdList
     * @return
     */
    List<UserStudy> listByUserIdAndCourseIdsForSumProgress(Long userId, List<Long> courseIdList);

    /**
     * 获取指定课程的课时进度总和
     *
     * @param courseId
     * @param userIdList
     * @return
     */
    List<UserStudy> listByCourseIdAndUserIdsForSumProgress(Long courseId, List<Long> userIdList);

    /**
     * 获取集合
     *
     * @param userId
     * @param courseId
     * @return
     */
    List<UserStudy> listByUserIdAndCourseId(Long userId, Long courseId);

    /**
     * 删除
     *
     * @param periodId
     * @return
     */
    int deleteByPeriodId(Long periodId);


    AdminUserStudyStatResp stat(Long userId);
}
