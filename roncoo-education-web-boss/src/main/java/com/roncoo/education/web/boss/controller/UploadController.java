/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.web.boss.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.web.boss.biz.UploadBiz;

@Controller
public class UploadController extends BaseController {

	@Autowired
	private UploadBiz biz;

	/**
	 * 本地上传图片
	 * 
	 * @param descFile
	 * @return
	 * @author YZJ
	 */
	@ResponseBody
	@RequestMapping(value = "/uploadPic")
	public Object uploadPic(MultipartFile descFile) {
		return biz.uploadPic(descFile);
	}
}
