package com.roncoo.education.system.service.common.req;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 系统配置表-查看
 */
@Data
@Accessors(chain = true)
public class SysUpdateRESQ implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键id")
	@JsonSerialize(using = ToStringSerializer.class)
	private Long id;
	/**
	 * 视频存储平台（1保利威视，2七牛）
	 */
	@ApiModelProperty(value = "视频存储平台（1保利威视，2七牛）")
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
	 * 文件存储类型（1阿里云，2七牛）
	 */
	@ApiModelProperty(value = "文件存储类型（1阿里云，2七牛）")
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
	 * 支付通道（1龙果支付，2其他）
	 */
	@ApiModelProperty(value = "支付通道（1龙果支付，2其他）")
	private Integer payType;
	/**
	 * roncoo_key
	 */
	@ApiModelProperty(value = "roncoo_key")
	private String roncooKey;
	/**
	 * roncoo_secret
	 */
	@ApiModelProperty(value = "roncoo_secret")
	private String roncooSecret;
}
