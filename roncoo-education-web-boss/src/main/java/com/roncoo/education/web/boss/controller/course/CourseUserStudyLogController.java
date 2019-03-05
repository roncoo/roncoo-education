package com.roncoo.education.web.boss.controller.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.abel533.echarts.Option;
import com.roncoo.education.course.common.bean.qo.CourseUserStudyLogQO;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.web.boss.biz.course.CourseUserStudyLogBiz;

/**
 * 课程用户学习日志
 *
 * @author wujing
 */
@Controller
@RequestMapping(value = "/course/courseUserStudyLog")
public class CourseUserStudyLogController extends BaseController {

	private final static String TARGETID = "course-courseUserStudyLog";

	@Autowired
	private CourseUserStudyLogBiz biz;

	@RequestMapping(value = "/list")
	public void list(@ModelAttribute CourseUserStudyLogQO qo, ModelMap modelMap) {
		modelMap.put("page", biz.listForPage(qo));
		modelMap.put("bean", qo);
	}

	@RequestMapping(value = "/add")
	public void add() {

	}

	@ResponseBody
	@RequestMapping(value = "/save")
	public String save(@ModelAttribute CourseUserStudyLogQO qo) {
		if (biz.save(qo) > 0) {
			return success(TARGETID);
		}
		return error("添加失败");
	}

	@ResponseBody
	@RequestMapping(value = "/delete")
	public String delete(@RequestParam(value = "id") Long id) {
		if (biz.deleteById(id) > 0) {
			return delete(TARGETID);
		}
		return error("删除失败");
	}

	@RequestMapping(value = "/edit")
	public void edit(@RequestParam(value = "id") Long id, ModelMap modelMap) {
		modelMap.put("bean", biz.getById(id));
	}

	@ResponseBody
	@RequestMapping(value = "/update")
	public String update(@ModelAttribute CourseUserStudyLogQO qo) {
		if (biz.updateById(qo) > 0) {
			return success(TARGETID);
		}
		return error("修改失败");
	}

	@RequestMapping(value = "/view")
	public void view(@RequestParam(value = "id") Long id, ModelMap modelMap) {
		modelMap.put("bean", biz.getById(id));
	}

	@RequestMapping(value = "/courseList")
	public void courseList(@ModelAttribute CourseUserStudyLogQO qo, ModelMap modelMap) {
		modelMap.put("page", biz.courseList(qo));
		modelMap.put("bean", qo);
	}

	@ResponseBody
	@RequestMapping(value = "/coursePlayCount")
	public Option coursePlayCount(@ModelAttribute CourseUserStudyLogQO qo) {
		return biz.coursePlayCount(qo);
	}

	@RequestMapping(value = "/periodList")
	public void periodList(@ModelAttribute CourseUserStudyLogQO qo, ModelMap modelMap) {
		modelMap.put("page", biz.periodList(qo));
		modelMap.put("bean", qo);
	}

	@ResponseBody
	@RequestMapping(value = "/periodPlayCount")
	public Option periodPlayCount(@ModelAttribute CourseUserStudyLogQO qo) {
		return biz.periodPlayCount(qo);
	}

}
