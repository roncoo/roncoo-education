package com.roncoo.education.course.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.dao.impl.mapper.entity.OrderPay;
import com.roncoo.education.course.dao.impl.mapper.entity.OrderPayExample;

public interface OrderPayDao {
    int save(OrderPay record);

    int deleteById(Long id);

    int updateById(OrderPay record);

    OrderPay getById(Long id);

    Page<OrderPay> listForPage(int pageCurrent, int pageSize, OrderPayExample example);

    /**
     * 根据订单编号查找订单支付信息
     *
     * @param orderNo
     * @return
     */
    OrderPay getByOrderNo(long orderNo);

    /**
     * 根据流水编号查找订单支付信息
     *
     * @param serialNumber
     * @return
     * @author wuyun
     */
    OrderPay getBySerialNumber(long serialNumber);
}
