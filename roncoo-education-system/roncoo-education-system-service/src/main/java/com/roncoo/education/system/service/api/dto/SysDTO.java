package com.roncoo.education.system.service.api.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 系统配置表
 *
 * @author YZJ
 */
@Data
@Accessors(chain = true)
public class SysDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 创建时间
     */
    private Date gmtCreate;
    /**
     * 修改时间
     */
    private Date gmtModified;
    /**
     * 状态(1有效, 0无效)
     */
    private Integer statusId;
    /**
     * 排序
     */
    private Integer sort;
    /**
     * 视频存储平台（1保利威视，2其他）
     */
    private Integer videoType;
    /**
     * useid
     */
    private String polyvUseid;
    /**
     * writetoken
     */
    private String polyvWritetoken;
    /**
     * readtoken
     */
    private String polyvReadtoken;
    /**
     * secretkey
     */
    private String polyvSecretkey;
    /**
     * 文件存储类型（1阿里云，2FDSF）
     */
    private Integer fileType;
    /**
     * access_key_id
     */
    private String aliyunAccessKeyId;
    /**
     * access_key_secret
     */
    private String aliyunAccessKeySecret;
    /**
     * 支付通道（1龙果支付，2其他）
     */
    private Integer payType;
    /**
     * roncoo_key
     */
    private String roncooKey;
    /**
     * roncoo_secret
     */
    private String roncooSecret;
}
