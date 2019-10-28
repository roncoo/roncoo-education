package com.roncoo.education.system.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 站内信消息-删除
 *
 */
@Data
@Accessors(chain = true)
public class MsgViewREQ implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 主键ID
	 */
	@ApiModelProperty(value = "主键ID", required = true)
	private Long id;

}
