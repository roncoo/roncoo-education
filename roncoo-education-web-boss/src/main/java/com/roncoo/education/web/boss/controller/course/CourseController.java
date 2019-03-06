package com.roncoo.education.web.boss.controller.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.roncoo.education.course.common.bean.qo.CourseQO;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.web.boss.biz.course.CourseBiz;

/**
 * 课程信息
 *
 * @author wujing
 */
@Controller
@RequestMapping(value = "/course/course")
public class CourseController extends BaseController {

	private final static String TARGETID = "course-course";

	@Autowired
	private CourseBiz biz;

	@RequestMapping(value = "/list")
	public void list(@ModelAttribute CourseQO qo, ModelMap modelMap) {
		modelMap.put("page", biz.listForPage(qo));
		modelMap.put("bean", qo);
		modelMap.put("lecturerList", biz.listAllLecturer());
	}

	@RequestMapping(value = "/add")
	public void add() {

	}

	@ResponseBody
	@RequestMapping(value = "/save")
	public String save(@ModelAttribute CourseQO qo) {
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
	public String update(@ModelAttribute CourseQO qo, MultipartFile advFile) {
		if (biz.updateById(qo, advFile) > 0) {
			return success(TARGETID);
		}
		return error("修改失败");
	}

	@RequestMapping(value = "/view")
	public void view(@RequestParam(value = "id") Long id, ModelMap modelMap) {
		modelMap.put("bean", biz.getByCourseId(id));
		modelMap.put("lecturerList", biz.listAllLecturer());
	}

	@ResponseBody
	@RequestMapping(value = "/status")
	public String status(@ModelAttribute CourseQO qo) {
		if (biz.updateByStatusId(qo) > 0) {
			return ties("操作成功");
		}
		return error("修改失败");
	}

}
