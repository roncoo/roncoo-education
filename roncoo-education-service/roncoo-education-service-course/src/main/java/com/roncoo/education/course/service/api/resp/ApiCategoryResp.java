package com.roncoo.education.course.service.api.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * API-分类
 * </p>
 *
 * @author wujing
 * @date 2022-08-25
 */
@Data
@Accessors(chain = true)
@Schema(description = "API-分类")
public class ApiCategoryResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "父分类ID")
    private Long parentId;

    @Schema(description = "名称")
    private String categoryName;

    @Schema(description = "备注")
    private String remark;

    private List<ApiCategoryResp> list;
}
