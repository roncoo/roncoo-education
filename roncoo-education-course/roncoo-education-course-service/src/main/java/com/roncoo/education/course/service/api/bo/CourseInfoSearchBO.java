package com.roncoo.education.course.service.api.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class CourseInfoSearchBO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 课程名称
     */
    @ApiModelProperty(value = "课程名称")
    private String courseName;
    /**
     * 是否高亮(1高亮;0不高亮)
     */
    @ApiModelProperty(value = "是否高亮(1高亮;0不高亮)")
    private Integer isHfield;
    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页")
    private Integer pageCurrent = 1;
    /**
     * 每页条数
     */
    @ApiModelProperty(value = "每页条数")
    private Integer pageSize = 20;

}
