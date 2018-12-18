package com.roncoo.education.web.boss.controller.course;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.roncoo.education.course.common.bean.qo.CourseAuditQO;
import com.roncoo.education.util.annotation.AdminLog;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.web.boss.biz.course.CourseAuditBiz;

/**
 * 课程信息-审核
 *
 * @author wujing
 */
@Controller
@RequestMapping(value = "/course/courseAudit")
public class CourseAuditController extends BaseController {

	private final static String TARGETID = "course-courseAudit";

	@Autowired
	private CourseAuditBiz biz;

	@RequestMapping(value = "/list")
	public void list(@ModelAttribute CourseAuditQO qo, ModelMap modelMap) {
		modelMap.put("page", biz.listForPage(qo));
		modelMap.put("bean", qo);
		modelMap.put("lecturerList", biz.listAllLecturer());
	}

	@RequestMapping(value = "/add")
	public void add() {

	}

	@ResponseBody
	@RequestMapping(value = "/save")
	public String save(@ModelAttribute CourseAuditQO qo) {
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
	public void edit(@ModelAttribute CourseAuditQO qo, ModelMap modelMap) {
		modelMap.put("bean", biz.getById(qo.getId()));
		modelMap.put("oneList", biz.listByFloorAndCategoryId(1, Integer.valueOf(0).longValue()));
		modelMap.put("twoList", biz.listByFloorAndCategoryId(2, qo.getCategoryId1()));
		modelMap.put("threeList", biz.listByFloorAndCategoryId(3, qo.getCategoryId2()));
	}

	@ResponseBody
	@AdminLog(value = "录播课程修改")
	@RequestMapping(value = "/update")
	public String update(@ModelAttribute CourseAuditQO qo, MultipartFile advFile) {
		if (biz.updateById(qo, advFile) > 0) {
			return success(TARGETID);
		}
		return error("修改失败");
	}

	@RequestMapping(value = "/view")
	public void view(@RequestParam(value = "id") Long id, ModelMap modelMap) {
		modelMap.put("bean", biz.getById(id));
	}

	@ResponseBody
	@AdminLog(value = "录播课程删除")
	@RequestMapping(value = "/status")
	public String updateByStatusId(@ModelAttribute CourseAuditQO qo) {
		if (biz.updateStatusId(qo) > 0) {
			return ties("操作成功");
		}
		return error("修改失败");
	}

	/**
	 * 审核页面
	 * 
	 * @author WY
	 * @param modelMap
	 */
	@RequestMapping(value = "/isAudit")
	public void isAudit(@ModelAttribute CourseAuditQO qo, ModelMap modelMap) {
		modelMap.put("bean", qo);
	}

	/**
	 * 审核
	 * 
	 * @author WY
	 * @param modelMap
	 */
	@ResponseBody
	@AdminLog(value = "录播课程审核")
	@RequestMapping(value = "/audit")
	public String audit(@ModelAttribute CourseAuditQO qo) {
		if (biz.audit(qo) > 0) {
			return success(TARGETID);
		}
		return error("审核失败");
	}
}
