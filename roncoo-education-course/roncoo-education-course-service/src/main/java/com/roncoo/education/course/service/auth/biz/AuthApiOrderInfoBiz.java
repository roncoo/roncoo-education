package com.roncoo.education.course.service.auth.biz;

import cn.hutool.core.util.ObjectUtil;
import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.AxisType;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.series.Line;
import com.roncoo.education.common.core.base.*;
import com.roncoo.education.common.core.config.SystemUtil;
import com.roncoo.education.common.core.enums.*;
import com.roncoo.education.common.core.pay.PayUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.core.tools.DateUtil;
import com.roncoo.education.common.core.tools.NOUtil;
import com.roncoo.education.course.dao.CourseDao;
import com.roncoo.education.course.dao.OrderInfoDao;
import com.roncoo.education.course.dao.OrderPayDao;
import com.roncoo.education.course.dao.impl.mapper.entity.Course;
import com.roncoo.education.course.dao.impl.mapper.entity.OrderInfo;
import com.roncoo.education.course.dao.impl.mapper.entity.OrderInfoExample;
import com.roncoo.education.course.dao.impl.mapper.entity.OrderInfoExample.Criteria;
import com.roncoo.education.course.dao.impl.mapper.entity.OrderPay;
import com.roncoo.education.course.service.api.bo.OrderInfoCloseBO;
import com.roncoo.education.course.service.auth.bo.*;
import com.roncoo.education.course.service.auth.dto.*;
import com.roncoo.education.system.feign.interfaces.IFeignSys;
import com.roncoo.education.system.feign.interfaces.vo.SysVO;
import com.roncoo.education.user.feign.interfaces.IFeignLecturer;
import com.roncoo.education.user.feign.interfaces.IFeignUserExt;
import com.roncoo.education.user.feign.interfaces.vo.LecturerVO;
import com.roncoo.education.user.feign.interfaces.vo.UserExtVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 订单信息表
 *
 * @author wujing
 */
@Component
public class AuthApiOrderInfoBiz extends BaseBiz {

    @Autowired
    private OrderInfoDao orderInfoDao;
    @Autowired
    private OrderPayDao orderPayDao;
    @Autowired
    private CourseDao courseDao;

    @Autowired
    private IFeignSys bossSys;
    @Autowired
    private IFeignUserExt bossUserExt;
    @Autowired
    private IFeignLecturer bossLecturer;

    /**
     * 订单列表接口
     *
     * @param authOrderInfoListBO
     * @return
     */
    public Result<Page<AuthOrderInfoListDTO>> listForPage(AuthOrderInfoListBO authOrderInfoListBO) {
        OrderInfoExample Example = new OrderInfoExample();
        Criteria c = Example.createCriteria();
        c.andUserNoEqualTo(authOrderInfoListBO.getUserNo());
        c.andIsShowUserEqualTo(IsShowEnum.YES.getCode());
        // 初始化页数
        if (StringUtils.isEmpty(authOrderInfoListBO.getPageCurrent())) {
            authOrderInfoListBO.setPageCurrent(1);
        }
        if (StringUtils.isEmpty(authOrderInfoListBO.getPageSize())) {
            authOrderInfoListBO.setPageSize(20);
        }
        // 0用于判断前端,查出除了关闭状态的所有订单
        if (authOrderInfoListBO.getOrderStatus() != null && !authOrderInfoListBO.getOrderStatus().equals(Integer.valueOf(0))) {
            c.andOrderStatusEqualTo(authOrderInfoListBO.getOrderStatus());
        } else {
            c.andOrderStatusNotEqualTo(OrderStatusEnum.CLOSE.getCode());
        }
        Example.setOrderByClause(" id desc ");
        Page<OrderInfo> page = orderInfoDao.listForPage(authOrderInfoListBO.getPageCurrent(), authOrderInfoListBO.getPageSize(), Example);
        Page<AuthOrderInfoListDTO> dtopage = PageUtil.transform(page, AuthOrderInfoListDTO.class);
        for (AuthOrderInfoListDTO dto : dtopage.getList()) {
            Course course = courseDao.getById(dto.getCourseId());
            dto.setCourseLogo(course.getCourseLogo());
            dto.setCourseId(course.getId());
        }
        return Result.success(dtopage);
    }

