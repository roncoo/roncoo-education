package com.roncoo.education.web.boss.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.roncoo.education.system.common.bean.qo.WebsiteQO;
import com.roncoo.education.util.annotation.AdminLog;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.web.boss.biz.system.WebsiteBiz;

/**
 * 站点信息
 *
 * @author wuyun
 */
@Controller
@RequestMapping(value = "/system/website")
public class WebsiteController extends BaseController {

	private final static String TARGETID = "system-website";

	@Autowired
	private WebsiteBiz biz;

	@RequestMapping(value = "/list")
	public void list(@ModelAttribute WebsiteQO qo, ModelMap modelMap) {
		modelMap.put("page", biz.listForPage(qo));
		modelMap.put("bean", qo);
	}

	@RequestMapping(value = "/add")
	public void add() {

	}

	@ResponseBody
	@RequestMapping(value = "/save")
	public String save(@ModelAttribute WebsiteQO qo) {
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
	public String update(@ModelAttribute WebsiteQO qo) {
		if (biz.updateById(qo) > 0) {
			return success(TARGETID);
		}
		return error("修改失败");
	}

	@RequestMapping(value = "/view")
	public void view(@RequestParam(value = "id") Long id, ModelMap modelMap) {
		modelMap.put("bean", biz.getById(id));
	}

	@RequestMapping(value = "/set", method = RequestMethod.GET)
	public void set(ModelMap modelMap) {
		// 加载站点信息
		modelMap.put("bean", biz.getWebsite());
	}

	/**
	 * 站点信息更新
	 * 
	 * @author wuyun
	 */
	@ResponseBody
	@AdminLog(value = "站点信息更新")
	@RequestMapping(value = "/updateWebsite", method = RequestMethod.POST)
	public String updateWebsite(@ModelAttribute WebsiteQO qo, @RequestParam(value = "weixinFile", required = false) MultipartFile weixinFile, @RequestParam(value = "weixinXcxFile", required = false) MultipartFile weixinXcxFile, @RequestParam(value = "weiboFile", required = false) MultipartFile weiboFile, @RequestParam(value = "logoImgFile", required = false) MultipartFile logoImgFile, @RequestParam(value = "logoIcoFile", required = false) MultipartFile logoIcoFile) {
		// 保存或修改站点信息
		if (null != qo.getId()) {
			if (biz.updateWebsite(qo, weixinFile, weixinXcxFile, weiboFile, logoImgFile, logoIcoFile) > 0) {
				return success(TARGETID);
			}
		} else {
			if (biz.saveWebsite(qo, weixinFile, weixinXcxFile, weiboFile, logoImgFile, logoIcoFile) > 0) {
				return success(TARGETID);
			}
		}
		return error("修改失败");
	}
}
