package com.roncoo.education.course.common.bo.auth;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程视频信息
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class AuthCourseVideoDeleteBO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 视频ID
	 */
	@ApiModelProperty(value = "视频ID", required = true)
	private Long id;

	/**
	 * 用户编号
	 */
	@ApiModelProperty(value = "用户编号", required = true)
	private Long userNo;

}
