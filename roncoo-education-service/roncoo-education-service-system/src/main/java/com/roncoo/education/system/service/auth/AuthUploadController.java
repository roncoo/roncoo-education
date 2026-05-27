package com.roncoo.education.system.service.auth;

import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.base.BaseController;
import com.roncoo.education.system.service.biz.UploadCommonBiz;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.constraints.NotNull;

import java.io.File;

/**
 * 上传接口
 *
 * @author wuyun
 */
@Tag(name = "auth-上传接口")
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/system/auth/upload")
public class AuthUploadController extends BaseController {

    @NotNull
    private final UploadCommonBiz biz;

    @Operation(summary = "上传图片", description = "服务端上传图片接口，只支持图片格式")
    @Parameter(name = "picFile", description = "图片文件", required = true)
    @PostMapping(value = "/pic")
    public Result<String> uploadPic(@RequestParam(value = "picFile", required = false) MultipartFile picFile) {
        return biz.uploadPic(picFile);
    }

}
