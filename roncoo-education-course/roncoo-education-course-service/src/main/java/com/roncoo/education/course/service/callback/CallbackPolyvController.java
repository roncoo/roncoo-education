/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.course.service.callback;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.polyv.PolyvAuth;
import com.roncoo.education.common.core.polyv.PolyvVideo;
import com.roncoo.education.course.service.callback.biz.CallbackPolyvBiz;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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
