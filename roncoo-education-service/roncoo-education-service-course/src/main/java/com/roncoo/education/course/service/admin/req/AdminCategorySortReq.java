package com.roncoo.education.course.service.admin.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * 资源分类
 * </p>
 *
 * @author fengyw
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "ADMIN-排序")
public class AdminCategorySortReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "下级分类")
    private List<AdminCategorySortReq> childrenList;
}
