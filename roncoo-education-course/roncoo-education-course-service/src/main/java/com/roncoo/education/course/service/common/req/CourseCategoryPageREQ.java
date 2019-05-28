package com.roncoo.education.course.service.common.req;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 课程分类-分页列出
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class CourseCategoryPageREQ implements Serializable {

	private static final long serialVersionUID = 1L;
	/**
	 * 状态(1:正常，0:禁用)
	 */
	@ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
	private Integer statusId;
	/**
	 * 分类类型(1课程)
	 */
	@ApiModelProperty(value = "分类类型(1:课程)", required = false)
	private Integer categoryType;
	/**
	 * 分类名称
	 */
	@ApiModelProperty(value = "分类名称", required = false)
	private String categoryName;
	/**
	 * 层级
	 */
	@ApiModelProperty(value = "层级", required = false)
	private Integer floor;
	/**
	 * 当前页
	 */
	@ApiModelProperty(value = "当前页", required = true)
	private Integer pageCurrent = 1;

	/**
	 * 每页条数
	 */
	@ApiModelProperty(value = "每页条数", required = true)
	private Integer pageSize = 20;
}
