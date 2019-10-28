package com.roncoo.education.system.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 消息模板-保存
 *
 */
@Data
@Accessors(chain = true)
public class MsgTemplateSaveREQ implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 标题
	 */
	@ApiModelProperty(value = "标题", required = true)
	private String title;
	/**
	 * 备注
	 */
	@ApiModelProperty(value = "备注", required = true)
	private String remark;

}
