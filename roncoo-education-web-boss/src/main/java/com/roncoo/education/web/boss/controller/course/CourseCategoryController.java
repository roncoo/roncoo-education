package com.roncoo.education.web.boss.controller.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.roncoo.education.course.common.bean.qo.CourseCategoryQO;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.web.boss.biz.course.CourseCategoryBiz;

/**
 * 课程分类
 *
 * @author wujing
 */
@Controller
@RequestMapping(value = "/course/courseCategory")
public class CourseCategoryController extends BaseController {

	private final static String TARGETID = "course-courseCategory";

	@Autowired
	private CourseCategoryBiz biz;

	@RequestMapping(value = "/list")
	public void list(@ModelAttribute CourseCategoryQO qo, ModelMap modelMap) {
		modelMap.put("page", biz.listForPage(qo));
		modelMap.put("bean", qo);
	}

	@RequestMapping(value = "/listFloor")
	public void listFloor(@ModelAttribute CourseCategoryQO qo, ModelMap modelMap) {
		modelMap.put("page", biz.listForPage(qo));
		modelMap.put("bean", qo);
	}

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public void add(@ModelAttribute CourseCategoryQO qo, ModelMap modelMap) {
		modelMap.put("bean", qo);
	}

	@RequestMapping(value = "/add2", method = RequestMethod.GET)
	public void add2(@ModelAttribute CourseCategoryQO qo, ModelMap modelMap) {
		modelMap.put("bean", qo);
	}

	@ResponseBody
	@RequestMapping(value = "/save")
	public String save(@ModelAttribute CourseCategoryQO qo) {
		if (biz.save(qo) > 0) {
			return success(TARGETID);
		}
		return error("添加失败");
	}

	@ResponseBody
	@RequestMapping(value = "/delete")
	public String delete(@ModelAttribute CourseCategoryQO qo) {
		int result = biz.deleteById(qo);
		if (result > 0) {
			return delete(TARGETID);
		} else if (result == -1) {
			return error("删除失败，请先删除子分类");
		}
		return error("删除失败");
	}

	@RequestMapping(value = "/edit")
	public void edit(@RequestParam(value = "id") Long id, ModelMap modelMap) {
		modelMap.put("bean", biz.getById(id));
	}

	@ResponseBody
	@RequestMapping(value = "/update")
	public String update(@ModelAttribute CourseCategoryQO qo) {
		if (biz.updateById(qo) > 0) {
			return success(TARGETID);
		}
		return error("修改失败");
	}

	@RequestMapping(value = "/view")
	public void view(@RequestParam(value = "id") Long id, ModelMap modelMap) {
		modelMap.put("bean", biz.getById(id));
	}

}
