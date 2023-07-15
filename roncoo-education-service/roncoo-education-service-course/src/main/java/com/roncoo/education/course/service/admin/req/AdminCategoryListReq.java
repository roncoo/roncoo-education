package com.roncoo.education.course.service.admin.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * ADMIN-分类
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "ADMIN-分类分页")
public class AdminCategoryListReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "状态(1:正常，0:禁用)")
    private Integer statusId;

    @ApiModelProperty(value = "类型(1课程，2资源)")
    private Integer categoryType = 1;

    @ApiModelProperty(value = "名称")
    private String categoryName;

    @ApiModelProperty(value = "备注")
    private String remark;

}
