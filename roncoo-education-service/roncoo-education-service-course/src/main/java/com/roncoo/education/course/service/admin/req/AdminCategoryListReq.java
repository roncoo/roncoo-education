package com.roncoo.education.course.service.admin.req;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "ADMIN-分类分页")
public class AdminCategoryListReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "状态(1:正常，0:禁用)")
    private Integer statusId;

    @Schema(description = "类型(1课程，2资源)")
    private Integer categoryType = 1;

    @Schema(description = "名称")
    private String categoryName;

    @Schema(description = "备注")
    private String remark;

}
