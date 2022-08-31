package com.roncoo.education.common.upload.impl;

import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.BaseException;
import com.roncoo.education.common.upload.Upload;
import com.roncoo.education.common.upload.UploadFace;
import io.minio.MinioClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Component(value = "minio")
public class MinIOUploadImpl implements UploadFace {

    /**
     * 按需引入，需要打开jar
     */
    @Autowired(required = false)
    private MinioClient minioClient;

    @Override
    public String uploadPic(MultipartFile file, Upload upload) {
        if(ObjectUtil.isEmpty(minioClient)){
            throw new BaseException("请完成MinIO的配置");
        }
        return null;
    }

}
