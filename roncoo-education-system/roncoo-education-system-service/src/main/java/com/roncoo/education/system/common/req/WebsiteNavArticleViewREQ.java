package com.roncoo.education.system.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 站点导航文章-查看
 */
@Data
@Accessors(chain = true)
public class WebsiteNavArticleViewREQ implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 导航ID
	 */
	@ApiModelProperty(value = "导航ID")
	private Long navId;
}
