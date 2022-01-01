package com.roncoo.education.system.service.pc.resq;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 站点导航文章-查看
 */
@Data
@Accessors(chain = true)
public class WebsiteNavArticleViewRESQ implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 导航ID
     */
    @ApiModelProperty(value = "导航ID")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long navId;
    /**
     * 文章标题
     */
    @ApiModelProperty(value = "文章标题")
    private String artTitle;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;
    /**
     * 文章图片
     */
    @ApiModelProperty(value = "文章图片")
    private String artPic;
    /**
     * 文章描述
     */
    @ApiModelProperty(value = "文章描述")
    private String artDesc;
}
