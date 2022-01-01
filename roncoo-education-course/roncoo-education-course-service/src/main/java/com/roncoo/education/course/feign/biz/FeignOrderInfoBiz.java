package com.roncoo.education.course.feign.biz;

import cn.hutool.core.collection.CollectionUtil;
import com.roncoo.education.common.core.base.BaseBiz;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.enums.OrderStatusEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.core.tools.DateUtil;
import com.roncoo.education.course.dao.OrderInfoDao;
import com.roncoo.education.course.dao.OrderPayDao;
import com.roncoo.education.course.dao.impl.mapper.entity.OrderInfo;
import com.roncoo.education.course.dao.impl.mapper.entity.OrderInfoExample;
import com.roncoo.education.course.dao.impl.mapper.entity.OrderInfoExample.Criteria;
import com.roncoo.education.course.dao.impl.mapper.entity.OrderPay;
import com.roncoo.education.course.dao.impl.mapper.entity.OrderPayExample;
import com.roncoo.education.course.feign.interfaces.qo.OrderEchartsQO;
import com.roncoo.education.course.feign.interfaces.qo.OrderInfoQO;
import com.roncoo.education.course.feign.interfaces.vo.CountIncomeVO;
import com.roncoo.education.course.feign.interfaces.vo.OrderEchartsVO;
import com.roncoo.education.course.feign.interfaces.vo.OrderInfoVO;
import com.roncoo.education.course.feign.interfaces.vo.OrderReportVO;
import com.roncoo.education.course.service.pc.resq.CountIncomeRESQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 订单信息表
 *
 * @author wujing
 */
@Component
public class FeignOrderInfoBiz extends BaseBiz {

    @Autowired
    private OrderInfoDao dao;
    @Autowired
    private OrderPayDao orderPayDao;

    public Page<OrderInfoVO> listForPage(OrderInfoQO qo) {
        OrderInfoExample example = new OrderInfoExample();
        Criteria c = example.createCriteria();
        if (qo.getOrderNo() != null) {
            c.andOrderNoEqualTo(qo.getOrderNo());
        }
        if (qo.getTradeType() != null) {
            c.andTradeTypeEqualTo(qo.getTradeType());
        }
        if (qo.getPayType() != null) {
            c.andPayTypeEqualTo(qo.getPayType());
        }
        if (qo.getChannelType() != null) {
            c.andChannelTypeEqualTo(qo.getChannelType());
        }
        if (qo.getOrderStatus() != null) {
            c.andOrderStatusEqualTo(qo.getOrderStatus());
        }
        if (StringUtils.hasText(qo.getCourseName())) {
            c.andCourseNameEqualTo(qo.getCourseName());
        }
        if (StringUtils.hasText(qo.getLecturerName())) {
            c.andLecturerNameEqualTo(qo.getLecturerName());
        }
        if (qo.getCourseId() != null) {
            c.andCourseIdEqualTo(qo.getCourseId());
        }
        if (qo.getLecturerUserNo() != null) {
            c.andLecturerUserNoEqualTo(qo.getLecturerUserNo());
        }
        if (StringUtils.hasText(qo.getMobile())) {
            c.andMobileEqualTo(qo.getMobile());
        }
        if (StringUtils.hasText(qo.getRemark())) {
            c.andRemarkEqualTo(qo.getRemark());
        }
        if (StringUtils.hasText(qo.getBeginPayTime())) {
            c.andPayTimeGreaterThanOrEqualTo(DateUtil.parseDate(qo.getBeginPayTime(), "yyyy-MM-dd"));
        }
        if (StringUtils.hasText(qo.getEndPayTime())) {
            c.andPayTimeLessThanOrEqualTo(DateUtil.addDate(DateUtil.parseDate(qo.getEndPayTime(), "yyyy-MM-dd"), 1));
        }
        example.setOrderByClause(" order_status asc , id desc ");
        Page<OrderInfo> page = dao.listForPage(qo.getPageCurrent(), qo.getPageSize(), example);
        return PageUtil.transform(page, OrderInfoVO.class);
    }

    public int save(OrderInfoQO qo) {
        OrderInfo record = BeanUtil.copyProperties(qo, OrderInfo.class);
        return dao.save(record);
    }

    public int deleteById(Long id) {
        return dao.deleteById(id);
    }

