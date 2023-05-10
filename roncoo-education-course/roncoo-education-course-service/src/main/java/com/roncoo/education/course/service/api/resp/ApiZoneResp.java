package com.roncoo.education.course.service.api.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(description = "API-专区")
public class ApiZoneResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "名称")
    private String zoneName;

    @ApiModelProperty(value = "描述")
    private String zoneDesc;

    @ApiModelProperty(value = "课程信息")
    private List<ApiZoneCourseResp> courseList;
}
