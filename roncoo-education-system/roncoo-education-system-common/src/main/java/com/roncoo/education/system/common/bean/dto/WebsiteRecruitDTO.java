package com.roncoo.education.system.common.bean.dto;

import java.io.Serializable;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 招募中心
 *
 * @author YZJ
 */
@Data
@Accessors(chain = true)
public class WebsiteRecruitDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;
    /**
     * 招募分类(1讲师招募,2代理招募)
     */
    @ApiModelProperty(value = "招募分类(1讲师招募,2代理招募)")
    private Integer recruitType;
    /**
     * 招募标题
     */
    @ApiModelProperty(value = "招募标题")
    private String recruitTitle;
    /**
     * 招募信息
     */
    @ApiModelProperty(value = "招募信息")
    private String recruitInfo;
    /**
     * 入驻协议
     */
    @ApiModelProperty(value = "入驻协议")
    private String entryAgreement;
}
