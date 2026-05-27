package com.roncoo.education.course.service.biz.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * ADMIN-讲师信息
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@Schema(description = "ADMIN-讲师信息查看")
public class CourseLecturerResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "讲师名称")
    private String lecturerName;

    @Schema(description = "讲师手机")
    private String lecturerMobile;

    @Schema(description = "讲师职位")
    private String lecturerPosition;

    @Schema(description = "讲师头像")
    private String lecturerHead;

    @Schema(description = "简介")
    private String introduce;
}
