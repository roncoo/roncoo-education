package com.roncoo.education.user.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.dao.impl.mapper.entity.MsgTemplate;
import com.roncoo.education.user.dao.impl.mapper.entity.MsgTemplateExample;

import java.util.List;

/**
 * 消息模板 服务类
 *
 * @author wujing
 * @date 2022-08-25
 */
public interface MsgTemplateDao {

    /**
     * 保存消息模板
     *
     * @param record 消息模板
     * @return 影响记录数
     */
    int save(MsgTemplate record);

    /**
     * 根据ID删除消息模板
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    int deleteById(Long id);

    /**
     * 修改消息模板
     *
     * @param record 消息模板
     * @return 影响记录数
     */
    int updateById(MsgTemplate record);

    /**
     * 根据ID获取消息模板
     *
     * @param id 主键ID
     * @return 消息模板
     */
    MsgTemplate getById(Long id);

    /**
     * 消息模板--分页查询
     *
     * @param pageCurrent 当前页
     * @param pageSize    分页大小
     * @param example     查询条件
     * @return 分页结果
     */
    Page<MsgTemplate> page(int pageCurrent, int pageSize, MsgTemplateExample example);

    /**
     * 消息模板--条件列出
     *
     * @param example 查询条件
     * @return 消息模板列表
     */
    List<MsgTemplate> listByExample(MsgTemplateExample example);

    /**
     * 消息模板--条件统计
     *
     * @param example 统计条件
     * @return 消息模板数量
     */
    int countByExample(MsgTemplateExample example);
}
