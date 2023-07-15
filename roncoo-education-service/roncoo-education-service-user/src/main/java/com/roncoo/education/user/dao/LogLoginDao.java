package com.roncoo.education.user.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.dao.impl.mapper.entity.LogLogin;
import com.roncoo.education.user.dao.impl.mapper.entity.LogLoginExample;
import com.roncoo.education.user.service.admin.resp.AdminStatLogin;

import java.util.List;

/**
 * 用户登录日志 服务类
 *
 * @author wujing
 * @date 2022-08-25
 */
public interface LogLoginDao {

    /**
     * 保存用户登录日志
     *
     * @param record 用户登录日志
     * @return 影响记录数
     */
    int save(LogLogin record);

    /**
     * 根据ID删除用户登录日志
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    int deleteById(Long id);

    /**
     * 修改用户登录日志
     *
     * @param record 用户登录日志
     * @return 影响记录数
     */
    int updateById(LogLogin record);

    /**
     * 根据ID获取用户登录日志
     *
     * @param id 主键ID
     * @return 用户登录日志
     */
    LogLogin getById(Long id);

    /**
     * 用户登录日志--分页查询
     *
     * @param pageCurrent 当前页
     * @param pageSize    分页大小
     * @param example     查询条件
     * @return 分页结果
     */
    Page<LogLogin> page(int pageCurrent, int pageSize, LogLoginExample example);

    /**
     * 用户登录日志--条件列出
     *
     * @param example 查询条件
     * @return 用户登录日志列表
     */
    List<LogLogin> listByExample(LogLoginExample example);

    /**
     * 用户登录日志--条件统计
     *
     * @param example 统计条件
     * @return 用户登录日志数量
     */
    int countByExample(LogLoginExample example);

    List<AdminStatLogin> statByDate(int date);
}
