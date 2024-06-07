package com.roncoo.education.common.upload.impl;

import cn.hutool.core.util.IdUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.ObjectMetadata;
import com.roncoo.education.common.upload.Upload;
import com.roncoo.education.common.upload.UploadFace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author fengyw
 */
@Slf4j
@Component(value = "aliyun")
public class AliyunUploadImpl implements UploadFace {

    private final static String PREFIX = "education";

    @Override
    public String uploadPic(MultipartFile file, Upload upload) {
        return putObject(file, upload, CannedAccessControlList.PublicRead);
    }

    @Override
    public String uploadDoc(MultipartFile file, Upload upload, Boolean isPublicRead) {
        return putObject(file, upload, isPublicRead ? CannedAccessControlList.PublicRead : CannedAccessControlList.Private);
    }

    @Override
    public String getDownloadUrl(String docUrl, int expireSeconds, Upload upload) {
        return "";
    }

    @Override
    public String getPreviewConfig(String docUrl, int expireSeconds, Upload upload) {
        return "";
    }

    /**
     * 存入OSS
     */
    private static String putObject(MultipartFile file, Upload upload, CannedAccessControlList cannedAcl) {
        InputStream inputStream = null;
        // 上传
        try {
            inputStream = file.getInputStream();
            String name = file.getOriginalFilename();
            String filePath = PREFIX + "/" + IdUtil.simpleUUID() + name.substring(name.lastIndexOf("."));
            OSS ossClient = new OSSClientBuilder().build(upload.getAliyunOssEndpoint(), upload.getAliyunAccessKeyId(), upload.getAliyunAccessKeySecret());
            ObjectMetadata meta = new ObjectMetadata();
            meta.setContentEncoding("UTF-8");
            meta.setContentDisposition("attachment;filename={}".replace("{}", name));
            meta.setObjectAcl(cannedAcl);
            ossClient.putObject(upload.getAliyunOssBucket(), filePath, inputStream, meta);
            return upload.getAliyunOssUrl() + filePath;
        } catch (Exception e) {
            log.error("上传失败", e);
            return "";
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }
        }
    }
}
