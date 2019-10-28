package com.roncoo.education.course.common.bo.auth;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程信息-审核
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class AuthCourseAuditDeleteBO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 课程ID
	 */
	@ApiModelProperty(value = "课程ID")
	private Long id;
	/**
	 * 用户编号
	 */
	@ApiModelProperty(value = "用户编号")
	private Long userNo;
}
