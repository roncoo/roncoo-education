package com.roncoo.education.course.service.pc.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Page;
import com.roncoo.education.common.core.base.PageUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.ResultEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.core.tools.DateUtil;
import com.roncoo.education.course.dao.OrderInfoDao;
import com.roncoo.education.course.dao.OrderPayDao;
import com.roncoo.education.course.dao.impl.mapper.entity.OrderInfo;
import com.roncoo.education.course.dao.impl.mapper.entity.OrderInfoExample;
import com.roncoo.education.course.dao.impl.mapper.entity.OrderInfoExample.Criteria;
import com.roncoo.education.course.dao.impl.mapper.entity.OrderPay;
import com.roncoo.education.course.feign.interfaces.qo.OrderInfoQO;
import com.roncoo.education.course.service.pc.req.OrderInfoPageREQ;
import com.roncoo.education.course.service.pc.req.OrderInfoStatisticalREQ;
import com.roncoo.education.course.service.pc.req.OrderInfoUpdateREQ;
import com.roncoo.education.course.service.pc.req.OrderInfoViewREQ;
import com.roncoo.education.course.service.pc.resq.CountIncomeRESQ;
import com.roncoo.education.course.service.pc.resq.OrderInfoPageRESQ;
import com.roncoo.education.course.service.pc.resq.OrderInfoViewRESQ;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * 订单信息
 */
@Component
public class PcApiOrderInfoBiz {
    @Autowired
    private OrderInfoDao dao;
    @Autowired
    private OrderPayDao orderPayDao;

    public Result<Page<OrderInfoPageRESQ>> listForPage(OrderInfoPageREQ req) {
        OrderInfoExample example = new OrderInfoExample();
        Criteria c = example.createCriteria();
        if (req.getOrderNo() != null) {
            c.andOrderNoEqualTo(req.getOrderNo());
        }
        if (req.getTradeType() != null) {
            c.andTradeTypeEqualTo(req.getTradeType());
        }
        if (req.getPayType() != null) {
            c.andPayTypeEqualTo(req.getPayType());
        }
        if (req.getChannelType() != null) {
            c.andChannelTypeEqualTo(req.getChannelType());
        }
        if (req.getOrderStatus() != null) {
            c.andOrderStatusEqualTo(req.getOrderStatus());
        }
        if (StringUtils.hasText(req.getCourseName())) {
            c.andCourseNameEqualTo(req.getCourseName());
        }
        if (StringUtils.hasText(req.getLecturerName())) {
            c.andLecturerNameEqualTo(req.getLecturerName());
        }
        if (req.getCourseId() != null) {
            c.andCourseIdEqualTo(req.getCourseId());
        }
        if (req.getLecturerUserNo() != null) {
            c.andLecturerUserNoEqualTo(req.getLecturerUserNo());
        }
        if (StringUtils.hasText(req.getMobile())) {
            c.andMobileEqualTo(req.getMobile());
        }
        if (StringUtils.hasText(req.getRemark())) {
            c.andRemarkEqualTo(req.getRemark());
        }
        if (StringUtils.hasText(req.getBeginPayTime())) {
            c.andPayTimeGreaterThanOrEqualTo(DateUtil.parseDate(req.getBeginPayTime(), "yyyy-MM-dd"));
        }
        if (StringUtils.hasText(req.getEndPayTime())) {
            c.andPayTimeLessThanOrEqualTo(DateUtil.addDate(DateUtil.parseDate(req.getEndPayTime(), "yyyy-MM-dd"), 1));
        }
        example.setOrderByClause("order_status asc , id desc ");
        Page<OrderInfo> page = dao.listForPage(req.getPageCurrent(), req.getPageSize(), example);
        return Result.success(PageUtil.transform(page, OrderInfoPageRESQ.class));
    }

    public Result<OrderInfoViewRESQ> view(OrderInfoViewREQ req) {
        if (StringUtils.isEmpty(req.getId())) {
            return Result.error("ID不能为空");
        }
        OrderInfo orderInfo = dao.getById(req.getId());
        if (ObjectUtil.isNull(orderInfo)) {
            return Result.error("找不到订单信息");
        }
        OrderInfoViewRESQ resq = BeanUtil.copyProperties(orderInfo, OrderInfoViewRESQ.class);
        // 根据订单编号查找订单支付信息
        OrderPay orderPay = orderPayDao.getByOrderNo(resq.getOrderNo());
        resq.setSerialNumber(orderPay.getSerialNumber());
        return Result.success(resq);
    }

    public Result<Integer> update(OrderInfoUpdateREQ req) {
        if (StringUtils.isEmpty(req.getId())) {
            return Result.error("ID不能为空");
        }
        OrderInfo orderInfo = dao.getById(req.getId());
        if (ObjectUtil.isNull(orderInfo)) {
            return Result.error("找不到订单信息");
        }
        OrderInfo record = BeanUtil.copyProperties(req, OrderInfo.class);
        int results = dao.updateById(record);
        if (results > 0) {
            return Result.success(results);
        }
        return Result.error(ResultEnum.COURSE_UPDATE_FAIL);
    }

    public Result<CountIncomeRESQ> statistical(OrderInfoStatisticalREQ req) {
        CountIncomeRESQ resq = dao.countIncome(BeanUtil.copyProperties(req, OrderInfoQO.class));
        if (resq == null) {
            return Result.error("统计失败");
        }
        return Result.success(resq);
    }

}
