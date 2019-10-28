package com.roncoo.education.system.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 站点导航
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class WebsiteNavListDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 站点导航集合
	 */
	@ApiModelProperty(value = "站点导航集合")
	private List<WebsiteNavDTO> websiteNavList = new ArrayList<>();
}
