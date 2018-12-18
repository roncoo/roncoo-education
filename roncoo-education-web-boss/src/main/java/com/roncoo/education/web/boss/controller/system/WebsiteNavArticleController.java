package com.roncoo.education.web.boss.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.roncoo.education.web.boss.biz.system.WebsiteNavArticleBiz;
import com.roncoo.education.system.common.bean.qo.WebsiteNavArticleQO;
import com.roncoo.education.util.base.BaseController;

/**
 * 站点导航文章
 *
 * @author wuyun
 */
@Controller
@RequestMapping(value = "/system/websiteNavArticle")
public class WebsiteNavArticleController extends BaseController {

	private final static String TARGETID = "system-websiteNavArticle";

	private final static String WEBSITENAV = "system-websiteNav";
	
	@Autowired
	private WebsiteNavArticleBiz biz;

	@RequestMapping(value = "/list")
	public void list(@ModelAttribute WebsiteNavArticleQO qo, ModelMap modelMap) {
		modelMap.put("page", biz.listForPage(qo));
		modelMap.put("bean", qo);
	}

	@RequestMapping(value = "/add")
	public void add(@ModelAttribute WebsiteNavArticleQO qo, ModelMap modelMap) {
		modelMap.put("bean", qo);
	}

	@ResponseBody
	@RequestMapping(value = "/save")
	public String save(@ModelAttribute WebsiteNavArticleQO qo, MultipartFile artPicFile) {
		if (biz.save(qo, artPicFile) > 0) {
			return success(WEBSITENAV);
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
	public void edit(@RequestParam(value = "navId") Long navId, ModelMap modelMap) {
		modelMap.put("bean", biz.getByNavId(navId));
	}

	@ResponseBody
	@RequestMapping(value = "/update")
	public String update(@ModelAttribute WebsiteNavArticleQO qo, MultipartFile artPicFile) {
		if (biz.updateById(qo, artPicFile) > 0) {
			return success(WEBSITENAV);
		}
		return error("修改失败");
	}

	@RequestMapping(value = "/view")
	public void view(@RequestParam(value = "id") Long id, ModelMap modelMap) {
		modelMap.put("bean", biz.getById(id));
	}

}
