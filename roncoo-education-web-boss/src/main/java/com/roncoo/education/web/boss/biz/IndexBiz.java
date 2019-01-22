package com.roncoo.education.web.boss.biz;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.github.abel533.echarts.Option;
import com.github.abel533.echarts.axis.CategoryAxis;
import com.github.abel533.echarts.axis.ValueAxis;
import com.github.abel533.echarts.code.AxisType;
import com.github.abel533.echarts.code.Magic;
import com.github.abel533.echarts.code.Tool;
import com.github.abel533.echarts.code.Trigger;
import com.github.abel533.echarts.feature.MagicType;
import com.github.abel533.echarts.series.Line;
import com.roncoo.education.course.common.bean.qo.OrderEchartsQO;
import com.roncoo.education.course.common.bean.qo.OrderInfoQO;
import com.roncoo.education.course.common.bean.vo.OrderEchartsVO;
import com.roncoo.education.course.feign.IBossOrderInfo;
import com.roncoo.education.user.common.bean.qo.UserExtEchartsQO;
import com.roncoo.education.user.common.bean.vo.UserEchartsVO;
import com.roncoo.education.user.feign.IBossUserExt;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.tools.DateUtil;

/**
 * 首页信息
 * 
 * @author wuyun
 *
 */
@Component
public class IndexBiz extends BaseBiz {

	@Autowired
	private IBossOrderInfo bossOrderInfo;
	@Autowired
	private IBossUserExt bossUserExt;

	/**
	 * 图表
	 * 
	 * @author wuyun
	 * @return
	 */
	public Option orderUser() {
		Option option = new Option();

		option.legend().data("订单数", "订单金额", "用户注册量");
		option.tooltip().trigger(Trigger.axis).axisPointer();
		option.calculable(true);

		// 设置x轴数据
		CategoryAxis categoryAxisc = new CategoryAxis();
		List<String> xdata = new ArrayList<>();
		OrderInfoQO orderInfoQO = new OrderInfoQO();
		payTime(orderInfoQO, xdata);
		for (String x : xdata) {
			categoryAxisc.data(x);
		}
		option.xAxis(categoryAxisc);

		// 设置左y轴数据
		ValueAxis valueAxis = new ValueAxis();
		valueAxis.type(AxisType.value);
		valueAxis.splitArea().show(true);
		option.yAxis(valueAxis);

		// 设置右y轴数据
		ValueAxis valueAxis1 = new ValueAxis();
		valueAxis.type(AxisType.value);
		valueAxis1.splitArea().show(true);
		valueAxis1.axisLabel().formatter("{value}元");
		option.yAxis(valueAxis1);

		// 获取订单数
		Line line1 = new Line();
		OrderEchartsQO orderEchartsQO = new OrderEchartsQO();
		orderEchartsQO.setDateList(xdata);
		List<OrderEchartsVO> countOrders = bossOrderInfo.sumByCountOrders(orderEchartsQO);
		for (OrderEchartsVO vo : countOrders) {
			for (Integer bi : vo.getCountOrders()) {
				line1.data(bi);
			}
		}
		line1.name("订单数");

		// 获取订单金额
		Line line2 = new Line();
		List<OrderEchartsVO> orderEcharts = bossOrderInfo.sumByPayTime(orderEchartsQO);
		for (OrderEchartsVO orderEchartsVO : orderEcharts) {
			for (BigDecimal bd : orderEchartsVO.getCountPaidPrice()) {
				line2.data(bd);
			}
		}
		line2.yAxisIndex(1);
		line2.name("订单金额");

		// 获取用户注册量
		Line line3 = new Line();
		UserExtEchartsQO userExtEchartsQO = new UserExtEchartsQO();
		userExtEchartsQO.setDateList(xdata);
		List<UserEchartsVO> userList = bossUserExt.sumByCounts(userExtEchartsQO);
		for (UserEchartsVO UserEcharts : userList) {
			for (Integer c : UserEcharts.getCount()) {
				line3.data(c);
			}
		}
		line3.name("用户注册量");
		option.series(line1, line2, line3);
		option.toolbox().show(true).feature(new MagicType(Magic.line, Magic.bar), Tool.restore, Tool.saveAsImage);
		return option;
	}

	/**
	 * 获取当前时间与后十天的时间
	 * 
	 * @param orderInfoQO
	 * @param data1
	 * @return
	 * @author wuyun
	 */
	private List<String> payTime(OrderInfoQO orderInfoQO, List<String> data1) {
//		orderInfoQO.setBeginCreate(DateUtil.format(DateUtil.addDate(new Date(), -10)));
//		orderInfoQO.setEndCreate(DateUtil.format(new Date()));
		
		// 演示需要
		orderInfoQO.setBeginCreate("2019-01-16");
		orderInfoQO.setEndCreate("2019-01-25");
		
		Calendar tempStart = Calendar.getInstance();
		tempStart.setTime(DateUtil.parseDate(orderInfoQO.getBeginCreate(), "yyyy-MM-dd"));
		tempStart.add(Calendar.DAY_OF_YEAR, 0);
		Calendar tempEnd = Calendar.getInstance();
		tempEnd.setTime(DateUtil.parseDate(orderInfoQO.getEndCreate(), "yyyy-MM-dd"));
		tempEnd.add(Calendar.DAY_OF_YEAR, 1);
		while (tempStart.before(tempEnd)) {
			data1.add(DateUtil.formatDate(tempStart.getTime()));
			tempStart.add(Calendar.DAY_OF_YEAR, 1);
		}
		return data1;
	}

}
