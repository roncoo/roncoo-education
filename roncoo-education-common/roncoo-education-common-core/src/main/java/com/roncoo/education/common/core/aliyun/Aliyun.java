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

    private String aliyunAccessKeyId;
    private String aliyunAccessKeySecret;
    private String aliyunOssUrl;
    private String aliyunOssEndpoint;
    private String aliyunOssBucket;

//    // 短信签名
//    private String aliyunSmsSignName;
//    private String aliyunSmsAccessKeyId;
//    private String aliyunSmsAccessKeySecret;
//    private String aliyunSmsAuthCode;

}
