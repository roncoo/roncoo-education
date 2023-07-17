package com.roncoo.education.system.service.auth;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.service.BaseController;
import com.roncoo.education.system.service.biz.UploadCommonBiz;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

/**
 * 上传接口
 *
 * @author wuyun
 */
@Api(tags = "auth-上传接口")
@RestController
@RequestMapping(value = "/system/auth/upload")
public class AuthUploadController extends BaseController {

    @Autowired
    private UploadCommonBiz biz;

    @ApiOperation(value = "上传图片", notes = "服务端上传图片接口，只支持图片格式")
    @ApiImplicitParam(name = "picFile", value = "图片文件", dataType = "File", dataTypeClass = File.class, paramType = "query", required = true)
    @PostMapping(value = "/pic")
    public Result<String> uploadPic(@RequestParam(value = "picFile", required = false) MultipartFile picFile) {
        return biz.uploadPic(picFile);
    }

}
