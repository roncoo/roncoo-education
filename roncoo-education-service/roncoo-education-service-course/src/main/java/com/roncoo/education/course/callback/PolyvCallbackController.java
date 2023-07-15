package com.roncoo.education.course.callback;

import com.roncoo.education.common.service.BaseController;
import com.roncoo.education.common.video.impl.polyv.vod.CallbackVodUpload;
import com.roncoo.education.course.callback.biz.PolyvCallbackBiz;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
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
    public String polyvUpload(CallbackVodUpload callbackVodUpload) {
        return biz.polyvUpload(callbackVodUpload);
    }

}
