package com.roncoo.education.course.service.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 课程分类
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class CourseCategoryDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "分类编号", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 分类名称
     */
    @ApiModelProperty(value = "分类名称", required = true)
    private String categoryName;
    /**
     * 层级
     */
    @ApiModelProperty(value = "分类层级", required = true)
    private Integer floor;
    /**
     * 分类备注信息
     */
    @ApiModelProperty(value = "备注", required = true)
    private String remark;

    /**
     * 课程分类,二级分类列表
     */
    @ApiModelProperty(value = "二级分类信息列表", required = true)
    private List<CourseCategoryTwoDTO> twoList = new ArrayList<>();
}
