package com.roncoo.education.common.upload.impl;

import com.roncoo.education.common.core.aliyun.Aliyun;
import com.roncoo.education.common.core.aliyun.AliyunOssUtil;
import com.roncoo.education.common.core.tools.BeanUtil;
import com.roncoo.education.common.upload.Upload;
import com.roncoo.education.common.upload.UploadFace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author fengyw
 */
@Slf4j
@Component(value = "aliyun")
public class AliyunUploadImpl implements UploadFace {

    @Override
    public String uploadPic(MultipartFile file, Upload upload) {
        Aliyun aliyun = BeanUtil.copyProperties(upload, Aliyun.class);
        return AliyunOssUtil.uploadPic(file, aliyun);
    }

    @Override
    public String uploadDoc(MultipartFile file, Upload upload) {
        Aliyun aliyun = BeanUtil.copyProperties(upload, Aliyun.class);
        return AliyunOssUtil.uploadDoc(file, aliyun);
    }

}
