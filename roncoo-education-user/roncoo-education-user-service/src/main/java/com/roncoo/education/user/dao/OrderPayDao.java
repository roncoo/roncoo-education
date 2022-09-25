package com.roncoo.education.user.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.user.dao.impl.mapper.entity.OrderPay;
import com.roncoo.education.user.dao.impl.mapper.entity.OrderPayExample;

import java.util.List;

/**
 * 订单支付信息表 服务类
 *
 * @author wujing
 * @date 2022-09-06
 */
public interface OrderPayDao {

    /**
     * 保存订单支付信息表
     *
     * @param record 订单支付信息表
     * @return 影响记录数
     */
    int save(OrderPay record);

    /**
     * 根据ID删除订单支付信息表
     *
     * @param id 主键ID
     * @return 影响记录数
     */
    int deleteById(Long id);

    /**
     * 修改订单支付信息表
     *
     * @param record 订单支付信息表
     * @return 影响记录数
     */
    int updateById(OrderPay record);

    /**
     * 根据ID获取订单支付信息表
     *
     * @param id 主键ID
     * @return 订单支付信息表
     */
    OrderPay getById(Long id);

    /**
     * 订单支付信息表--分页查询
     *
     * @param pageCurrent 当前页
     * @param pageSize    分页大小
     * @param example     查询条件
     * @return 分页结果
     */
    Page<OrderPay> page(int pageCurrent, int pageSize, OrderPayExample example);

    /**
     * 订单支付信息表--条件列出
     *
     * @param example 查询条件
     * @return 订单支付信息表列表
     */
    List<OrderPay> listByExample(OrderPayExample example);

    /**
     * 订单支付信息表--条件统计
     *
     * @param example 统计条件
     * @return 订单支付信息表数量
     */
    int countByExample(OrderPayExample example);

    OrderPay getBySerialNumber(Long serialNumber);

    int updateOrderStatusByOrderNo(Long orderNo, Integer orderStatus);
}
