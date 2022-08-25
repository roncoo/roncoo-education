package com.roncoo.education.user.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.dao.impl.mapper.entity.LogSms;
import com.roncoo.education.user.dao.impl.mapper.entity.LogSmsExample;

import java.util.List;

/**
 * 用户短信发送日志 服务类
 *
 * @author wujing
 * @date 2022-08-25
 */
public interface LogSmsDao {

    /**
     * 保存用户短信发送日志
     *
     * @param record 用户短信发送日志
     * @return 影响记录数
     */
    int save(LogSms record);

    /**
     * 根据ID删除用户短信发送日志
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    int deleteById(Long id);

    /**
     * 修改用户短信发送日志
     *
     * @param record 用户短信发送日志
     * @return 影响记录数
     */
    int updateById(LogSms record);

    /**
     * 根据ID获取用户短信发送日志
     *
     * @param id 主键ID
     * @return 用户短信发送日志
     */
    LogSms getById(Long id);

    /**
     * 用户短信发送日志--分页查询
     *
     * @param pageCurrent 当前页
     * @param pageSize    分页大小
     * @param example     查询条件
     * @return 分页结果
     */
    Page<LogSms> page(int pageCurrent, int pageSize, LogSmsExample example);

    /**
     * 用户短信发送日志--条件列出
     *
     * @param example 查询条件
     * @return 用户短信发送日志列表
     */
    List<LogSms> listByExample(LogSmsExample example);

    /**
     * 用户短信发送日志--条件统计
     *
     * @param example 统计条件
     * @return 用户短信发送日志数量
     */
    int countByExample(LogSmsExample example);
}
