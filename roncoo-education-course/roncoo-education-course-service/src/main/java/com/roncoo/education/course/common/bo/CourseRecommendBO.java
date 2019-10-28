package com.roncoo.education.course.common.bo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程推荐
 *
 * @author kyh
 *
 */
@Data
@Accessors(chain = true)
public class CourseRecommendBO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 分类ID
	 */
	@ApiModelProperty(value = "分类ID", required = true)
	private Long categoryId;

}
