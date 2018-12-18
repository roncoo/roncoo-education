package com.roncoo.education.web.boss.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.roncoo.education.user.common.bean.qo.LecturerAuditQO;
import com.roncoo.education.user.common.bean.vo.LecturerAuditVO;
import com.roncoo.education.util.annotation.AdminLog;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.web.boss.biz.user.LecturerAuditBiz;

/**
 * 讲师信息-审核 
 *
 * @author wujing
 */
@Controller
@RequestMapping(value = "/user/lecturerAudit")
public class LecturerAuditController extends BaseController {

	private final static String TARGETID = "user-lecturerAudit";

	@Autowired
	private LecturerAuditBiz biz;
	
	@RequestMapping(value = "/list")
	public void list(@ModelAttribute LecturerAuditQO qo, ModelMap modelMap){
		modelMap.put("page", biz.listForPage(qo));
		modelMap.put("bean", qo);
		
	}
	
	@RequestMapping(value = "/add")
	public void add(){
	
	}
	
	@ResponseBody
	@AdminLog(value = "讲师信息添加")
	@RequestMapping(value = "/save")
	public String save(@ModelAttribute LecturerAuditQO qo){
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
	@AdminLog(value = "讲师信息修改")
	@RequestMapping(value = "/update")
	public String update(@ModelAttribute LecturerAuditQO qo){
		if (biz.updateById(qo) > 0) {
			return success(TARGETID);
		}
		return error("修改失败");
	}
	
	@RequestMapping(value = "/view")
	public void view(@RequestParam(value = "id") Long id, ModelMap modelMap){
		modelMap.put("bean", biz.getById(id));
	}
	
	/**
	 * 禁用启用
	 * 
	 * @author WY
	 * @param modelMap
	 */
	@ResponseBody
	@RequestMapping(value = "/status")
	public String delete(@ModelAttribute LecturerAuditQO qo){
		if (biz.updateStatusId(qo) > 0) {
			return delete(TARGETID);
		}
		return error("删除失败");
	}
	
	/**
	 * 审核页面
	 * 
	 * @author WY
	 * @param modelMap
	 */
	@RequestMapping(value = "/isAudit")
	public void isAudit(@ModelAttribute LecturerAuditQO qo, ModelMap modelMap){
		modelMap.put("bean", qo);
		
	}
	
	/**
	 * 审核
	 * 
	 * @author WY
	 * @param qo
	 * @return
	 */
	@ResponseBody
	@AdminLog(value = "讲师信息审核")
	@RequestMapping(value = "/audit")
	public String audit(@ModelAttribute LecturerAuditQO qo){
		if (biz.audit(qo) > 0) {
			return success(TARGETID);
		}
		return error("审核失败");
	}
	
	@ResponseBody
	@RequestMapping(value = "/checkUserAndLecturer")
	public LecturerAuditVO checkUserAndLecturer(@ModelAttribute LecturerAuditQO qo) {
		return biz.checkUserAndLecturer(qo);
	}
}
