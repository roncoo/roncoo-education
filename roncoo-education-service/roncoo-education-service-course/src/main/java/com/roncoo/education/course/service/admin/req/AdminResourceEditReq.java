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
@Schema(description = "ADMIN-课程视频信息修改")
public class AdminResourceEditReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "状态(1:正常，0:禁用)")
    private Integer statusId;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "分类ID")
    private Long categoryId;

    @Schema(description = "资源名称")
    private String resourceName;

    @Schema(description = "资源类型(1:视频2:音频;3:文档)")
    private Integer resourceType;

    @Schema(description = "资源大小")
    private Long resourceSize;

    @Schema(description = "资源地址")
    private String resourceUrl;

    @Schema(description = "状态(1转码中，2成功，3失败)")
    private Integer videoStatus;

    @Schema(description = "时长")
    private Integer videoLength;

    @Schema(description = "vid")
    private String videoVid;

    @Schema(description = "页数")
    private Integer docPage;

    @Schema(description = "图片宽度")
    private Integer imgWidth;

    @Schema(description = "图片高度")
    private Integer imgHeight;
}
