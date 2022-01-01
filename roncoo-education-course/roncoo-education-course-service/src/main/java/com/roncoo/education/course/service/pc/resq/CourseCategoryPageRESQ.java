package com.roncoo.education.course.service.pc.resq;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 专区表-分页列出
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class CourseCategoryPageRESQ implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 状态(1:正常，0:禁用)
     */
    @ApiModelProperty(value = "状态(1:正常，0:禁用)")
    private Integer statusId;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;
    /**
     * 父分类ID
     */
    @ApiModelProperty(value = "父分类ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long parentId;
    /**
     * 分类类型(1课程，2资源)
     */
    @ApiModelProperty(value = "分类类型(1课程，2资源)")
    private Integer categoryType;
    /**
     * 分类名称
     */
    @ApiModelProperty(value = "分类名称")
    private String categoryName;
    /**
     * 层级
     */
    @ApiModelProperty(value = "层级")
    private Integer floor;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 课程分类集合
     */
    @ApiModelProperty(value = "课程分类集合")
    private List<CourseCategoryPageRESQ> children;
}
