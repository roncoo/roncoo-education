package com.roncoo.education.web.boss.controller.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.roncoo.education.web.boss.biz.course.CourseChapterPeriodAuditBiz;
import com.roncoo.education.course.common.bean.qo.CourseChapterPeriodAuditQO;
import com.roncoo.education.util.base.BaseController;

/**
 * 课时信息-审核 
 *
 * @author wujing
 */
@Controller
@RequestMapping(value = "/course/courseChapterPeriodAudit")
public class CourseChapterPeriodAuditController extends BaseController {

	private final static String TARGETID = "course-courseChapterPeriodAudit";

	@Autowired
	private CourseChapterPeriodAuditBiz biz;
	
	@RequestMapping(value = "/list")
	public void list(@ModelAttribute CourseChapterPeriodAuditQO qo, ModelMap modelMap){
		modelMap.put("page", biz.listForPage(qo));
		modelMap.put("bean", qo);
	}
	
	@RequestMapping(value = "/add")
	public void add(){
	
	}
	
	@ResponseBody
	@RequestMapping(value = "/save")
	public String save(@ModelAttribute CourseChapterPeriodAuditQO qo){
		if (biz.save(qo) > 0) {
			return success(TARGETID);
		}
		return error("添加失败");
	}
	
	@ResponseBody
	@RequestMapping(value = "/delete")
	public String delete(@RequestParam(value = "id") Long id){
		if (biz.deleteById(id) > 0) {
			return delete(TARGETID);
		}
		return error("删除失败");
	}
	
	@RequestMapping(value = "/edit")
	public void edit(@RequestParam(value = "id") Long id, ModelMap modelMap){
		modelMap.put("bean", biz.getById(id));
	}
	
	@ResponseBody
	@RequestMapping(value = "/update")
	public String update(@ModelAttribute CourseChapterPeriodAuditQO qo){
		if (biz.updateById(qo) > 0) {
			return success(TARGETID);
		}
		return error("修改失败");
	}
	
	@RequestMapping(value = "/view")
	public void view(@RequestParam(value = "id") Long id, ModelMap modelMap){
		modelMap.put("bean", biz.getById(id));
	}
	
}
