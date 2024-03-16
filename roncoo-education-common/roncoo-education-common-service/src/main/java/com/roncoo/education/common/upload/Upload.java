package com.roncoo.education.common.upload;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 上传接口
 *
 * @author LYQ
 */
@Data
@Accessors(chain = true)
public class Upload implements Serializable {

    private static final long serialVersionUID = 1195869049655301491L;

    /**
     * MinIO
     */
    private String minioEndpoint;
    private String minioAccessKey;
    private String minioSecretKey;
    private String minioDomain;
    private String minioBucket;
    private String minioPreviewUrl;

    /**
     * OSS
     */
    private String aliyunOssEndpoint;
    private String aliyunAccessKeyId;
    private String aliyunAccessKeySecret;
    private String aliyunOssUrl;
    private String aliyunOssBucket;


    /**
     * 存储平台，参考：UploadModeEnum
     */
    private Integer storagePlatform;

    /**
     * 站点域名
     */
    private String websiteDomain;
}
