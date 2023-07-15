package com.roncoo.education.user.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.dao.impl.mapper.entity.OrderInfo;
import com.roncoo.education.user.dao.impl.mapper.entity.OrderInfoExample;

import java.util.List;

/**
 * 订单信息表 服务类
 *
 * @author wujing
 * @date 2022-09-06
 */
public interface OrderInfoDao {

    /**
     * 保存订单信息表
     *
     * @param record 订单信息表
     * @return 影响记录数
     */
    int save(OrderInfo record);

    /**
     * 根据ID删除订单信息表
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    int deleteById(Long id);

    /**
     * 修改订单信息表
     *
     * @param record 订单信息表
     * @return 影响记录数
     */
    int updateById(OrderInfo record);

    /**
     * 根据ID获取订单信息表
     *
     * @param id 主键ID
     * @return 订单信息表
     */
    OrderInfo getById(Long id);

    /**
     * 订单信息表--分页查询
     *
     * @param pageCurrent 当前页
     * @param pageSize    分页大小
     * @param example     查询条件
     * @return 分页结果
     */
    Page<OrderInfo> page(int pageCurrent, int pageSize, OrderInfoExample example);

    /**
     * 订单信息表--条件列出
     *
     * @param example 查询条件
     * @return 订单信息表列表
     */
    List<OrderInfo> listByExample(OrderInfoExample example);

    /**
     * 订单信息表--条件统计
     *
     * @param example 统计条件
     * @return 订单信息表数量
     */
    int countByExample(OrderInfoExample example);

    OrderInfo getByUserAndCourseId(Long userId, Long courseId);

    OrderInfo getByOrderNo(Long orderNo);
}
