package com.roncoo.education.app.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 站内信-定时发送
 *
 * @author wuyun
 */
@Component
public class MsgSendCrontab extends BaseController {

    private static final Object KEY = new Object();
    private static boolean taskFlag = false;

    @Autowired
    private IFeignMsg bossMsg;

    /**
     * 定时任务，一小时启动一次
     *
     * @author wuyun
     */
    @Scheduled(fixedRate = 60000)
    public void pushCancel() {
        synchronized (KEY) {
            if (MsgSendCrontab.taskFlag) {
                log.warn("站内信-定时发送已经启动");
                return;
            }
            MsgSendCrontab.taskFlag = true;
        }

        try {
            bossMsg.push();
        } catch (Exception e) {
            log.error("站内信-定时发送-执行出错", e);
        }

        MsgSendCrontab.taskFlag = false;

        log.warn("站内信-定时发送-任务完成");
    }
}
