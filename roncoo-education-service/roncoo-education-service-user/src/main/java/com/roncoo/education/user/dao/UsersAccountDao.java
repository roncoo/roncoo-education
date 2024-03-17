package com.roncoo.education.user.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.dao.impl.mapper.entity.UsersAccount;
import com.roncoo.education.user.dao.impl.mapper.entity.UsersAccountExample;

import java.util.List;

/**
 * 用户账户 服务类
 *
 * @author wujing
 * @date 2024-03-16
 */
public interface UsersAccountDao {

    /**
     * 保存用户账户
     *
     * @param record 用户账户
     * @return 影响记录数
     */
    int save(UsersAccount record);

    /**
     * 根据ID删除用户账户
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    int deleteById(Long id);

    /**
     * 修改用户账户
     *
     * @param record 用户账户
     * @return 影响记录数
     */
    int updateById(UsersAccount record);

    /**
     * 根据ID获取用户账户
     *
     * @param id 主键ID
     * @return 用户账户
     */
    UsersAccount getById(Long id);

    /**
     * 用户账户--分页查询
     *
     * @param pageCurrent 当前页
     * @param pageSize    分页大小
     * @param example     查询条件
     * @return 分页结果
     */
    Page<UsersAccount> page(int pageCurrent, int pageSize, UsersAccountExample example);

    /**
     * 用户账户--条件列出
     *
     * @param example 查询条件
     * @return 用户账户列表
     */
    List<UsersAccount> listByExample(UsersAccountExample example);

    /**
     * 用户账户--条件统计
     *
     * @param example 统计条件
     * @return 用户账户数量
     */
    int countByExample(UsersAccountExample example);

    UsersAccount getByUserId(Long userId);

    List<UsersAccount> listByUserIds(List<Long> userIds);
}
