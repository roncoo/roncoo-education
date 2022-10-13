package com.roncoo.education.user.job;

import cn.hutool.core.collection.CollUtil;
import com.roncoo.education.common.core.enums.OrderStatusEnum;
import com.roncoo.education.user.dao.OrderInfoDao;
import com.roncoo.education.user.dao.OrderPayDao;
import com.roncoo.education.user.dao.impl.mapper.entity.OrderInfo;
import com.roncoo.education.user.dao.impl.mapper.entity.OrderInfoExample;
import com.xxl.job.core.context.XxlJobHelper;
import com.xxl.job.core.handler.annotation.XxlJob;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author fengyw
 */
@Slf4j
@Component
public class OrderJob {

    @Autowired
    private OrderInfoDao orderInfoDao;
    @Autowired
    private OrderPayDao orderPayDao;

    /**
     * 每10秒执行一次
     */
    @XxlJob("orderJobHandler")
    public void order() {
        // 处理过期订单
        OrderInfoExample example = new OrderInfoExample();
        OrderInfoExample.Criteria c = example.createCriteria();
        c.andOrderStatusEqualTo(OrderStatusEnum.WAIT.getCode());
        c.andGmtCreateLessThan(LocalDateTime.now().minusMinutes(30));
        List<OrderInfo> orderInfoList = orderInfoDao.listByExample(example);
        if (CollUtil.isNotEmpty(orderInfoList)) {
            for (OrderInfo orderInfo : orderInfoList) {
                OrderInfo record = new OrderInfo();
                record.setId(orderInfo.getId());
                record.setOrderStatus(OrderStatusEnum.CLOSE.getCode());
                orderInfoDao.updateById(record);

                orderPayDao.updateOrderStatusByOrderNo(orderInfo.getOrderNo(), record.getOrderStatus());
            }
        }
        XxlJobHelper.handleSuccess("完成");
    }

}
