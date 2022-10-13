package com.roncoo.education.user.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.dao.impl.mapper.entity.MsgUser;
import com.roncoo.education.user.dao.impl.mapper.entity.MsgUserExample;

import java.util.List;

/**
 * 站内信用户记录表 服务类
 *
 * @author wujing
 * @date 2022-08-25
 */
public interface MsgUserDao {

    /**
     * 保存站内信用户记录表
     *
     * @param record 站内信用户记录表
     * @return 影响记录数
     */
    int save(MsgUser record);

    /**
     * 根据ID删除站内信用户记录表
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    int deleteById(Long id);

    /**
     * 修改站内信用户记录表
     *
     * @param record 站内信用户记录表
     * @return 影响记录数
     */
    int updateById(MsgUser record);

    /**
     * 根据ID获取站内信用户记录表
     *
     * @param id 主键ID
     * @return 站内信用户记录表
     */
    MsgUser getById(Long id);

    /**
     * 站内信用户记录表--分页查询
     *
     * @param pageCurrent 当前页
     * @param pageSize    分页大小
     * @param example     查询条件
     * @return 分页结果
     */
    Page<MsgUser> page(int pageCurrent, int pageSize, MsgUserExample example);

    /**
     * 站内信用户记录表--条件列出
     *
     * @param example 查询条件
     * @return 站内信用户记录表列表
     */
    List<MsgUser> listByExample(MsgUserExample example);

    /**
     * 站内信用户记录表--条件统计
     *
     * @param example 统计条件
     * @return 站内信用户记录表数量
     */
    int countByExample(MsgUserExample example);
}
