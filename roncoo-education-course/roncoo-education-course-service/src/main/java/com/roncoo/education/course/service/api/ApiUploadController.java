/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.course.service.api;

import com.roncoo.education.common.core.base.BaseController;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.course.service.api.biz.ApiUploadBiz;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 上传接口
 *
 * @author wuyun
 */
@RestController
@RequestMapping(value = "/course/api/upload")
public class ApiUploadController extends BaseController {

    @Autowired
    private ApiUploadBiz biz;

    /**
     * 上传视频接口
     *
     * @param videoFile
     * @author wuyun
     */
    @ApiOperation(value = "上传视频接口", notes = "上传视频")
    @RequestMapping(value = "/video", method = RequestMethod.POST)
    public Result<String> uploadVideo(@RequestParam(value = "videoFile", required = false) MultipartFile videoFile) {
        return biz.uploadVideo(videoFile);
    }

    /**
     * 上传图片接口
     *
     * @param picFile
     * @author wuyun
     */
    @ApiOperation(value = "上传图片接口", notes = "上传图片")
    @RequestMapping(value = "/pic", method = RequestMethod.POST)
    public Result<String> uploadPic(@RequestParam(value = "picFile", required = false) MultipartFile picFile) {
        return biz.uploadPic(picFile);
    }

    /**
     * 上传文档接口
     *
     * @param docFile
     * @param periodUploadDoc
     * @author wuyun
     */
    @ApiOperation(value = "上传文档接口", notes = "上传文档")
    @RequestMapping(value = "/doc", method = RequestMethod.POST)
    public Result<String> uploadDoc(@RequestParam(name = "docFile", required = false) MultipartFile docFile) {
        return biz.uploadDoc(docFile);
    }

}
