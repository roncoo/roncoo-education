package com.roncoo.education.user.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 用户查看信息
 *
 */
@Data
@Accessors(chain = true)
public class UserExtViewREQ implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户主键
	 */
	@ApiModelProperty(value = "用户主键")
	private Long id;
	/**
	 * 用户编号
	 */
	@ApiModelProperty(value = "用户编号")
	private Long orderUserNo;

}
