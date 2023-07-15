package com.roncoo.education.user.service.auth.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.config.ThreadContext;
import com.roncoo.education.common.core.base.BaseException;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.OrderStatusEnum;
import com.roncoo.education.common.core.enums.PayTypeEnum;
import com.roncoo.education.common.core.enums.PutawayEnum;
import com.roncoo.education.common.core.enums.StatusIdEnum;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.core.tools.NOUtil;
import com.roncoo.education.common.pay.PayFace;
import com.roncoo.education.common.pay.req.TradeOrderReq;
import com.roncoo.education.common.pay.resp.TradeOrderResp;
import com.roncoo.education.common.pay.util.PayModelEnum;
import com.roncoo.education.common.service.BaseBiz;
import com.roncoo.education.course.feign.interfaces.IFeignCourse;
import com.roncoo.education.course.feign.interfaces.vo.CourseViewVO;
import com.roncoo.education.system.feign.interfaces.IFeignSysConfig;
import com.roncoo.education.system.feign.interfaces.vo.PayConfig;
import com.roncoo.education.user.dao.OrderInfoDao;
import com.roncoo.education.user.dao.OrderPayDao;
import com.roncoo.education.user.dao.UsersDao;
import com.roncoo.education.user.dao.impl.mapper.entity.OrderInfo;
import com.roncoo.education.user.dao.impl.mapper.entity.OrderPay;
import com.roncoo.education.user.dao.impl.mapper.entity.Users;
import com.roncoo.education.user.service.auth.req.AuthOrderCancelReq;
import com.roncoo.education.user.service.auth.req.AuthOrderCountinuePayReq;
import com.roncoo.education.user.service.auth.req.AuthOrderPayReq;
import com.roncoo.education.user.service.auth.resp.AuthOrderPayResp;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

/**
 * AUTH-订单支付信息表
 *
 * @author wujing
 */
@Component
@RequiredArgsConstructor
public class AuthOrderPayBiz extends BaseBiz {

    private static final String NOTIFYURL = "{domain}gateway/user/api/order/pay/notify/{payModel}/{payImpl}";

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
        OrderPay orderPay = createOrderPay(courseViewVO.getRulingPrice(), courseViewVO.getCoursePrice(), req.getPayType(), NOUtil.getOrderNo(), req.getRemarkCus());
        // 创建订单
        orderInfo = createOrderInfo(courseViewVO, users, orderPay);

        // 创建支付
        TradeOrderResp orderResp = createPay(req.getPayType(), req.getUserClientIp(), req.getQuitUrl(), courseViewVO, orderPay);
        if (orderResp.isSuccess()) {
            //返回支付信息
            AuthOrderPayResp resp = BeanUtil.copyProperties(orderInfo, AuthOrderPayResp.class);
            resp.setPayMessage(orderResp.getPayMessage());
            resp.setSerialNumber(orderPay.getSerialNumber());
            return Result.success(resp);
        }
        return Result.error("下单失败");
    }

    /**
     * 继续支付
     */
    @Transactional(rollbackFor = Exception.class)
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
        OrderPay orderPay = createOrderPay(orderInfo.getRulingPrice(), orderInfo.getCoursePrice(), req.getPayType(), orderInfo.getOrderNo(), orderInfo.getRemarkCus());

        // 创建支付
        TradeOrderResp orderResp = createPay(req.getPayType(), req.getUserClientIp(), req.getQuitUrl(), courseViewVO, orderPay);
        if (orderResp.isSuccess()) {
            //返回支付信息
            AuthOrderPayResp resp = BeanUtil.copyProperties(orderInfo, AuthOrderPayResp.class);
            resp.setPayMessage(orderResp.getPayMessage());
            resp.setSerialNumber(orderPay.getSerialNumber());
            return Result.success(resp);
        }
        return Result.error("下单失败");
    }

    @Transactional(rollbackFor = Exception.class)
    public Result<String> cancel(AuthOrderCancelReq req) {
        OrderInfo orderInfo = orderInfoDao.getByOrderNo(req.getOrderNo());
        if (ObjectUtil.isEmpty(orderInfo) || orderInfo.getOrderStatus().equals(OrderStatusEnum.SUCCESS.getCode())) {
            // 成功的订单，不能操作
            return Result.error("请确认订单号是否正确");
        }
        orderInfo.setOrderStatus(OrderStatusEnum.CLOSE.getCode());
        orderInfoDao.updateById(orderInfo);
        return Result.success("操作成功");
    }

    private TradeOrderResp createPay(Integer payType, String userClientIp, String quitUrl, CourseViewVO courseViewVO, OrderPay orderPay) {
        // 下单支付
        String impl = PayTypeEnum.byCode(payType).getImpl();
        PayFace payFace = payFaceMap.get(impl);
        if (ObjectUtil.isNull(payFace)) {
            log.error("该接口没实现，payType={}", payType);
            throw new BaseException("获取失败");
        }

        TradeOrderReq orderReq = new TradeOrderReq();
        // 获取支付配置
        getPayConfig(orderReq);

        // 直连模式
        orderReq.setPayModel(PayModelEnum.DIRECT_SALES.getCode());
        orderReq.setTradeSerialNo(orderPay.getSerialNumber().toString());
        orderReq.setAmount(orderPay.getCoursePrice());
        orderReq.setGoodsName(courseViewVO.getCourseName());
        orderReq.setUserClientIp(userClientIp);
        orderReq.setNotifyUrl(getNotifyUrl(orderReq.getPayModel(), impl));
        if (StringUtils.hasText(quitUrl)) {
            orderReq.setQuitUrl(quitUrl + "?tradeSerialNo=" + orderReq.getTradeSerialNo());
        }
        return payFace.tradeOrder(orderReq);
    }

    private void getPayConfig(TradeOrderReq orderReq) {
        PayConfig payConfig = feignSysConfig.getPay();
        orderReq.setAliPayConfig(payConfig.getAliPayConfig());
        orderReq.setWxPayConfig(payConfig.getWxPayConfig());
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
    private OrderPay createOrderPay(BigDecimal rulingPrice, BigDecimal coursePrice, int payType, Long orderNo, String remarkCus) {
        OrderPay orderpay = new OrderPay();
        orderpay.setOrderNo(orderNo);
        orderpay.setSerialNumber(NOUtil.getSerialNumber());
        orderpay.setRulingPrice(rulingPrice);
        orderpay.setCoursePrice(coursePrice);
        orderpay.setPayType(payType);
        orderpay.setOrderStatus(OrderStatusEnum.WAIT.getCode());
        orderpay.setRemarkCus(remarkCus);
        orderpay.setPayTime(LocalDateTime.now());
        dao.save(orderpay);
        return orderpay;
    }

    private String getNotifyUrl(Integer payModel, String impl) {
        return NOTIFYURL.replace("{domain}", feignSysConfig.getSys().getWebsiteDomain()).replace("{payModel}", payModel.toString()).replace("{payImpl}", impl);
    }
}
