package com.roncoo.education.course.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 课程推荐-更新
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class CourseRecommendUpdateREQ implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", required = true)
    private Long id;

    /**
     * 状态(1:正常;0:禁用)
     */
    @ApiModelProperty(value = "状态(1:正常;0:禁用)", required = false)
    private Integer statusId;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", required = false)
    private Integer sort;

}
