package com.roncoo.education.system.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 *
 * 底部导航-添加
 *
 */
@Data
@Accessors(chain = true)
public class WebsiteNavSaveREQ implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 父ID
	 */
	@ApiModelProperty(value = "父ID", required = false)
	private Long parentId;
	/**
	 * 导航名称
	 */
	@ApiModelProperty(value = "导航名称", required = true)
	private String navName;

}
