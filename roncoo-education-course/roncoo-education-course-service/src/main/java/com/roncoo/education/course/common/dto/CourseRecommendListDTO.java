package com.roncoo.education.course.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 推荐课程集合
 *
 * @author kyh
 *
 */
@Data
@Accessors(chain = true)
public class CourseRecommendListDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 推荐课程集合
	 */
	@ApiModelProperty(value = "推荐课程集合")
	private List<CourseRecommendDTO> list = new ArrayList<>();

}
