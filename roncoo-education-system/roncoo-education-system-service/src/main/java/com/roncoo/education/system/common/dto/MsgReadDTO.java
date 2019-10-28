package com.roncoo.education.system.common.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 站内信息表
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class MsgReadDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 用户站内信未读条数
	 */
	@ApiModelProperty(value = "用户站内信未读条数")
	private Integer num;
}