    /**
     * 订单下单接口
     *
     * @param authOrderPayBO
     * @return
     */
    @Transactional
    public Result<AuthOrderPayDTO> pay(AuthOrderPayBO authOrderPayBO) {
        // 参数校验
        verifyParam(authOrderPayBO);

        // 课程信息
        Course course = courseDao.getByCourseIdAndStatusId(authOrderPayBO.getCourseId(), StatusIdEnum.YES.getCode());
        if (StringUtils.isEmpty(course)) {
            return Result.error("courseId不正确");
        }

        // 根据用户编号查找用户信息
        UserExtVO userextVO = bossUserExt.getByUserNo(authOrderPayBO.getUserNo());
        if (ObjectUtil.isNull(userextVO) || StatusIdEnum.NO.getCode().equals(userextVO.getStatusId())) {
            return Result.error("userNo不正确");
        }

        // 获取讲师信息
        LecturerVO lecturerVO = bossLecturer.getByLecturerUserNo(course.getLecturerUserNo());
        if (StringUtils.isEmpty(lecturerVO) || !StatusIdEnum.YES.getCode().equals(lecturerVO.getStatusId())) {
            return Result.error("lecturerUserNo不正确");
        }

        // 判断所要购买的课程是否已经购买------(如果课程为测试支付课程，可以重复下单，不提示已经购买，去掉后面一截判断即可正常使用)
        if (!checkOrderInfo(authOrderPayBO.getUserNo(), authOrderPayBO.getCourseId()) && !SystemUtil.TEST_COURSE.equals(course.getId().toString())) {
            return Result.error("已经购买该课程，请勿重复购买");
        }

        // 创建订单信息
        OrderInfo orderInfo = createOrderInfo(authOrderPayBO, course, userextVO, lecturerVO);

        // 创建支付订单
        OrderPay orderPay = createOrderPay(orderInfo);

        // 查找系统配置信息
        SysVO sys = bossSys.getSys();
        if (ObjectUtil.isNull(sys)) {
            return Result.error("找不到系统配置信息");
        }
        if (StringUtils.isEmpty(sys.getPayKey()) || StringUtils.isEmpty(sys.getPaySecret()) || StringUtils.isEmpty(sys.getPayUrl())) {
            return Result.error("payKey,paySecret或payUrl未配置");
        }

        // 调用支付接口
        String payMessage = PayUtil.roncooPay(String.valueOf(orderPay.getSerialNumber()), orderInfo.getCourseName(), orderInfo.getPricePaid(), orderInfo.getPayType(), sys.getPayKey(), sys.getPaySecret(), sys.getPayUrl(), sys.getNotifyUrl());
        if (StringUtils.isEmpty(payMessage)) {
            return Result.error("系统繁忙，请稍后再试");
        }

        // 返回实体
        AuthOrderPayDTO dto = new AuthOrderPayDTO();
        dto.setPayMessage(payMessage);
        dto.setOrderNo(String.valueOf(orderInfo.getOrderNo()));
        dto.setCourseName(orderInfo.getCourseName());
        dto.setPayType(orderInfo.getPayType());
        dto.setPrice(orderInfo.getPricePaid());
        return Result.success(dto);
    }

