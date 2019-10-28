/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.course.service.api.pc;

import com.roncoo.education.course.service.api.pc.biz.PcApiUploadBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.roncoo.education.util.base.BaseController;
import com.roncoo.education.util.base.Result;

import io.swagger.annotations.ApiOperation;

/**
 * 上传接口
 */
@RestController
@RequestMapping(value = "/course/pc/api/upload")
public class PcApiUploadController extends BaseController {

    @Autowired
    private PcApiUploadBiz biz;

    /**
     * 上传图片接口
     *
     * @param picFile
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
     */
    @ApiOperation(value = "上传文档接口", notes = "上传文档")
    @RequestMapping(value = "/doc", method = RequestMethod.POST)
    public Result<String> uploadDoc(@RequestParam(name = "docFile", required = false) MultipartFile docFile) {
        return biz.uploadDoc(docFile);
    }

}
