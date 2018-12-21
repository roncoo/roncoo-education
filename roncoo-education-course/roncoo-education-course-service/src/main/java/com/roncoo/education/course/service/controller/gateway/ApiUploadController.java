/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.course.service.controller.gateway;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.roncoo.education.course.common.interfaces.gateway.ApiUpload;
import com.roncoo.education.course.service.biz.gateway.ApiUploadBiz;
import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;

/**
 * 上传接口
 * 
 * @author wuyun
 */
@RestController
public class ApiUploadController extends BaseController implements ApiUpload {

	@Autowired
	private ApiUploadBiz biz;

	@Override
	public Result<String> uploadVideo(@RequestParam(value = "videoFile", required = false) MultipartFile videoFile) {
		return biz.uploadVideo(videoFile);
	}

	@Override
	public Result<String> uploadPic(@RequestParam(value = "picFile", required = false) MultipartFile picFile) {
		return biz.uploadPic(picFile);
	}
	
	@Override
	public Result<String> uploadDoc(@RequestParam(name = "docFile", required = false) MultipartFile docFile, @PathVariable(name = "periodId") Long periodId) {
		return biz.uploadDoc(docFile, periodId);
	}

}
