package com.roncoo.education.course.service.auth.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * API-AUTH-分类
 * </p>
 *
 * @author wujing
 * @date 2022-08-25
 */
@Data
@Accessors(chain = true)
@Schema(description = "API-AUTH-分类")
public class AuthCategoryReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "状态(1:正常，0:禁用)")
    private Integer statusId;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "父分类ID")
    private Long parentId;

    @Schema(description = "类型(1课程，2资源)")
    private Integer categoryType;

    @Schema(description = "名称")
    private String categoryName;

    @Schema(description = "备注")
    private String remark;
}
