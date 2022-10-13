package com.roncoo.education.common.upload.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.ObjectUtil;
import com.roncoo.education.common.core.base.BaseException;
import com.roncoo.education.common.upload.Upload;
import com.roncoo.education.common.upload.UploadFace;
import io.minio.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

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
        if (ObjectUtil.isEmpty(minioClient)) {
            throw new BaseException("请完成MinIO的配置");
        }

        try {
            String fileName = IdUtil.simpleUUID() + "." + FileUtil.getSuffix(file.getOriginalFilename());
            String filePath = uploadForMinio(upload.getMinioBucket(), "education", fileName, file.getName(), file.getContentType(), true, file.getInputStream());
            return getMinioFileUrl(upload.getMinioDomain(), filePath);
        } catch (Exception e) {
            log.error("MinIO上传错误", e);
        }
        return "";
    }

    /**
     * 上传文件到MiniIO
     *
     * @param bucketName bucket名称
     * @param fileDir    文件目录
     * @param fileName   文件名称
     * @param stream     文件流
     * @return 上传后的文件地址
     * @throws Exception 上传异常
     */
    private String uploadForMinio(String bucketName, String fileDir, String fileName, String fileOriginalName, String contentType, boolean publicRead, InputStream stream) throws Exception {
        if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build())) {
            // 创建Bucket
            minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            // 设置Bucket访问策略
            String policy = "{\"Version\":\"2012-10-17\",\"Statement\":[{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:GetBucketLocation\"],\"Resource\":[\"arn:aws:s3:::{bucket}\"]},{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:ListBucket\"],\"Resource\":[\"arn:aws:s3:::{bucket}\"],\"Condition\":{\"StringEquals\":{\"s3:prefix\":[\"public/**\"]}}},{\"Effect\":\"Allow\",\"Principal\":{\"AWS\":[\"*\"]},\"Action\":[\"s3:GetObject\"],\"Resource\":[\"arn:aws:s3:::{bucket}/public/***\"]}]}\n";
            minioClient.setBucketPolicy(SetBucketPolicyArgs.builder().bucket(bucketName).config(policy.replace("{bucket}", bucketName)).build());
        }

        // 处理前缀目录
        String objectName = StringUtils.hasText(fileDir) ? fileDir + "/" + fileName : fileName;

        // 设置文件下载名称
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileOriginalName, "UTF-8"));
        if (StringUtils.hasText(contentType)) {
            headerMap.put("Content-Type", contentType);
        }

        minioClient.putObject(PutObjectArgs.builder().bucket(bucketName).object(objectName).headers(headerMap).stream(stream, stream.available(), -1).build());
        return bucketName + "/" + objectName;
    }

    private String getMinioFileUrl(String miniFileDomain, String filePath) {
        if (miniFileDomain.endsWith("/")) {
            return miniFileDomain + filePath;
        } else {
            return miniFileDomain + "/" + filePath;
        }
    }

}
