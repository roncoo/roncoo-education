package com.roncoo.education.course.service.auth.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * API-AUTH-专区
 * </p>
 *
 * @author wujing
 * @date 2022-08-25
 */
@Data
@Accessors(chain = true)
@Schema(description = "API-AUTH-专区")
public class AuthZoneReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "状态(1:正常;0:禁用)")
    private Integer statusId;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "名称")
    private String zoneName;

    @Schema(description = "描述")
    private String zoneDesc;
}
