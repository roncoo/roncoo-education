package com.roncoo.education.course.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.dao.impl.mapper.entity.UserStudy;
import com.roncoo.education.course.dao.impl.mapper.entity.UserStudyExample;

import java.util.List;

/**
 * 课程用户学习日志 服务类
 *
 * @author wujing
 * @date 2022-08-25
 */
public interface UserStudyDao {

    /**
     * 保存课程用户学习日志
     *
     * @param record 课程用户学习日志
     * @return 影响记录数
     */
    int save(UserStudy record);

    /**
     * 根据ID删除课程用户学习日志
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    int deleteById(Long id);

    /**
     * 修改课程用户学习日志
     *
     * @param record 课程用户学习日志
     * @return 影响记录数
     */
    int updateById(UserStudy record);

    /**
     * 根据ID获取课程用户学习日志
     *
     * @param id 主键ID
     * @return 课程用户学习日志
     */
    UserStudy getById(Long id);

    /**
     * 课程用户学习日志--分页查询
     *
     * @param pageCurrent 当前页
     * @param pageSize    分页大小
     * @param example     查询条件
     * @return 分页结果
     */
    Page<UserStudy> page(int pageCurrent, int pageSize, UserStudyExample example);

    /**
     * 课程用户学习日志--条件列出
     *
     * @param example     查询条件
     * @return 课程用户学习日志列表
     */
    List<UserStudy> listByExample(UserStudyExample example);

    /**
     * 课程用户学习日志--条件统计
     *
     * @param example     统计条件
     * @return 课程用户学习日志数量
     */
    int countByExample(UserStudyExample example);
}
