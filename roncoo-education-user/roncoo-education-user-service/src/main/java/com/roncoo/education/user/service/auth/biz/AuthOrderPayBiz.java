package com.roncoo.education.user.service.auth.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.config.ThreadContext;
import com.roncoo.education.common.core.base.BaseException;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.*;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.core.tools.Constants;
import com.roncoo.education.common.core.tools.NOUtil;
import com.roncoo.education.common.pay.PayFace;
import com.roncoo.education.common.pay.req.TradeOrderReq;
import com.roncoo.education.common.pay.resp.TradeOrderResp;
import com.roncoo.education.common.pay.util.AliPayConfig;
import com.roncoo.education.common.pay.util.PayModelEnum;
import com.roncoo.education.common.pay.util.WxPayConfig;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.feign.interfaces.IFeignCourse;
import com.roncoo.education.course.feign.interfaces.vo.CourseViewVO;
import com.roncoo.education.system.feign.interfaces.IFeignSysConfig;
import com.roncoo.education.user.dao.OrderInfoDao;
import com.roncoo.education.user.dao.OrderPayDao;
import com.roncoo.education.user.dao.UsersDao;
import com.roncoo.education.user.dao.impl.mapper.entity.OrderInfo;
import com.roncoo.education.user.dao.impl.mapper.entity.OrderPay;
import com.roncoo.education.user.dao.impl.mapper.entity.Users;
import com.roncoo.education.user.service.auth.req.AuthOrderCountinuePayReq;
import com.roncoo.education.user.service.auth.req.AuthOrderPayReq;
import com.roncoo.education.user.service.auth.resp.AuthOrderPayResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * AUTH-订单支付信息表
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AuthOrderPayBiz extends BaseBiz {

    private static final String NOTIFYURL = "{domain}gateway/user/api/order/pay/notify";

    @NotNull
    private final OrderPayDao dao;
    @NotNull
    private final OrderInfoDao orderInfoDao;
    @NotNull
    private final UsersDao usersDao;

    @NotNull
    private final Map<String, PayFace> payFaceMap;
    @NotNull
    private final IFeignSysConfig feignSysConfig;
    @NotNull
    private final IFeignCourse feignCourse;

    @Transactional(rollbackFor = Exception.class)
    public Result<AuthOrderPayResp> pay(AuthOrderPayReq req) {
        if (ObjectUtil.isEmpty(req.getCourseId())) {
            return Result.error("请选择需要购买的课程");
        }
        // 校验课程是否已经购买
        OrderInfo orderInfo = orderInfoDao.getByUserAndCourseId(ThreadContext.userId(), req.getCourseId());
        if (orderInfo != null && orderInfo.getOrderStatus().equals(OrderStatusEnum.SUCCESS.getCode())) {
            return Result.error("该课程已经购买");
        }

        // 校验课程信息
        CourseViewVO courseViewVO = feignCourse.getById(req.getCourseId());
        if (!courseViewVO.getStatusId().equals(StatusIdEnum.YES.getCode()) || !courseViewVO.getIsPutaway().equals(PutawayEnum.UP.getCode())) {
            return Result.error("该课程不允许购买");
        }

        // 校验用户信息
        Users users = usersDao.getById(ThreadContext.userId());
        if (!users.getStatusId().equals(StatusIdEnum.YES.getCode())) {
            return Result.error("该用户已被禁用");
        }

        // 创建支付订单
        OrderPay orderPay = createOrderPay(courseViewVO.getRulingPrice(), courseViewVO.getCoursePrice(), req.getPayType(), req.getRemarkCus(), req.getChannelType());
        // 创建订单
        orderInfo = createOrderInfo(courseViewVO, users, orderPay);

        // 创建支付
        TradeOrderResp orderResp = createPay(req, courseViewVO, orderPay);
        if (orderResp.isSuccess()) {
            //返回支付信息
            AuthOrderPayResp resp = BeanUtil.copyProperties(orderInfo, AuthOrderPayResp.class);
            resp.setPayMessage(orderResp.getPayMessage());
            resp.setSerialNumber(orderPay.getSerialNumber());
            return Result.success(resp);
        }
        return Result.error("下单失败");
    }

    private TradeOrderResp createPay(AuthOrderPayReq req, CourseViewVO courseViewVO, OrderPay orderPay) {
        // 下单支付
        PayFace payFace = payFaceMap.get(PayTypeEnum.byCode(req.getPayType()).getImpl());
        if (ObjectUtil.isNull(payFace)) {
            log.error("该接口没实现，payType={}", req.getPayType());
            throw new BaseException("获取失败");
        }

        TradeOrderReq orderReq = new TradeOrderReq();
        // 获取支付配置
        getPayConfig(orderReq);

        orderReq.setTradeSerialNo(orderPay.getSerialNumber().toString());
        orderReq.setAmount(orderPay.getCoursePrice());
        orderReq.setGoodsName(courseViewVO.getCourseName());
        orderReq.setUserClientIp(req.getUserClientIp());
        //orderReq.setTimeExpire(null);
        orderReq.setNotifyUrl(getNotifyUrl());
        orderReq.setQuitUrl(req.getQuitUrl() + "?tradeSerialNo=" + orderReq.getTradeSerialNo());

        // 直连模式
        orderReq.setPayModel(PayModelEnum.DIRECT_SALES.getCode());
        return payFace.tradeOrder(orderReq);
    }

    private void getPayConfig(TradeOrderReq orderReq) {
        Map<String, String> payConfig = cacheRedis.getByJson(Constants.RedisPre.PAY + ConfigTypeEnum.PAY.getCode(), HashMap.class);
        if (ObjectUtil.isEmpty(payConfig)) {
            payConfig = feignSysConfig.getMapByConfigType(ConfigTypeEnum.PAY.getCode());
            cacheRedis.set(Constants.RedisPre.PAY + ConfigTypeEnum.PAY.getCode(), payConfig);
        }
        orderReq.setAliPayConfig(BeanUtil.objToBean(payConfig, AliPayConfig.class));
        orderReq.setWxPayConfig(BeanUtil.objToBean(payConfig, WxPayConfig.class));
    }

    /**
     * 继续支付
     */
    public Result<AuthOrderPayResp> continuePay(AuthOrderCountinuePayReq req) {
        if (ObjectUtil.isEmpty(req.getOrderNo())) {
            return Result.error("请确认订单号是否正确");
        }
        // 校验订单
        OrderInfo orderInfo = orderInfoDao.getByOrderNo(req.getOrderNo());
        if (ObjectUtil.isEmpty(orderInfo) || orderInfo.getOrderStatus().equals(OrderStatusEnum.SUCCESS.getCode())) {
            // 成功的订单，不需要支付
            return Result.error("请确认订单号是否正确");
        }

        // 校验课程信息
        CourseViewVO courseViewVO = feignCourse.getById(orderInfo.getCourseId());
        if (!courseViewVO.getStatusId().equals(StatusIdEnum.YES.getCode()) || !courseViewVO.getIsPutaway().equals(PutawayEnum.UP.getCode())) {
            return Result.error("该课程不允许购买");
        }

        // 校验用户信息
        Users users = usersDao.getById(ThreadContext.userId());
        if (!users.getStatusId().equals(StatusIdEnum.YES.getCode())) {
            return Result.error("该用户已被禁用");
        }

        // 每次支付都新增支付订单
        OrderPay orderPay = createOrderPay(orderInfo.getRulingPrice(), orderInfo.getCoursePrice(), req.getPayType(), req.getRemarkCus(), req.getChannelType());

        // 下单支付
        String payMessage = "";

        //返回支付信息
        AuthOrderPayResp resp = BeanUtil.copyProperties(orderInfo, AuthOrderPayResp.class);
        resp.setPayMessage(payMessage);
        resp.setSerialNumber(orderPay.getSerialNumber());
        return Result.success(resp);
    }

    /**
     * 创建订单
     */
    private OrderInfo createOrderInfo(CourseViewVO courseViewVO, Users users, OrderPay orderPay) {
        OrderInfo orderInfo = BeanUtil.copyProperties(orderPay, OrderInfo.class);
        orderInfo.setUserId(users.getId());
        orderInfo.setMobile(users.getMobile());
        orderInfo.setRegisterTime(users.getGmtCreate());
        orderInfo.setCourseId(courseViewVO.getId());
        orderInfoDao.save(orderInfo);
        return orderInfo;
    }

    /**
     * 创建支付订单
     */
    private OrderPay createOrderPay(BigDecimal rulingPrice, BigDecimal coursePrice, int payType, String remarkCus, int channelType) {
        OrderPay orderpay = new OrderPay();
        orderpay.setOrderNo(NOUtil.getOrderNo());
        orderpay.setSerialNumber(NOUtil.getSerialNumber());
        orderpay.setRulingPrice(rulingPrice);
        orderpay.setCoursePrice(coursePrice);
        orderpay.setPayType(payType);
        orderpay.setOrderStatus(OrderStatusEnum.WAIT.getCode());
        orderpay.setRemarkCus(remarkCus);
        orderpay.setPayTime(new Date());
        dao.save(orderpay);
        return orderpay;
    }

    private String getNotifyUrl() {
        String websiteDomain = cacheRedis.get(Constants.RedisPre.DOMAIN);
        if (!StringUtils.hasText(websiteDomain)) {
            websiteDomain = feignSysConfig.getByConfigKey("websiteDomain").getConfigValue();
            cacheRedis.set(Constants.RedisPre.DOMAIN, websiteDomain, 1, TimeUnit.HOURS);
        }
        return NOTIFYURL.replace("{domain}", websiteDomain);
    }
}
