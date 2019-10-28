package com.roncoo.education.user.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户发送短信日志-发送验证码
 *
 */
@Data
@Accessors(chain = true)
public class SendSmsLogSendREQ implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 手机号码
	 */
	@ApiModelProperty(value = "手机号码", required = true)
	private String mobile;

}
