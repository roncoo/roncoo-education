package com.roncoo.education.course.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 课程分类-添加
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class CourseCategorySaveREQ implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 父分类ID
     */
    @ApiModelProperty(value = "父分类ID", required = true)
    private Long parentId;
    /**
     * 分类类型(1课程)
     */
    @ApiModelProperty(value = "分类类型(1:课程)", required = true)
    private Integer categoryType;
    /**
     * 层级
     */
    @ApiModelProperty(value = "层级", required = true)
    private Integer floor;
    /**
     * 分类名称
     */
    @ApiModelProperty(value = "分类名称", required = true)
    private String categoryName;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = false)
    private String remark;

}
