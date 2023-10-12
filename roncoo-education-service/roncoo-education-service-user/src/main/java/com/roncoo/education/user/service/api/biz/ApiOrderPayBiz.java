package com.roncoo.education.user.service.api.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.enums.BuyTypeEnum;
import com.roncoo.education.common.core.enums.OrderStatusEnum;
import com.roncoo.education.common.core.tools.JSUtil;
import com.roncoo.education.common.pay.PayFace;
import com.roncoo.education.common.pay.req.TradeNotifyReq;
import com.roncoo.education.common.pay.resp.TradeNotifyResp;
import com.roncoo.education.common.pay.util.TradeStatusEnum;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.feign.interfaces.IFeignUserCourse;
import com.roncoo.education.course.feign.interfaces.qo.UserCourseBindingQO;
import com.roncoo.education.system.feign.interfaces.IFeignSysConfig;
import com.roncoo.education.system.feign.interfaces.vo.PayConfig;
import com.roncoo.education.user.dao.OrderInfoDao;
import com.roncoo.education.user.dao.OrderPayDao;
import com.roncoo.education.user.dao.impl.mapper.entity.OrderInfo;
import com.roncoo.education.user.dao.impl.mapper.entity.OrderPay;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * API-订单支付信息表
 *
 * @author wujing
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ApiOrderPayBiz extends BaseBiz {

    @NotNull
    private final OrderPayDao dao;
    @NotNull
    private final OrderInfoDao orderInfoDao;
    @NotNull
    private final IFeignSysConfig feignSysConfig;
    @NotNull
    private final IFeignUserCourse feignUserCourse;

    @NotNull
    private final Map<String, PayFace> payFaceMap;

    @GlobalTransactional(rollbackFor = Exception.class)
    public String notify(HttpServletRequest request, Integer payModel, String payImpl) {
        // step1：获取支付通知参数
        TradeNotifyReq notifyParam = getTradeNotifyParam(request);
        // 支付模式
        notifyParam.setPayModel(payModel);
        // 获取支付配置
        getPayConfig(notifyParam);
        // 获取支付通道
        PayFace payFace = payFaceMap.get(payImpl);
        TradeNotifyResp resp = payFace.tradeNotify(notifyParam);
        log.info("回调通知处理={}", JSUtil.toJsonString(resp));

        if (resp.isSuccess() && resp.getTradeStatus().equals(TradeStatusEnum.SUCCESS.getCode())) {
            // 处理交易成功订单
            OrderPay orderPay = dao.getBySerialNumber(Long.valueOf(resp.getTradeOrderNo()));
            if (ObjectUtil.isNotEmpty(orderPay) && !orderPay.getOrderStatus().equals(OrderStatusEnum.SUCCESS.getCode())) {
                OrderInfo orderInfo = orderInfoDao.getByOrderNo(orderPay.getOrderNo());
                if (ObjectUtil.isNotEmpty(orderInfo) && !orderInfo.getOrderStatus().equals(OrderStatusEnum.SUCCESS.getCode())) {
                    // 更新支付订单
                    updateOrderPay(orderPay);
                    // 更新订单
                    updateOrderInfo(orderInfo);
                    // 课程绑定用户
                    feignUserCourse.binding(new UserCourseBindingQO().setCourseId(orderInfo.getCourseId()).setUserId(orderInfo.getUserId()).setBuyType(BuyTypeEnum.BUY.getCode()));
                }
            }
        }
        return resp.getReturnMsg();
    }

    private void updateOrderInfo(OrderInfo orderInfo) {
        OrderInfo info = new OrderInfo();
        info.setId(orderInfo.getId());
        info.setOrderStatus(OrderStatusEnum.SUCCESS.getCode());
        //info.setPayTime(LocalDateTime.now());
        orderInfoDao.updateById(info);
    }

    private void updateOrderPay(OrderPay orderPay) {
        OrderPay pay = new OrderPay();
        pay.setId(orderPay.getId());
        pay.setOrderStatus(OrderStatusEnum.SUCCESS.getCode());
        //pay.setPayTime(LocalDateTime.now());
        dao.updateById(pay);
    }

    private void getPayConfig(TradeNotifyReq req) {
        PayConfig payConfig = feignSysConfig.getPay();
        req.setAliPayConfig(payConfig.getAliPayConfig());
        req.setWxPayConfig(payConfig.getWxPayConfig());
    }

    /**
     * 获取交易通知参数
     *
     * @param request 通知请求
     * @return 交易通知参数
     */
    private TradeNotifyReq getTradeNotifyParam(HttpServletRequest request) {
        Map<String, String> headerMap = getHeaderParam(request);
        log.info("交易回调通知--header参数：{}", headerMap);

        // 获取Query参数
        Map<String, String> queryParamMap = getQueryParam(request);
        log.info("交易回调通知--query参数：{}", queryParamMap);

        // 获取body参数
        String bodyParam = getBodyParam(request);
        log.info("交易回调通知--body参数：{}", bodyParam);
        return new TradeNotifyReq().setHeaderMap(headerMap).setQueryParamMap(queryParamMap).setBodyParam(bodyParam);
    }

}
