package com.roncoo.education.course.common.dto.auth;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
public class AuthCourseVideoListDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 课程视频集合信息
	 */
	@ApiModelProperty(value = "课程视频集合信息")
	List<AuthCourseVideoForListDTO> list = new ArrayList<>();
}
