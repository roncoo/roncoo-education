package com.roncoo.education.user.dao;

import com.roncoo.education.common.base.page.Page;
import com.roncoo.education.user.dao.impl.mapper.entity.UsersAccountConsume;
import com.roncoo.education.user.dao.impl.mapper.entity.UsersAccountConsumeExample;

import java.util.List;

/**
 * 用户账户消费记录 服务类
 *
 * @author wujing
 * @date 2024-03-17
 */
public interface UsersAccountConsumeDao {

    /**
     * 保存用户账户消费记录
     *
     * @param record 用户账户消费记录
     * @return 影响记录数
     */
    int save(UsersAccountConsume record);

    /**
     * 根据ID删除用户账户消费记录
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    int deleteById(Long id);

    /**
     * 修改用户账户消费记录
     *
     * @param record 用户账户消费记录
     * @return 影响记录数
     */
    int updateById(UsersAccountConsume record);

    /**
     * 根据ID获取用户账户消费记录
     *
     * @param id 主键ID
     * @return 用户账户消费记录
     */
    UsersAccountConsume getById(Long id);

    /**
     * 用户账户消费记录--分页查询
     *
     * @param pageCurrent 当前页
     * @param pageSize    分页大小
     * @param example     查询条件
     * @return 分页结果
     */
    Page<UsersAccountConsume> page(int pageCurrent, int pageSize, UsersAccountConsumeExample example);

    /**
     * 用户账户消费记录--条件列出
     *
     * @param example     查询条件
     * @return 用户账户消费记录列表
     */
    List<UsersAccountConsume> listByExample(UsersAccountConsumeExample example);

    /**
     * 用户账户消费记录--条件统计
     *
     * @param example     统计条件
     * @return 用户账户消费记录数量
     */
    int countByExample(UsersAccountConsumeExample example);
}
