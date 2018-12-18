/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.course.common.interfaces.gateway.callback;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.roncoo.education.util.polyv.PolyvAuth;
import com.roncoo.education.util.polyv.PolyvVideo;

import io.swagger.annotations.ApiOperation;

/**
 * 回调接口
 *
 * @author wujing
 */
public interface CallbackPolyv {

	/**
	 * 保利威视，视频上传回调接口
	 */
	@ApiOperation(value = "保利威视，视频上传回调接口", notes = "保利威视，视频上传回调接口")
	@RequestMapping(value = "/callback/polyv/video", method = { RequestMethod.POST, RequestMethod.GET })
	String callbackPolyvVideo(PolyvVideo polyvVideo);

	/**
	 * 保利威视，视频授权播放回调接口
	 */
	@ApiOperation(value = "保利威视，视频授权播放回调接口", notes = "保利威视，视频授权播放回调接口")
	@RequestMapping(value = "/callback/polyv/auth", method = { RequestMethod.POST, RequestMethod.GET })
	String callbackPolyvAuth(PolyvAuth polyvAuth, HttpServletRequest request);

}
