package com.roncoo.education.app.job;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.course.feign.interfaces.IFeignOrderInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务-订单处理
 *
 * @author wuyun
 */
@Component
public class OrderCrontab extends BaseController {

    private static final Object KEY = new Object();

    private static boolean taskFlag = false;

    @Autowired
    private IFeignOrderInfo feignOrderInfo;

    /**
     * 定时任务每分钟执行一次
     */
    @Scheduled(fixedRate = 60000)
    public void orderCancel() {
        synchronized (KEY) {
            if (OrderCrontab.taskFlag) {
                logger.warn("订单处理-任务已经启动");
                return;
            }
            OrderCrontab.taskFlag = true;
        }
        logger.warn("订单处理-定时任务开始");

        try {
            feignOrderInfo.handleScheduledTasks();
        } catch (Exception e) {
            logger.error("定时任务-订单处理-执行出错", e);
        }
        OrderCrontab.taskFlag = false;
    }

}
