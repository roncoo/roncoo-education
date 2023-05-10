/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.system.service.biz;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.Result;
import com.roncoo.education.common.core.enums.StoragePlatformEnum;
import com.roncoo.education.common.core.tools.FileUtils;
import com.roncoo.education.common.upload.Upload;
import com.roncoo.education.common.upload.UploadFace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

/**
 * 上传接口
 *
 * @author wuyun
 */
@Slf4j
@Component
public class UploadCommonBiz {

    @Autowired
    private Map<String, UploadFace> uploadFaceMap;

    @Autowired
    private SysConfigCommonBiz sysConfigCommonBiz;

    public Result<String> uploadPic(MultipartFile picFile) {
        if (!FileUtils.isPic(picFile)) {
            return Result.error("请选择图片上传");
        }

        Upload upload = sysConfigCommonBiz.getSysConfig(Upload.class);
        if (ObjectUtil.isNotEmpty(upload) || StringUtils.isEmpty(upload.getStoragePlatform())) {
            UploadFace uploadFace = uploadFaceMap.get(StoragePlatformEnum.byCode(Integer.valueOf(upload.getStoragePlatform())).getMode());
            String fileUrl = uploadFace.uploadPic(picFile, upload);
            return Result.success(fileUrl);
        }
        return Result.error("上传参数没配置");
    }

    public Result<String> uploadDoc(MultipartFile docFile) {
        return null;
    }

}
