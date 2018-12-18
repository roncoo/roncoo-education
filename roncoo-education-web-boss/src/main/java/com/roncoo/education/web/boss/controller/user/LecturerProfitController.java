package com.roncoo.education.web.boss.controller.user;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.roncoo.education.web.boss.biz.user.LecturerProfitBiz;
import com.roncoo.education.user.common.bean.qo.LecturerProfitQO;
import com.roncoo.education.util.base.BaseController;

/**
 * 讲师提现日志表
 *
 * @author wujing
 */
@Controller
@RequestMapping(value = "/user/lecturerProfit")
public class LecturerProfitController extends BaseController {

	private final static String TARGETID = "user-lecturerProfit";

	@Autowired
	private LecturerProfitBiz biz;

	@RequestMapping(value = "/list")
	public void list(@ModelAttribute LecturerProfitQO qo, ModelMap modelMap) {
		modelMap.put("page", biz.listForPage(qo));
		modelMap.put("bean", qo);
	}

	@RequestMapping(value = "/add")
	public void add() {

	}

	@ResponseBody
	@RequestMapping(value = "/save")
	public String save(@ModelAttribute LecturerProfitQO qo) {
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
	public String update(@ModelAttribute LecturerProfitQO qo) {
		if (biz.updateById(qo) > 0) {
			return success(TARGETID);
		}
		return error("修改失败");
	}

	@RequestMapping(value = "/view")
	public void view(@RequestParam(value = "id") Long id, ModelMap modelMap) {
		modelMap.put("bean", biz.getById(id));
	}

	/**
	 * 状态页面
	 * 
	 * @author WY
	 * @param modelMap
	 */
	@RequestMapping(value = "/isStatus")
	public void isAudit(@ModelAttribute LecturerProfitQO qo, ModelMap modelMap) {
		modelMap.put("bean", qo);
	}

	/**
	 * 状态修改
	 * 
	 * @author WY
	 * @param qo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/status")
	public String audit(@ModelAttribute LecturerProfitQO qo) {
		if (biz.status(qo) > 0) {
			return success(TARGETID);
		}
		return error("状态修改失败");
	}

	/**
	 * 标记成功
	 * 
	 * @author WY
	 * @param qo
	 * @return
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/audit")
	public String audit(String ids) {
		biz.audit(ids);
		return delete(TARGETID);
	}

	/**
	 * 报表导出
	 * 
	 * @author WY
	 * @param qo
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/export", method = RequestMethod.GET)
	public void export(HttpServletResponse response, LecturerProfitQO qo) throws IOException {
		biz.export(response, qo);
	}

}
