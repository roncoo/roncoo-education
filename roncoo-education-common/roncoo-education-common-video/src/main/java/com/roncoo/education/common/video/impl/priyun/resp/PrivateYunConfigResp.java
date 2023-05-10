package com.roncoo.education.common.video.impl.priyun.resp;

import lombok.Data;

@Data
public class PrivateYunConfigResp {

    private String uploadUrl;

    private String callbackUrl;

    private String accessKeyId;

    private String state;

    private String tags;

    private String signatureNonce;

    private String sign;
}
