package com.roncoo.education.course.service.pc.resq;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 专区表-分页列出
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class ZonePageRESQ implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 专区ID
     */
    @ApiModelProperty(value = "专区ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 状态(1:正常;0:禁用)
     */
    @ApiModelProperty(value = "状态(1:正常;0:禁用)")
    private Integer statusId;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;
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
}
