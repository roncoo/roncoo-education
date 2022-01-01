package com.roncoo.education.course.dao;

import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.course.dao.impl.mapper.entity.OrderInfo;
import com.roncoo.education.course.dao.impl.mapper.entity.OrderInfoExample;
import com.roncoo.education.course.feign.interfaces.qo.OrderInfoQO;
import com.roncoo.education.course.feign.interfaces.vo.OrderReportVO;
import com.roncoo.education.course.service.pc.resq.CountIncomeRESQ;

import java.math.BigDecimal;
import java.util.List;

public interface OrderInfoDao {
    int save(OrderInfo record);

    int deleteById(Long id);

    int updateById(OrderInfo record);

    OrderInfo getById(Long id);

    Page<OrderInfo> listForPage(int pageCurrent, int pageSize, OrderInfoExample example);

    OrderInfo getByUserNoAndCourseId(Long userNo, Long courseId);

    /**
     * 根据订单编号查找订单信息
     *
     * @param orderNo
     * @return
     */
    OrderInfo getByOrderNo(long orderNo);

    /**
     * 统计时间段内该讲师的订单收益
     *
     * @param lecturerUserNo
     * @param date
     * @return
     * @author YZJ
     */
    BigDecimal sumLecturerUserNoAndData(Long lecturerUserNo, String date);

    /**
     * 统计时间段内的总订单数
     *
     * @param date
     * @return
     * @author wuyun
     */
    Integer sumByCountOrders(String date);

    /**
     * 统计时间段的总收入
     *
     * @param date
     * @return
     * @author wuyun
     */
    BigDecimal sumByPayTime(String date);

    /**
     * 统计订单收入情况
     *
     * @param qo
     * @author wuyun
     */
    CountIncomeRESQ countIncome(OrderInfoQO qo);

    /**
     * 订单信息汇总（导出报表）
     *
     * @param orderInfoQO
     * @author wuyun
     */
    List<OrderReportVO> listForReport(OrderInfoQO orderInfoQO);
}
