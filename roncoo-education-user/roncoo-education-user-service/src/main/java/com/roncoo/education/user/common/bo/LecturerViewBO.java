/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.user.common.bo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 讲师信息
 * </p>
 *
 * @author wujing123
 */
@Data
@Accessors(chain = true)
public class LecturerViewBO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 讲师编号
	 */
	@ApiModelProperty(value = "讲师用户编号", required = true)
	private Long lecturerUserNo;

}