    /**
     * 订单继续支付接口
     *
     * @param orderInfoContinuePayBO
     * @return
     */
    @Transactional
    public Result<AuthOrderPayDTO> continuePay(AuthOrderInfoContinuePayBO authOrderInfoContinuePayBO) {
        if (StringUtils.isEmpty(authOrderInfoContinuePayBO.getOrderNo())) {
            return Result.error("orderNo不能为空");
        }
        if (StringUtils.isEmpty(authOrderInfoContinuePayBO.getPayType())) {
            return Result.error("payType不能为空");
        }

        // 订单校验
        OrderInfo orderInfo = orderInfoDao.getByOrderNo(authOrderInfoContinuePayBO.getOrderNo());
        if (ObjectUtil.isNull(orderInfo)) {
            return Result.error("orderNo不正确，没有找到订单信息");
        }
        if (!checkOrderInfo(orderInfo.getUserNo(), orderInfo.getCourseId())) {
            return Result.error("已经购买该课程，请勿重复购买");
        }

        OrderPay orderPay = orderPayDao.getByOrderNo(orderInfo.getOrderNo());
        if (ObjectUtil.isNull(orderPay)) {
            return Result.error("orderNo不正确，没有找到流水号");
        }

        // 查找课程信息
        Course course = courseDao.getByCourseIdAndStatusId(orderInfo.getCourseId(), StatusIdEnum.YES.getCode());
        if (StringUtils.isEmpty(course) || !StatusIdEnum.YES.getCode().equals(course.getStatusId())) {
            return Result.error("根据订单的课程编号没找到对应的课程信息!");
        }
        // 根据用户编号查找用户信息
        UserExtVO userExtVO = bossUserExt.getByUserNo(orderInfo.getUserNo());
        if (StringUtils.isEmpty(userExtVO) || !StatusIdEnum.YES.getCode().equals(userExtVO.getStatusId())) {
            return Result.error("根据传入的userNo没找到对应的用户信息!");
        }

        // 更新订单信息
        orderInfo.setPayType(authOrderInfoContinuePayBO.getPayType());
        orderInfo.setOrderStatus(OrderStatusEnum.WAIT.getCode());
        orderInfoDao.updateById(orderInfo);

        // 重新生成新的支付流水
        orderPay.setOrderStatus(OrderStatusEnum.WAIT.getCode());
        orderPay.setSerialNumber(NOUtil.getSerialNumber());
        orderPayDao.updateById(orderPay);

        // 查找系统配置信息
        SysVO sys = bossSys.getSys();
        if (ObjectUtil.isNull(sys)) {
            return Result.error("找不到系统配置信息");
        }
        if (StringUtils.isEmpty(sys.getPayKey()) || StringUtils.isEmpty(sys.getPaySecret()) || StringUtils.isEmpty(sys.getPayUrl())) {
            return Result.error("payKey,paySecret或payUrl未配置");
        }

        // 调用支付接口
        String payMessage = PayUtil.roncooPay(String.valueOf(orderPay.getSerialNumber()), orderInfo.getCourseName(), orderInfo.getPricePaid(), orderInfo.getPayType(), sys.getPayKey(), sys.getPaySecret(), sys.getPayUrl(), sys.getNotifyUrl());
        if (StringUtils.isEmpty(payMessage)) {
            return Result.error("系统繁忙，请稍后再试");
        }

        // 返回实体
        AuthOrderPayDTO dto = new AuthOrderPayDTO();
        dto.setPayMessage(payMessage);
        dto.setOrderNo(String.valueOf(orderInfo.getOrderNo()));
        dto.setCourseName(orderInfo.getCourseName());
        dto.setPayType(orderInfo.getPayType());
        dto.setPrice(orderInfo.getPricePaid());
        return Result.success(dto);
    }

    /**
     * 关闭订单信息接口
     *
     * @param continuePay
     * @return
     */
    @Transactional
    public Result<String> close(OrderInfoCloseBO orderInfoCloseBO) {
        if (StringUtils.isEmpty(orderInfoCloseBO.getOrderNo())) {
            return Result.error("orderNo不能为空");
        }
        OrderInfo orderInfo = orderInfoDao.getByOrderNo(orderInfoCloseBO.getOrderNo());
        if (ObjectUtil.isNull(orderInfo)) {
            return Result.error("orderNo不正确,找不到订单信息");
        }
        OrderPay orderPay = orderPayDao.getByOrderNo(orderInfo.getOrderNo());
        if (ObjectUtil.isNull(orderPay)) {
            return Result.error("orderNo不正确,找不到流水号");
        }
        if (!OrderStatusEnum.WAIT.getCode().equals(orderInfo.getOrderStatus())) {
            return Result.error("该订单已经处理完成，不需要再处理");
        }
        // 根据用户编号查找用户信息
        UserExtVO userExtVO = bossUserExt.getByUserNo(orderInfo.getUserNo());
        if (StringUtils.isEmpty(userExtVO) || !StatusIdEnum.YES.getCode().equals(userExtVO.getStatusId())) {
            return Result.error("根据传入的userNo没找到对应的用户信息!");
        }
        orderInfo.setOrderStatus(OrderStatusEnum.CLOSE.getCode());
        int orderNum = orderInfoDao.updateById(orderInfo);
        if (orderNum < 1) {
            throw new BaseException("订单信息更新失败");
        }
        orderPay.setOrderStatus(OrderStatusEnum.CLOSE.getCode());
        int orderPayNum = orderPayDao.updateById(orderPay);
        if (orderPayNum < 1) {
            throw new BaseException("订单流水号更新失败");
        }
        return Result.success("订单关闭成功");
    }

    /**
     * 订单详情
     *
     * @param orderInfoBO
     * @return
     */
    public Result<AuthOrderInfoDTO> view(AuthOrderInfoViewBO authOrderInfoViewBO) {
        if (StringUtils.isEmpty(authOrderInfoViewBO.getOrderNo())) {
            return Result.error("orderNo不能为空");
        }
        // 根据订单编号查找订单信息
        OrderInfo order = orderInfoDao.getByOrderNo(authOrderInfoViewBO.getOrderNo());
        if (ObjectUtil.isNull(order)) {
            return Result.error("orderNo不正确");
        }
        return Result.success(BeanUtil.copyProperties(order, AuthOrderInfoDTO.class));
    }

