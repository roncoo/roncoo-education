/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.course.service.callback.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.BaseBiz;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.OrderStatusEnum;
import com.roncoo.education.course.dao.CourseDao;
import com.roncoo.education.course.dao.OrderInfoDao;
import com.roncoo.education.course.dao.OrderPayDao;
import com.roncoo.education.course.dao.impl.mapper.entity.Course;
import com.roncoo.education.course.dao.impl.mapper.entity.OrderInfo;
import com.roncoo.education.course.dao.impl.mapper.entity.OrderPay;
import com.roncoo.education.course.service.api.bo.OrderInfoPayNotifyBO;
import com.roncoo.education.user.feign.interfaces.IFeignLecturer;
import com.roncoo.education.user.feign.interfaces.IFeignLecturerExt;
import com.roncoo.education.user.feign.interfaces.qo.LecturerExtQO;
import com.roncoo.education.user.feign.interfaces.vo.LecturerExtVO;
import com.roncoo.education.user.feign.interfaces.vo.LecturerVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;

/**
 * 订单信息表
 *
 * @author wujing123
 */
@Component
public class CallbackOrderBiz extends BaseBiz {

    @Autowired
    private OrderInfoDao orderInfoDao;
    @Autowired
    private OrderPayDao orderPayDao;
    @Autowired
    private CourseDao courseDao;

    @Autowired
    private IFeignLecturer bossLecturer;
    @Autowired
    private IFeignLecturerExt bossLecturerExt;

    /**
     * 订单异步通知接口
     *
     * @param orderInfoPayBO
     * @return
     * @author wuyun
     */
    @Transactional
    public Result<String> payNotify(OrderInfoPayNotifyBO notifyBO) {
        if (StringUtils.isEmpty(notifyBO.getSerialNumber())) {
            Result.error("传入的订单流水号不能为空!");
        }

        if (StringUtils.isEmpty(notifyBO.getOrderStatus())) {
            Result.error("传入的交易状态不能为空!");
        }

        // 根据订单交易流水号查找订单信息
        OrderPay orderPay = orderPayDao.getBySerialNumber(notifyBO.getSerialNumber());
        // 根据订单号查找订单信息
        OrderInfo orderInfo = orderInfoDao.getByOrderNo(orderPay.getOrderNo());

        if (StringUtils.isEmpty(orderPay)) {
            Result.error("根据传入的交易订单流水号,没找到对应的订单信息!");
        }
        if (StringUtils.isEmpty(orderInfo)) {
            Result.error("根据传入的交易订单号,没找到对应的订单信息!");
        }

        // 如果订单状态不是待支付状态证明订单已经处理过,不用再处理
        if (!OrderStatusEnum.WAIT.getCode().equals(orderPay.getOrderStatus())) {
            return Result.success("success");
        }

        // 订单状态为成功时处理
        if (OrderStatusEnum.SUCCESS.getCode().equals(notifyBO.getOrderStatus())) {
            // 处理课程信息
            return course(orderInfo, orderPay);
        } else if (OrderStatusEnum.FAIL.getCode().equals(notifyBO.getOrderStatus())) {
            // 更新订单信息
            orderInfo.setOrderStatus(OrderStatusEnum.FAIL.getCode());
            orderInfoDao.updateById(orderInfo);
            // 更新订单支付信息
            orderPay.setOrderStatus(OrderStatusEnum.FAIL.getCode());
            orderPayDao.updateById(orderPay);
        }

        // 这里是指处理成功
        return Result.success("success");
    }

    /**
     * 课程处理
     *
     * @param orderInfo
     * @param orderPay
     * @return
     * @author wuyun
     */
    private Result<String> course(OrderInfo orderInfo, OrderPay orderPay) {
        // 根据课程No查找课程信息
        Course course = courseDao.getById(orderInfo.getCourseId());
        if (StringUtils.isEmpty(course)) {
            Result.error("根据订单的课程编号,没找到对应的课程信息!");
        }

        // 根据讲师编号和机构编号查找讲师账户信息
        LecturerExtVO lecturerExtVO = bossLecturerExt.getByLecturerUserNo(course.getLecturerUserNo());
        if (StringUtils.isEmpty(lecturerExtVO)) {
            Result.error("找不到讲师账户信息!");
        }

        LecturerVO lecturerVO = bossLecturer.getByLecturerUserNo(course.getLecturerUserNo());
        if (ObjectUtil.isNull(lecturerVO)) {
            return Result.error("找不到讲师信息");
        }

        // 更新课程信息的购买人数
        course.setCountBuy(course.getCountBuy() + 1);
        courseDao.updateById(course);

        // 计算讲师分润
        orderInfo = countProfit(orderInfo, lecturerExtVO, lecturerVO.getLecturerProportion());
        orderInfo.setPayTime(new Date());
        orderInfo.setOrderStatus(OrderStatusEnum.SUCCESS.getCode());

        // 更新讲师账户信息
        updateLecturerExtVO(orderInfo, lecturerExtVO);

        // 更新订单信息
        orderInfoDao.updateById(orderInfo);

        // 更新订单支付信息
        orderPay.setOrderStatus(OrderStatusEnum.SUCCESS.getCode());
        orderPay.setPayTime(new Date());
        orderPayDao.updateById(orderPay);

        return Result.success("success");
    }

    /**
     * 计算处理讲师分润信息
     *
     * @param orderInfo
     * @param lecturerExtVO
     * @param lecturerProportion
     * @return
     * @author wuyun
     */
    private OrderInfo countProfit(OrderInfo orderInfo, LecturerExtVO lecturerExtVO, BigDecimal lecturerProportion) {
        // 讲师收入 = 订单价格x讲师分成比例
        BigDecimal lecturerProfit = orderInfo.getPricePaid().multiply(lecturerProportion).setScale(2, RoundingMode.DOWN);
        // 平台收入 = 实付金额 - 讲师收入
        BigDecimal platformIncome = orderInfo.getPricePaid().subtract(lecturerProfit).setScale(2, RoundingMode.DOWN);

        orderInfo.setLecturerProfit(lecturerProfit);
        orderInfo.setPlatformProfit(platformIncome);

        return orderInfo;
    }

    /**
     * 更新讲师账户信息
     *
     * @param orderInfo
     * @param lecturerAccoutVO
     * @author wuyun
     */
    private void updateLecturerExtVO(OrderInfo orderInfo, LecturerExtVO lecturerExtVO) {
        LecturerExtQO lecturerExtQO = new LecturerExtQO();
        lecturerExtQO.setLecturerUserNo(lecturerExtVO.getLecturerUserNo());
        lecturerExtQO.setTotalIncome(orderInfo.getLecturerProfit());
        lecturerExtQO.setEnableBalances(orderInfo.getLecturerProfit());
        bossLecturerExt.updateTotalIncomeByLecturerUserNo(lecturerExtQO);
    }

}