    public OrderInfoVO getById(Long id) {
        OrderInfo record = dao.getById(id);
        OrderInfoVO orderInfoVO = BeanUtil.copyProperties(record, OrderInfoVO.class);

        // 根据订单编号查找订单支付信息
        OrderPay orderPay = orderPayDao.getByOrderNo(orderInfoVO.getOrderNo());
        orderInfoVO.setSerialNumber(orderPay.getSerialNumber());

        return orderInfoVO;
    }

    public int updateById(OrderInfoQO qo) {
        OrderInfo record = BeanUtil.copyProperties(qo, OrderInfo.class);
        return dao.updateById(record);
    }

    /**
     * 订单信息汇总（导出报表）
     *
     * @param orderInfoQO
     * @author wuyun
     */
    public List<OrderReportVO> listForReport(OrderInfoQO orderInfoQO) {
        return dao.listForReport(orderInfoQO);
    }

    /**
     * 统计时间段的总订单数
     *
     * @param orderEchartsQO
     * @return
     * @author wuyun
     */
    public List<OrderEchartsVO> sumByCountOrders(OrderEchartsQO orderEchartsQO) {
        List<OrderEchartsVO> list = new ArrayList<>();
        List<Integer> countOrders = new ArrayList<>();
        OrderEchartsVO vo = new OrderEchartsVO();
        for (String date : orderEchartsQO.getDateList()) {
            Integer sum = dao.sumByCountOrders(date);
            countOrders.add(sum);
        }
        vo.setCountOrders(countOrders);
        list.add(vo);
        return list;
    }

    /**
     * 统计时间段的总收入
     *
     * @param orderEchartsQO
     * @return
     * @author wuyun
     */
    public List<OrderEchartsVO> sumByPayTime(OrderEchartsQO orderEchartsQO) {
        List<OrderEchartsVO> list = new ArrayList<>();
        List<BigDecimal> countPaidPrice = new ArrayList<>();
        OrderEchartsVO vo = new OrderEchartsVO();
        for (String date : orderEchartsQO.getDateList()) {
            BigDecimal sum = dao.sumByPayTime(date);
            countPaidPrice.add(sum);
        }
        vo.setCountPaidPrice(countPaidPrice);
        list.add(vo);
        return list;
    }

    /**
     * 统计订单收入情况
     *
     * @author wuyun
     */
    public CountIncomeVO countIncome(OrderInfoQO qo) {
        CountIncomeRESQ resq = dao.countIncome(qo);
        return BeanUtil.copyProperties(resq, CountIncomeVO.class);
    }

    /**
     * 1小时后如果订单不支付，就关闭订单和标记订单支付日志，每次处理10条数据
     *
     * @return
     * @author wuyun
     */
    public int handleScheduledTasks() {
        // 1.订单信息的处理
        OrderInfoExample example = new OrderInfoExample();
        Criteria c = example.createCriteria();
        c.andOrderStatusEqualTo(OrderStatusEnum.WAIT.getCode());
        c.andGmtCreateLessThan(new Date(System.currentTimeMillis() - 3600000L));
        example.setOrderByClause(" id desc ");
        Page<OrderInfo> page = dao.listForPage(1, 10, example);
        if (CollectionUtil.isNotEmpty(page.getList())) {
            for (OrderInfo orderInfo : page.getList()) {
                OrderInfo argOrderInfo = new OrderInfo();
                argOrderInfo.setId(orderInfo.getId());
                argOrderInfo.setOrderStatus(OrderStatusEnum.CLOSE.getCode());
                argOrderInfo.setRemark("系统自动关闭该订单");
                dao.updateById(argOrderInfo);
            }
        }

        // 2.订单支付的处理
        OrderPayExample orderPayExample = new OrderPayExample();
        OrderPayExample.Criteria orderPayCriteria = orderPayExample.createCriteria();
        orderPayCriteria.andOrderStatusEqualTo(OrderStatusEnum.WAIT.getCode());
        orderPayCriteria.andGmtCreateLessThan(new Date(System.currentTimeMillis() - 3600000L));
        Page<OrderPay> orderPayPage = orderPayDao.listForPage(1, 10, orderPayExample);
        if (CollectionUtil.isNotEmpty(orderPayPage.getList())) {
            for (OrderPay orderPay : orderPayPage.getList()) {
                OrderPay argOrderPay = new OrderPay();
                argOrderPay.setId(orderPay.getId());
                argOrderPay.setOrderStatus(OrderStatusEnum.CLOSE.getCode());
                orderPayDao.updateById(argOrderPay);
            }
        }
        return 1;
    }

}
