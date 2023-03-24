package com.roncoo.education.course.service.api;

import com.roncoo.education.common.service.BaseController;
import com.roncoo.education.common.video.impl.polyv.vod.CallbackVodUpload;
import com.roncoo.education.course.service.api.biz.ApiCallbackBiz;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 专区课程关联表
 *
 * @author wuyun
 */
@RestController
@RequestMapping(value = "/course/api/callback")
public class ApiCallbackController extends BaseController {

    @Autowired
    private ApiCallbackBiz biz;

    /**
     * 专区课程分页列表接口
     *
     * @param bo
     * @return
     */
    @ApiOperation(value = "保利威点播上传回调接口", notes = "保利威点播上传回调接口")
    @RequestMapping(value = "/polyv/upload", method = {RequestMethod.POST, RequestMethod.GET})
    public String polyvUpload(CallbackVodUpload callbackVodUpload) {
        return biz.polyvUpload(callbackVodUpload);
    }

}
