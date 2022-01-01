package com.roncoo.education.course.service.api.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 专区表
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class ZoneDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 专区编号
     */
    @ApiModelProperty(value = "专区编号")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;

    /**
     * 专区名称
     */
    @ApiModelProperty(value = "专区名称")
    private String zoneName;

    /**
     * 专区描述
     */
    @ApiModelProperty(value = "专区描述")
    private String zoneDesc;

    /**
     * 课程信息
     */
    @ApiModelProperty(value = "课程信息")
    private List<ZoneCourseDTO> zoneCourseList = new ArrayList<>();

}
