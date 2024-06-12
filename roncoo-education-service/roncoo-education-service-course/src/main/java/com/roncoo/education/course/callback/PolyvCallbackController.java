package com.roncoo.education.course.callback;

import com.roncoo.education.common.service.BaseController;
import com.roncoo.education.common.video.impl.polyv.live.*;
import com.roncoo.education.common.video.impl.polyv.vod.CallbackVodUpload;
import com.roncoo.education.course.callback.biz.PolyvCallbackBiz;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;

/**
 * 保利威
 *
 * @author wujing
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/course/callback/polyv")
public class PolyvCallbackController extends BaseController {

    @NotNull
    private final PolyvCallbackBiz biz;

    /**
     * 上传回调
     *
     * @param callbackVodUpload
     * @return
     */
    @ApiOperation(value = "点播上传回调接口", notes = "点播上传回调接口")
    @RequestMapping(value = "/vod/upload", method = {RequestMethod.POST, RequestMethod.GET})
    public String vodUpload(CallbackVodUpload callbackVodUpload) {
        return biz.vodUpload(callbackVodUpload);
    }

    @ApiOperation(value = "直播状态回调", notes = "直播状态回调")
    @GetMapping(value = "/live/status")
    public String liveStatus(CallbackLiveStatus callbackLiveStatus) {
        return biz.liveStatus(callbackLiveStatus);
    }

    @ApiOperation(value = "直播授观看授权", notes = "直播授观看授权")
    @RequestMapping(value = "/live/auth", method = {RequestMethod.POST, RequestMethod.GET})
    public String liveAuth(CallbackLiveAuth callbackLiveAuth) {
        return biz.liveAuth(callbackLiveAuth);
    }

    @ApiOperation(value = "直播回放生成", notes = "直播回放生成")
    @GetMapping(value = "/live/playback")
    public String livePlayback(CallbackLivePlayback callbackLivePlayback) {
        return biz.livePlayback(callbackLivePlayback);
    }

    @ApiOperation(value = "直播回放转存", notes = "直播回放转存")
    @GetMapping(value = "/live/convert")
    public String liveConvert(CallbackLiveConvert callbackLiveConvert) {
        return biz.liveConvert(callbackLiveConvert);
    }

    @ApiOperation(value = "直播课件重制", notes = "直播课件重制")
    @GetMapping(value = "/live/refashion")
    public String liveRefashion(CallbackLiveRefashion callbackLiveRefashion) {
        return biz.liveRefashion(callbackLiveRefashion);
    }
}