    /**
     * 查找订单信息列表信息
     *
     * @param authOrderInfoListBO
     * @return
     */
    public Result<Page<AuthOrderInfoListForLecturerDTO>> list(AuthOrderInfoListBO authOrderInfoListBO) {
        if (StringUtils.isEmpty(authOrderInfoListBO.getLecturerUserNo())) {
            return Result.error("lecturerUserNo不正确");
        }
        OrderInfoExample example = new OrderInfoExample();
        Criteria c = example.createCriteria();
        c.andLecturerUserNoEqualTo(authOrderInfoListBO.getLecturerUserNo());
        c.andIsShowUserEqualTo(IsShowUserEnum.YES.getCode());
        c.andPricePaidGreaterThanOrEqualTo(BigDecimal.valueOf(0.5));
        // 不查找已经关闭了的订单
        c.andOrderStatusEqualTo(OrderStatusEnum.SUCCESS.getCode());
        example.setOrderByClause(" id desc ");
        Page<OrderInfo> page = orderInfoDao.listForPage(authOrderInfoListBO.getPageCurrent(), authOrderInfoListBO.getPageSize(), example);
        Page<AuthOrderInfoListForLecturerDTO> dtoPage = PageUtil.transform(page, AuthOrderInfoListForLecturerDTO.class);
        for (AuthOrderInfoListForLecturerDTO dto : dtoPage.getList()) {
            dto.setPhone(dto.getMobile().substring(0, 3) + "****" + dto.getMobile().substring(7, dto.getMobile().length()));
        }
        return Result.success(dtoPage);
    }

    /**
     * 讲师收益折线图
     *
     * @param authOrderInfoForChartsBO
     * @return
     */
    public Result<Option> charts(AuthOrderInfoForChartsBO authOrderInfoForChartsBO) {
        Option option = new Option();
        option.legend().data("订单收益", "日期时间");
        option.tooltip().trigger(Trigger.axis).axisPointer();
        option.calculable(true);
        // 设置x轴数据
        CategoryAxis categoryAxis = new CategoryAxis();
        List<String> xData = new ArrayList<>();
        payTime(authOrderInfoForChartsBO, xData);
        for (String x : xData) {
            categoryAxis.data(x);
        }
        option.xAxis(categoryAxis);

        // 设置y轴数据
        ValueAxis valueAxis = new ValueAxis();
        valueAxis.type(AxisType.value);
        valueAxis.splitArea().show(true);
        valueAxis.axisLabel().formatter("{value}元");
        option.yAxis(valueAxis);
        // 第一条线为当天收益
        Line line1 = new Line();
        List<AuthOrderInfoLecturerIncomeDTO> dtoList = sumByLecturerUserNoAndData(authOrderInfoForChartsBO.getLecturerUserNo(), xData);
        for (AuthOrderInfoLecturerIncomeDTO dto : dtoList) {
            for (BigDecimal bi : dto.getLecturerProfit()) {
                line1.data(bi);
            }
        }
        line1.name("讲师	");
        option.series(line1);
        option.toolbox().show(true).feature(new MagicType(Magic.line, Magic.bar), Tool.restore, Tool.saveAsImage);
        return Result.success(option);
    }

    private List<AuthOrderInfoLecturerIncomeDTO> sumByLecturerUserNoAndData(Long lecturerUserNo, List<String> xData) {
        List<AuthOrderInfoLecturerIncomeDTO> list = new ArrayList<>();
        AuthOrderInfoLecturerIncomeDTO dto = new AuthOrderInfoLecturerIncomeDTO();
        List<BigDecimal> countPaidPrice = new ArrayList<>();
        for (String date : xData) {
            BigDecimal sum = orderInfoDao.sumLecturerUserNoAndData(lecturerUserNo, date);
            countPaidPrice.add(sum);
        }
        dto.setLecturerProfit(countPaidPrice);
        list.add(dto);
        return list;
    }

