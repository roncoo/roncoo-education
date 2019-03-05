package com.roncoo.education.web.boss.biz.course;

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
import com.roncoo.education.course.common.bean.qo.CourseUserStudyLogQO;
import com.roncoo.education.course.common.bean.vo.CourseUserStudyLogVO;
import com.roncoo.education.course.feign.IBossCourseUserStudyLog;
import com.roncoo.education.util.base.BaseBiz;
import com.roncoo.education.util.base.Page;

/**
 * 课程用户学习日志
 *
 * @author wujing
 */
@Component
public class CourseUserStudyLogBiz extends BaseBiz {

	@Autowired
	private IBossCourseUserStudyLog bossCourseUserStudyLog;

	public Page<CourseUserStudyLogVO> listForPage(CourseUserStudyLogQO qo) {
		return bossCourseUserStudyLog.listForPage(qo);
	}

	public int save(CourseUserStudyLogQO qo) {
		return bossCourseUserStudyLog.save(qo);
	}

	public int deleteById(Long id) {
		return bossCourseUserStudyLog.deleteById(id);
	}

	public CourseUserStudyLogVO getById(Long id) {
		return bossCourseUserStudyLog.getById(id);
	}

	public int updateById(CourseUserStudyLogQO qo) {
		return bossCourseUserStudyLog.updateById(qo);
	}

	public Page<CourseUserStudyLogVO> courseList(CourseUserStudyLogQO qo) {
		return bossCourseUserStudyLog.courseList(qo);
	}

	public Option coursePlayCount(CourseUserStudyLogQO qo) {
		Option option = new Option();
		option.legend().data("课程播放量排行榜");
		option.tooltip().trigger(Trigger.axis).axisPointer();
		option.calculable(true);

		// 设置x轴数据
		CategoryAxis categoryAxisc = new CategoryAxis();
		// 根据时间取得前五播放量的课程
		List<CourseUserStudyLogVO> courseUserStudyLogs = bossCourseUserStudyLog.countCourseIdByGmtCreate(qo);
		for (CourseUserStudyLogVO courseUserStudyLog : courseUserStudyLogs) {
			categoryAxisc.data(courseUserStudyLog.getCourseName());
		}
		categoryAxisc.interval(0);
		option.xAxis(categoryAxisc);

		// 设置左y轴数据
		ValueAxis valueAxis = new ValueAxis();
		valueAxis.type(AxisType.value);
		valueAxis.splitArea().show(true);
		valueAxis.axisLabel().formatter("{value}次");
		option.yAxis(valueAxis);

		// 设置右y轴数据
		ValueAxis valueAxis1 = new ValueAxis();
		valueAxis.type(AxisType.value);
		valueAxis1.splitArea().show(true);
		valueAxis1.axisLabel().formatter("{value}次");
		option.yAxis(valueAxis1);

		Line line1 = new Line();
		for (CourseUserStudyLogVO courseUserStudyLog : courseUserStudyLogs) {
			line1.data(courseUserStudyLog.getCourseId());
		}
		line1.name("课程播放数");

		option.series(line1);
		option.toolbox().show(true).feature(new MagicType(Magic.line, Magic.bar), Tool.restore, Tool.saveAsImage);
		return option;
	}

	public Page<CourseUserStudyLogVO> periodList(CourseUserStudyLogQO qo) {
		return bossCourseUserStudyLog.periodList(qo);
	}

	public Option periodPlayCount(CourseUserStudyLogQO qo) {
		Option option = new Option();
		option.legend().data("课时播放量排行榜");
		option.tooltip().trigger(Trigger.axis).axisPointer();
		option.calculable(true);

		// 设置x轴数据
		CategoryAxis categoryAxisc = new CategoryAxis();
		List<CourseUserStudyLogVO> courseUserStudyLogs = bossCourseUserStudyLog.countPeriodNoByCourseIdAndGmtCreate(qo);
		for (CourseUserStudyLogVO courseUserStudyLog : courseUserStudyLogs) {
			categoryAxisc.data(courseUserStudyLog.getPeriodName());
		}
		categoryAxisc.interval(0);
		option.xAxis(categoryAxisc);

		// 设置左y轴数据
		ValueAxis valueAxis = new ValueAxis();
		valueAxis.type(AxisType.value);
		valueAxis.splitArea().show(true);
		valueAxis.axisLabel().formatter("{value}次");
		option.yAxis(valueAxis);
		// 设置右y轴数据
		ValueAxis valueAxis1 = new ValueAxis();
		valueAxis.type(AxisType.value);
		valueAxis1.splitArea().show(true);
		valueAxis1.axisLabel().formatter("{value}次");
		option.yAxis(valueAxis1);

		Line line1 = new Line();
		for (CourseUserStudyLogVO courseUserStudyLog : courseUserStudyLogs) {
			line1.data(courseUserStudyLog.getPeriodId());
		}
		line1.name("课程播放数");

		option.series(line1);
		option.toolbox().show(true).feature(new MagicType(Magic.line, Magic.bar), Tool.restore, Tool.saveAsImage);
		return option;
	}

}
