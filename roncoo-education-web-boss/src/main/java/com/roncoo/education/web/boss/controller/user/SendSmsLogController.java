package com.roncoo.education.web.boss.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.roncoo.education.user.common.bean.qo.SendSmsLogQO;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.web.boss.biz.user.SendSmsLogBiz;

/**
 * 用户发送短信日志
 *
 * @author YZJ
 */
@Controller
@RequestMapping(value = "/user/sendSmsLog")
public class SendSmsLogController extends BaseController {

	private final static String TARGETID = "user-sendSmsLog";

	@Autowired
	private SendSmsLogBiz biz;

	@RequestMapping(value = "/list")
	public void list(@ModelAttribute SendSmsLogQO qo, ModelMap modelMap) {
		modelMap.put("page", biz.listForPage(qo));
		modelMap.put("bean", qo);
	}

	@RequestMapping(value = "/add")
	public void add() {

	}

	@ResponseBody
	@RequestMapping(value = "/save")
	public String save(@ModelAttribute SendSmsLogQO qo) {
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
	public String update(@ModelAttribute SendSmsLogQO qo) {
		if (biz.updateById(qo) > 0) {
			return success(TARGETID);
		}
		return error("修改失败");
	}

	@RequestMapping(value = "/view")
	public void view(@RequestParam(value = "id") Long id, ModelMap modelMap) {
		modelMap.put("bean", biz.getById(id));
	}

	@ResponseBody
	@RequestMapping(value = "/send")
	public String sendPost(@ModelAttribute SendSmsLogQO qo) {
		if (biz.send(qo) > 0) {
			return success(TARGETID);
		}
		return error("发送失败");
	}

	@ResponseBody
	@RequestMapping(value = "/reSend")
	public String sendGet(@ModelAttribute SendSmsLogQO qo) {
		if (biz.send(qo) > 0) {
			return ties("重发成功");
		}
		return error("重发失败");
	}

}
