package com.roncoo.education.course.service.admin.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * ADMIN-课程视频信息
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@Schema(description = "ADMIN-课程视频信息分页")
public class AdminResourcePageReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "资源类型(1:视频2:音频;3:文档)")
    private Integer resourceType;

    @Schema(description = "资源名称")
    private String resourceName;

    @Schema(description = "分类ID")
    private Long categoryId;

    @Schema(description = "当前页")
    private int pageCurrent = 1;

    @Schema(description = "每页条数")
    private int pageSize = 20;
}
