package com.roncoo.education.web.boss.controller.system;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.roncoo.education.system.common.bean.qo.MsgQO;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.enums.IsDefaultEnum;
import com.roncoo.education.util.tools.DateUtil;
import com.roncoo.education.web.boss.biz.system.MsgBiz;

/**
 * 站内信息表
 *
 * @author wuyun
 */
@Controller
@RequestMapping(value = "/system/msg")
public class MsgController extends BaseController {

	private final static String TARGETID = "system-msg";

	@Autowired
	private MsgBiz biz;

	@RequestMapping(value = "/list")
	public void list(@ModelAttribute MsgQO qo, ModelMap modelMap) {
		modelMap.put("page", biz.listForPage(qo));
		modelMap.put("bean", qo);
	}

	@RequestMapping(value = "/add")
	public void add() {

	}

	@ResponseBody
	@RequestMapping(value = "/save")
	public String save(@ModelAttribute MsgQO qo, String sendTimeString) {
		if (StringUtils.isEmpty(qo.getMsgTitle())) {
			return error("标题不能为空");
		}
		if (IsDefaultEnum.YES.getCode().equals(qo.getIsTimeSend())) {// 开启定时发送
			// 时间设置不合理
			if (StringUtils.isEmpty(sendTimeString) || DateUtil.parseDate(sendTimeString, "yyyy-MM-dd HH:mm:ss").before(new Date())) {
				return error("请设置合理时间");
			}
			qo.setSendTime(DateUtil.parseDate(sendTimeString, "yyyy-MM-dd HH:mm:ss"));
		}
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
	public String update(@ModelAttribute MsgQO qo) {
		if (biz.updateById(qo) > 0) {
			return success(TARGETID);
		}
		return error("修改失败");
	}

	@RequestMapping(value = "/view")
	public void view(@RequestParam(value = "id") Long id, ModelMap modelMap) {
		modelMap.put("bean", biz.getById(id));
	}

	// 手动推送站内信
	@ResponseBody
	@RequestMapping(value = "/push")
	public String push(@RequestParam(value = "id") Long id) {
		if (biz.pushByManual(id) > 0) {
			return ties("发送开始");
		}
		return error("发送失败，请重试");
	}

}
