package com.roncoo.education.course.common.bean.dto.auth;

import java.io.Serializable;
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

	@ApiModelProperty(value = "视频id")
	List<AuthCourseVideoForListDTO> list;
}