    private List<String> payTime(AuthOrderInfoForChartsBO authOrderInfoForChartsBO, List<String> xData) {
        // 如果时间为空，则传入现在当前时间七天前的订单
        if (authOrderInfoForChartsBO.getBeginCreate() == null && authOrderInfoForChartsBO.getEndCreate() == null) {
            authOrderInfoForChartsBO.setBeginCreate(DateUtil.format(DateUtil.addDate(new Date(), -7)));
            authOrderInfoForChartsBO.setEndCreate(DateUtil.format(new Date()));
        }
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(DateUtil.parseDate(authOrderInfoForChartsBO.getBeginCreate(), "yyyy-MM-dd"));
        tempStart.add(Calendar.DAY_OF_YEAR, 0);
        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(DateUtil.parseDate(authOrderInfoForChartsBO.getEndCreate(), "yyyy-MM-dd"));
        tempEnd.add(Calendar.DAY_OF_YEAR, 1);
        while (tempStart.before(tempEnd)) {
            xData.add(DateUtil.formatDate(tempStart.getTime()));
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        return xData;
    }

    /**
     * 校验下单时传入的参数
     *
     * @param orderInfoPayBO
     */
    private void verifyParam(AuthOrderPayBO authOrderPayBO) {
        if (StringUtils.isEmpty(authOrderPayBO.getUserNo())) {
            throw new BaseException("userNo不能为空");
        }
        if (StringUtils.isEmpty(authOrderPayBO.getCourseId())) {
            throw new BaseException("courseId不能为空");
        }
        if (StringUtils.isEmpty(authOrderPayBO.getPayType())) {
            throw new BaseException("payType不能为空");
        }
        if (StringUtils.isEmpty(authOrderPayBO.getChannelType())) {
            throw new BaseException("channelType不能为空");
        }
    }

    /**
     * 判断课程是否已经支付
     */
    private boolean checkOrderInfo(long userNo, long courseId) {
        OrderInfo orderInfo = orderInfoDao.getByUserNoAndCourseId(userNo, courseId);
        if (ObjectUtil.isNull(orderInfo)) {
            return true;
        } else if (!OrderStatusEnum.SUCCESS.getCode().equals(orderInfo.getOrderStatus())) {
            return true;
        }
        return false;
    }

    /**
     * 创建订单支付信息
     */
    private OrderPay createOrderPay(OrderInfo retrunOrderInfo) {
        OrderPay orderpay = new OrderPay();
        orderpay.setOrderNo(retrunOrderInfo.getOrderNo());
        orderpay.setOrderStatus(retrunOrderInfo.getOrderStatus());
        orderpay.setPayType(retrunOrderInfo.getPayType());
        orderpay.setSerialNumber(NOUtil.getSerialNumber());
        orderPayDao.save(orderpay);
        return orderpay;
    }

    /**
     * 创建订单信息表
     */
    private OrderInfo createOrderInfo(AuthOrderPayBO authOrderPayBO, Course course, UserExtVO userextvo, LecturerVO lecturervo) {
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setCourseName(course.getCourseName());
        orderInfo.setCourseId(course.getId());
        orderInfo.setPricePaid(course.getCourseDiscount());
        orderInfo.setPricePayable(course.getCourseOriginal());
        orderInfo.setLecturerUserNo(lecturervo.getLecturerUserNo());
        orderInfo.setLecturerName(lecturervo.getLecturerName());
        orderInfo.setUserNo(userextvo.getUserNo());
        orderInfo.setMobile(userextvo.getMobile());
        orderInfo.setRegisterTime(userextvo.getGmtCreate());
        orderInfo.setOrderNo(NOUtil.getOrderNo()); // 订单号，不要使用IdWorker生成
        orderInfo.setCourseId(course.getId());
        orderInfo.setCourseName(course.getCourseName());
        orderInfo.setPriceDiscount(BigDecimal.ZERO);
        orderInfo.setPlatformProfit(BigDecimal.ZERO);
        orderInfo.setLecturerProfit(BigDecimal.ZERO);
        orderInfo.setIsShowUser(IsShowUserEnum.YES.getCode());
        orderInfo.setTradeType(TradeTypeEnum.ONLINE.getCode());
        orderInfo.setPayType(authOrderPayBO.getPayType());
        orderInfo.setChannelType(authOrderPayBO.getChannelType());
        orderInfo.setRemarkCus(authOrderPayBO.getRemarkCus());
        orderInfo.setOrderStatus(OrderStatusEnum.WAIT.getCode());
        orderInfoDao.save(orderInfo);
        return orderInfo;
    }

    class updateCount implements Runnable {
        private Course course;

        public updateCount(Course course) {
            this.course = course;
        }

        @Override
        public void run() {
            Course info = new Course();
            info.setCountBuy(course.getCountBuy() + 1);
            info.setId(course.getId());
            courseDao.updateById(course);
        }
    }

}
