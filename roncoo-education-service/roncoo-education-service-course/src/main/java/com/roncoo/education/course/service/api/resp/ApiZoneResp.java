package com.roncoo.education.course.service.api.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * API-专区
 * </p>
 *
 * @author wujing
 * @date 2022-08-25
 */
@Data
@Accessors(chain = true)
@Schema(description = "API-专区")
public class ApiZoneResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "名称")
    private String zoneName;

    @Schema(description = "描述")
    private String zoneDesc;

    @Schema(description = "课程信息")
    private List<ApiZoneCourseResp> courseList;
}
