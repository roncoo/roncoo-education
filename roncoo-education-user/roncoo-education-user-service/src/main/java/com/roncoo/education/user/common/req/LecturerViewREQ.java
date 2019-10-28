package com.roncoo.education.user.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 讲师信息查看
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class LecturerViewREQ implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 主键
	 */
	@ApiModelProperty(value = "主键")
	private Long id;
	/**
	 * 讲师编号
	 */
	@ApiModelProperty(value = "讲师编号")
	private Long lecturerUserNo;
}
