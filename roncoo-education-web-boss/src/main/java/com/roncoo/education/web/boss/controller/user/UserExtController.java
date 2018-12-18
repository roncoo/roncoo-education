package com.roncoo.education.web.boss.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.roncoo.education.user.common.bean.qo.UserExtQO;
import com.roncoo.education.util.annotation.AdminLog;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.web.boss.biz.user.UserExtBiz;

/**
 * 用户教育信息
 *
 * @author wujing
 */
@Controller
@RequestMapping(value = "/user/userExt")
public class UserExtController extends BaseController {

	private final static String TARGETID = "user-userExt";

	@Autowired
	private UserExtBiz biz;

	@RequestMapping(value = "/list")
	public void list(@ModelAttribute UserExtQO qo, ModelMap modelMap) {
		modelMap.put("page", biz.listForPage(qo));
		modelMap.put("bean", qo);
	}

	@RequestMapping(value = "/add")
	public void add() {

	}

	@ResponseBody
	@RequestMapping(value = "/save")
	public String save(@ModelAttribute UserExtQO qo) {
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
	public String update(@ModelAttribute UserExtQO qo, MultipartFile headImgFile) {
		if (biz.updateById(qo, headImgFile) > 0) {
			return success(TARGETID);
		}
		return error("修改失败");
	}

	@RequestMapping(value = "/view")
	public void view(@RequestParam(value = "id") Long id, ModelMap modelMap) {
		modelMap.put("bean", biz.getById(id));
	}

	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public String get(@RequestParam(value = "userNo") Long userNo, ModelMap modelMap) {
		modelMap.put("bean", biz.getByUserNo(userNo));
		return "user/userExt/view";
	}

	@ResponseBody
	@AdminLog(value = "修改状态")
	@RequestMapping(value = "/updateStatusId", method = RequestMethod.POST)
	public String updateStatusId(@ModelAttribute UserExtQO qo) {
		if (biz.updateStatusId(qo) > 0) {
			return ties("修改成功");
		}
		return error("更新失败");
	}
}
