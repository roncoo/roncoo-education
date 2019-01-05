package com.roncoo.education.system.common.bean.bo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 招募中心
 *
 * @author YZJ
 */
@Data
@Accessors(chain = true)
public class WebsiteRecruitBO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 招募分类(1讲师招募,2代理招募)
	 */
	@ApiModelProperty(value = "招募分类(1讲师招募,2代理招募)")
	private Integer recruitType;

}
