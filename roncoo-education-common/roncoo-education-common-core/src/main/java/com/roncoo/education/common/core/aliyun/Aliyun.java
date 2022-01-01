package com.roncoo.education.common.core.aliyun;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class Aliyun implements Serializable {

    private static final long serialVersionUID = 1L;

    private String polyvUseid;
    private String polyvWritetoken;
    private String polyvReadtoken;
    private String polyvSecretkey;
    private String aliyunAccessKeyId;
    private String aliyunAccessKeySecret;
    private String aliyunOssUrl;
    private String aliyunOssBucket;
    private String aliyunOssEndpoint;
    private String aliyunOasVault;
    private String payUrl;
    private String payKey;
    private String paySecret;
    private String notifyUrl;
    private String smsCode;
    private String signName;
}
