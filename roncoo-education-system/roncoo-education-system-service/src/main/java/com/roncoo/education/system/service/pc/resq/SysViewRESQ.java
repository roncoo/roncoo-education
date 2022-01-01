package com.roncoo.education.system.service.pc.resq;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 系统配置表-查看
 */
@Data
@Accessors(chain = true)
public class SysViewRESQ implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 视频存储平台（1保利威视，2其他）
     */
    @ApiModelProperty(value = "视频存储平台（1保利威视，2其他）")
    private Integer videoType;
    /**
     * useid
     */
    @ApiModelProperty(value = "useid")
    private String polyvUseid;
    /**
     * writetoken
     */
    @ApiModelProperty(value = "writetoken")
    private String polyvWritetoken;
    /**
     * readtoken
     */
    @ApiModelProperty(value = "readtoken")
    private String polyvReadtoken;
    /**
     * secretkey
     */
    @ApiModelProperty(value = "secretkey")
    private String polyvSecretkey;
    /**
     * 文件存储类型（1阿里云，2FDSF）
     */
    @ApiModelProperty(value = "文件存储类型（1阿里云，2FDSF）")
    private Integer fileType;
    /**
     * access_key_id
     */
    @ApiModelProperty(value = "access_key_id")
    private String aliyunAccessKeyId;
    /**
     * access_key_secret
     */
    @ApiModelProperty(value = "access_key_secret")
    private String aliyunAccessKeySecret;
    /**
     * aliyunOssUrl
     */
    @ApiModelProperty(value = "aliyunOssUrl")
    private String aliyunOssUrl;
    /**
     * aliyunOssBucket
     */
    @ApiModelProperty(value = "aliyunOssBucket")
    private String aliyunOssBucket;

    @ApiModelProperty(value = "Endpoint（地域节点）")
    private String aliyunOssEndpoint;

    @ApiModelProperty(value = "视频Bucket")
    private String aliyunOasVault;
    /**
     * smsCode
     */
    @ApiModelProperty(value = "smsCode")
    private String smsCode;
    /**
     * 短信签名
     */
    @ApiModelProperty(value = "短信签名")
    private String signName;
    /**
     * 支付通道（1龙果支付，2其他）
     */
    @ApiModelProperty(value = "支付通道（1龙果支付，2其他）")
    private Integer payType;
    /**
     * roncooKey
     */
    @ApiModelProperty(value = "roncooKey")
    private String payKey;
    /**
     * roncooSecret
     */
    @ApiModelProperty(value = "roncooSecret")
    private String paySecret;
    /**
     * 支付请求
     */
    @ApiModelProperty(value = "支付请求")
    private String payUrl;
    /**
     * 回调地址
     */
    @ApiModelProperty(value = "回调地址")
    private String notifyUrl;
}
