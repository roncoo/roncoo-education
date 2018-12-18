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
//import com.roncoo.education.course.feign.web.IBossOrderOrgStatistics;
//import com.roncoo.education.util.base.BaseController;
//
///**
// * 定时任务-机构分润处理
// * 
// * @author wujing
// */
//@Component
//public class OrgCrontab extends BaseController {
//
//	private static final Object KEY = new Object();
//	private static final String TASKCONT = "orgProfit";
//
//	private static boolean taskFlag = false;
//
//	@Autowired
//	private IBossCrontabPlan bossCrontabPlan;
//	@Autowired
//	private IBossOrderOrgStatistics bossOrderOrgStatistics;
//
//	// 每月1号，凌晨4点开始
//	@Scheduled(cron = "0 0 4 1 * ?")
//	public void orderCancel() {
//		synchronized (KEY) {
//			if (OrgCrontab.taskFlag) {
//				logger.warn("机构分润处理-任务已经启动");
//				return;
//			}
//			OrgCrontab.taskFlag = true;
//		}
//		CrontabPlanVO crontabPlanVO = bossCrontabPlan.getByTaskContForUpdate(TASKCONT);
//		if (crontabPlanVO == null || crontabPlanVO.getTaskStatus().equals(2)) {
//			OrgCrontab.taskFlag = false;
//			return;
//		}
//		logger.warn("机构分润处理-定时任务开始");
//
//		try {
//			bossOrderOrgStatistics.handleScheduledTasks();
//		} catch (Exception e) {
//			logger.error("定时任务-机构分润处理-执行出错", e);
//		}
//
//		CrontabPlanQO qo = new CrontabPlanQO();
//		BeanUtils.copyProperties(crontabPlanVO, qo);
//		qo.setTaskStatus(1);
//		bossCrontabPlan.updateById(qo);
//		OrgCrontab.taskFlag = false;
//	}
//
//}
