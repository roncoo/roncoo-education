package com.roncoo.education.web.boss.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.roncoo.education.web.boss.biz.system.WebsiteNavBiz;
import com.roncoo.education.system.common.bean.qo.WebsiteNavQO;
import com.roncoo.education.util.base.BaseController;

/**
 * 站点导航
 *
 * @author wuyun
 */
@Controller
@RequestMapping(value = "/system/websiteNav")
public class WebsiteNavController extends BaseController {

	private final static String TARGETID = "system-websiteNav";

	@Autowired
	private WebsiteNavBiz biz;

	@RequestMapping(value = "/list")
	public void list(@ModelAttribute WebsiteNavQO qo, ModelMap modelMap) {
		modelMap.put("page", biz.listForPage(qo));
		modelMap.put("bean", qo);
	}

	@RequestMapping(value = "/add")
	public void add(@RequestParam(value = "parentId") Long parentId, ModelMap modelMap) {
		modelMap.put("parentId", parentId);
	}

	@ResponseBody
	@RequestMapping(value = "/save")
	public String save(@ModelAttribute WebsiteNavQO qo) {
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
	public String update(@ModelAttribute WebsiteNavQO qo) {
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
	@RequestMapping(value = "/updateStatusId")
	public String updateStatusId(@ModelAttribute WebsiteNavQO qo) {
		if (biz.updateStatusId(qo) > 0) {
			return ties("修改成功");
		}
		return error("修改失败");
	}
}
