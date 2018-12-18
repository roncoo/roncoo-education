//package com.roncoo.education.crontab;
//
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
//import com.roncoo.education.course.common.bean.qo.CrontabPlanQO;
//import com.roncoo.education.course.common.bean.vo.CrontabPlanVO;
//import com.roncoo.education.course.feign.web.IBossCrontabPlan;
//import com.roncoo.education.course.feign.web.IBossOrderInfo;
//import com.roncoo.education.util.base.BaseController;
//
///**
// * 定时任务-讲师分润处理
// * 
// * @author wujing
// */
//@Component
//public class LecturerCrontab extends BaseController {
//
//	private static final Object KEY = new Object();
//	private static final String TASKCONT = "lecturerProfit";
//
//	private static boolean taskFlag = false;
//
//	@Autowired
//	private IBossCrontabPlan bossCrontabPlan;
//	@Autowired
//	private IBossOrderInfo bossOrderInfo;
//
//	// 每月1号，凌晨3点开始
//	@Scheduled(cron = "0 0 3 1 * ?")
//	public void orderCancel() {
//		synchronized (KEY) {
//			if (LecturerCrontab.taskFlag) {
//				logger.warn("讲师分润处理-任务已经启动");
//				return;
//			}
//			LecturerCrontab.taskFlag = true;
//		}
//		CrontabPlanVO crontabPlanVO = bossCrontabPlan.getByTaskContForUpdate(TASKCONT);
//		if (crontabPlanVO == null || crontabPlanVO.getTaskStatus().equals(2)) {
//			LecturerCrontab.taskFlag = false;
//			return;
//		}
//		logger.warn("讲师分润处理-定时任务开始");
//
//		try {
//			bossOrderInfo.handleScheduledTasksForLecturer();
//		} catch (Exception e) {
//			logger.error("定时任务-讲师分润处理-执行出错", e);
//		}
//
//		CrontabPlanQO qo = new CrontabPlanQO();
//		BeanUtils.copyProperties(crontabPlanVO, qo);
//		qo.setTaskStatus(1);
//		bossCrontabPlan.updateById(qo);
//		LecturerCrontab.taskFlag = false;
//	}
//
//}
