/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.course.common.interfaces.gateway;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 上传接口
 * 
 * @author wujing123
 */
public interface ApiUpload {

	/**
	 * 上传视频接口
	 * 
	 * @param videoFile
	 * @author wuyun
	 */
	@ApiOperation(value = "上传视频接口", notes = "上传视频")
	@RequestMapping(value = "/course/api/upload/video", method = RequestMethod.POST)
	Result<String> uploadVideo(@RequestParam(name = "videoFile", required = false) MultipartFile videoFile);
	
	/**
	 * 上传图片接口
	 * 
	 * @param picFile
	 * @author wuyun
	 */
	@ApiOperation(value = "上传图片接口", notes = "上传图片")
	@RequestMapping(value = "/course/api/upload/pic", method = RequestMethod.POST)
	Result<String> uploadPic(@RequestParam(name = "picFile", required = false) MultipartFile picFile);

	/**
	 * 上传文档接口
	 * 
	 * @param docFile
	 * @param periodId
	 * @author wuyun
	 */
	@ApiOperation(value = "上传文档接口", notes = "上传文档")
	@RequestMapping(value = "/course/api/upload/doc/{periodId}", method = RequestMethod.POST)
	Result<String> uploadDoc(@RequestParam(name = "docFile", required = false) MultipartFile docFile, @PathVariable(name = "periodId") Long periodId);

}
