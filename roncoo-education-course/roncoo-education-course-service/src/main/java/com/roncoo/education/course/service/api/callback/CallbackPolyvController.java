/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.course.service.api.callback;

import javax.servlet.http.HttpServletRequest;

import com.roncoo.education.course.service.api.callback.biz.CallbackPolyvBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.polyv.PolyvAuth;
import com.roncoo.education.util.polyv.PolyvVideo;

import io.swagger.annotations.ApiOperation;

/**
 * 课时信息-审核
 *
 * @author wujing123
 */
@RestController
@RequestMapping(value = "/callback/polyv")
public class CallbackPolyvController extends BaseController {

    @Autowired
    private CallbackPolyvBiz biz;

    /**
     * 保利威视，视频上传回调接口
     */
    @ApiOperation(value = "保利威视，视频上传回调接口", notes = "保利威视，视频上传回调接口")
    @RequestMapping(value = "/video", method = {RequestMethod.POST, RequestMethod.GET})
    public String callbackPolyvVideo(PolyvVideo polyvVideo) {
        return biz.video(polyvVideo);
    }

    /**
     * 保利威视，视频授权播放回调接口
     */
    @ApiOperation(value = "保利威视，视频授权播放回调接口", notes = "保利威视，视频授权播放回调接口")
    @RequestMapping(value = "/auth", method = {RequestMethod.POST, RequestMethod.GET})
    public String callbackPolyvAuth(PolyvAuth polyvAuth, HttpServletRequest request) {
        if (StringUtils.isEmpty(polyvAuth.getCallback())) {
            String sourceParam = request.getQueryString();
            sourceParam = sourceParam.replaceAll("vid=" + polyvAuth.getVid(), "");
            sourceParam = sourceParam.replaceAll("&t=" + polyvAuth.getT(), "");
            sourceParam = sourceParam.replaceAll("&code=", "").replace("+", "%2B");
            polyvAuth.setCode(sourceParam);
        }
        return biz.auth(polyvAuth);
    }

}
