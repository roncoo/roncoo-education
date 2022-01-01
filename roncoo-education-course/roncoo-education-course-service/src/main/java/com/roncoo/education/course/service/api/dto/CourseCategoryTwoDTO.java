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
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程分类
 * </p>
 *
 * @author wujing123
 */
@Data
@Accessors(chain = true)
public class CourseCategoryTwoDTO implements Serializable {

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
    @ApiModelProperty(value = "分类层级")
    private Integer floor;

    /**
     * 分类备注信息
     */
    @ApiModelProperty(value = "分类备注")
    private String remark;

    /**
     * 三级分类列表
     */
    @ApiModelProperty(value = "三级分类列表")
    private List<CourseCategoryThreeDTO> threeList = new ArrayList<>();

}
