package com.roncoo.education.web.boss.controller.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.roncoo.education.course.common.bean.qo.CourseQO;
import com.roncoo.education.course.common.bean.qo.ZoneCourseQO;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.web.boss.biz.course.ZoneCourseBiz;

/**
 * 专区课程关联表
 *
 * @author wujing
 */
@Controller
@RequestMapping(value = "/course/zoneCourse")
public class ZoneCourseController extends BaseController {

	private final static String TARGETID = "course-zoneCourse";

	@Autowired
	private ZoneCourseBiz biz;

	@RequestMapping(value = "/list")
	public void list(@ModelAttribute ZoneCourseQO qo, ModelMap modelMap) {
		modelMap.put("page", biz.listForPage(qo));
		modelMap.put("bean", qo);
	}

	@RequestMapping(value = "/courseList")
	public void list(@ModelAttribute CourseQO qo, Long zoneId, ModelMap modelMap) {
		biz.check(qo, zoneId);
		modelMap.put("page", biz.list(qo));
		modelMap.put("zoneId", zoneId);
		modelMap.put("bean", qo);
	}

	@ResponseBody
	@RequestMapping(value = "/save")
	public String save(@ModelAttribute ZoneCourseQO qo) {
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
	public String update(@ModelAttribute ZoneCourseQO qo) {
		if (biz.updateById(qo) > 0) {
			return success(TARGETID);
		}
		return error("修改失败");
	}

	@RequestMapping(value = "/view")
	public void view(@ModelAttribute ZoneCourseQO qo, ModelMap modelMap) {
		modelMap.put("bean", biz.getBycourseId(qo.getCourseId()));
		modelMap.put("zoneId", qo.getZoneId());
		modelMap.put("lecturerList", biz.listAllForLecturer());
		modelMap.put("zoneList", biz.listAllZone());
	}

	@ResponseBody
	@RequestMapping(value = "/status")
	public String status(@ModelAttribute ZoneCourseQO qo) {
		if (biz.updateById(qo) > 0) {
			return delete("操作成功");
		}
		return error("操作失败");
	}
}
