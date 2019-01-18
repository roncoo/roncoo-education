package com.roncoo.education.course.common.interfaces;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.course.common.bean.qo.OrderEchartsQO;
import com.roncoo.education.course.common.bean.qo.OrderInfoQO;
import com.roncoo.education.course.common.bean.vo.CountIncomeVO;
import com.roncoo.education.course.common.bean.vo.OrderEchartsVO;
import com.roncoo.education.course.common.bean.vo.OrderInfoVO;
import com.roncoo.education.course.common.bean.vo.OrderReportVO;
import com.roncoo.education.util.base.Page;

/**
 * 订单信息表
 *
 * @author wujing
 */
public interface BossOrderInfo {

	@RequestMapping(value = "/boss/course/orderInfo/listForPage", method = RequestMethod.POST)
	Page<OrderInfoVO> listForPage(@RequestBody OrderInfoQO qo);

	@RequestMapping(value = "/boss/course/orderInfo/save", method = RequestMethod.POST)
	int save(@RequestBody OrderInfoQO qo);

	@RequestMapping(value = "/boss/course/orderInfo/delete/{id}", method = RequestMethod.DELETE)
	int deleteById(@PathVariable(value = "id") Long id);

	@RequestMapping(value = "/boss/course/orderInfo/update", method = RequestMethod.PUT)
	int updateById(@RequestBody OrderInfoQO qo);

	@RequestMapping(value = "/boss/course/orderInfo/get/{id}", method = RequestMethod.GET)
	OrderInfoVO getById(@PathVariable(value = "id") Long id);

	/**
	 * 订单信息汇总（导出报表）
	 * 
	 * @author YZJ
	 */
	@RequestMapping(value = "/boss/course/orderInfo/listForReport", method = RequestMethod.POST)
	List<OrderReportVO> listForReport(@RequestBody OrderInfoQO orderInfoQO);

	/**
	 * 统计时间段的总订单数
	 * 
	 * @param orderEchartsQO
	 * @return
	 * @author wuyun
	 */
	@RequestMapping(value = "/boss/course/orderInfo/sumByCountOrders", method = RequestMethod.POST)
	List<OrderEchartsVO> sumByCountOrders(@RequestBody OrderEchartsQO orderEchartsQO);

	/**
	 * 统计时间段的总收入
	 * 
	 * @param orderEchartsQO
	 * @return
	 * @author wuyun
	 */
	@RequestMapping(value = "/boss/course/orderInfo/sumByPayTime", method = RequestMethod.POST)
	List<OrderEchartsVO> sumByPayTime(@RequestBody OrderEchartsQO orderEchartsQO);

	/**
	 * 订单处理定时任务
	 * 
	 * @author wuyun
	 */
	@RequestMapping(value = "/boss/course/orderInfo/handleScheduledTasks", method = RequestMethod.POST)
	int handleScheduledTasks();

	/**
	 * 统计订单收入情况
	 * 
	 * @author wuyun
	 */
	@RequestMapping(value = "/boss/course/orderInfo/countIncome", method = RequestMethod.POST)
	CountIncomeVO countIncome(@RequestBody OrderInfoQO qo);
	
}
