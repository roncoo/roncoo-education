package com.roncoo.education.user.service.api.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * API-讲师信息
 * </p>
 *
 * @author fengyw
 */
@Data
@Accessors(chain = true)
@Schema(description = "API-讲师信息")
public class LecturerPageResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "讲师名称")
    private String lecturerName;

    @Schema(description = "讲师手机")
    private String lecturerMobile;

    @Schema(description = "讲师职位")
    private String lecturerPosition;

    @Schema(description = "讲师头像")
    private String lecturerHead;
}
