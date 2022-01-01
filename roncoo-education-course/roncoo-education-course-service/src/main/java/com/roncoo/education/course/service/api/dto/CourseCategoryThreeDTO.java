/**
 * Copyright 2015-现在 广州市领课网络科技有限公司
 */
package com.roncoo.education.course.service.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/*
 * 课程三级分类
 */
@Data
@Accessors(chain = true)
public class CourseCategoryThreeDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 分类编号
     */
    @ApiModelProperty(value = "分类编号")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 分类名称
     */
    @ApiModelProperty(value = "分类名称")
    private String categoryName;
    /**
     * 层级
     */
    @ApiModelProperty(value = "分类名称")
    private Integer floor;

    /**
     * 分页备注信息
     */
    @ApiModelProperty(value = "分类备注信息")
    private String remark;

}
