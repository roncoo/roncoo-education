package com.roncoo.education.user.common.bo.auth;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户教育信息
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class AuthUserExtViewBO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户编号
	 */
	@ApiModelProperty(value = "用户编号")
	private Long userNo;
}
