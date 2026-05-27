package com.roncoo.education.course.service.admin.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * ADMIN-章节信息
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@Schema(description = "ADMIN-章节信息添加")
public class AdminCourseChapterSaveReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "状态(1:正常，0:禁用)")
    private Integer statusId;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "课程ID")
    private Long courseId;

    @Schema(description = "章节名称")
    private String chapterName;

    @Schema(description = "章节描述")
    private String chapterDesc;

    @Schema(description = "是否免费(1免费，0收费)")
    private Integer isFree;
}
