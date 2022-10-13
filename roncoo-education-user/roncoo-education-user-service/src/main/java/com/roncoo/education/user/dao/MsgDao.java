package com.roncoo.education.user.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.dao.impl.mapper.entity.Msg;
import com.roncoo.education.user.dao.impl.mapper.entity.MsgExample;

import java.util.List;

/**
 * 站内信息表 服务类
 *
 * @author wujing
 * @date 2022-08-25
 */
public interface MsgDao {

    /**
     * 保存站内信息表
     *
     * @param record 站内信息表
     * @return 影响记录数
     */
    int save(Msg record);

    /**
     * 根据ID删除站内信息表
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    int deleteById(Long id);

    /**
     * 修改站内信息表
     *
     * @param record 站内信息表
     * @return 影响记录数
     */
    int updateById(Msg record);

    /**
     * 根据ID获取站内信息表
     *
     * @param id 主键ID
     * @return 站内信息表
     */
    Msg getById(Long id);

    /**
     * 站内信息表--分页查询
     *
     * @param pageCurrent 当前页
     * @param pageSize    分页大小
     * @param example     查询条件
     * @return 分页结果
     */
    Page<Msg> page(int pageCurrent, int pageSize, MsgExample example);

    /**
     * 站内信息表--条件列出
     *
     * @param example 查询条件
     * @return 站内信息表列表
     */
    List<Msg> listByExample(MsgExample example);

    /**
     * 站内信息表--条件统计
     *
     * @param example 统计条件
     * @return 站内信息表数量
     */
    int countByExample(MsgExample example);
}
