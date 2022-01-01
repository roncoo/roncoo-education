package com.roncoo.education.system.service.pc.req;

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
public class WebsiteNavArticleUpdateREQ implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键id", required = true)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 文章标题
     */
    @ApiModelProperty(value = "文章标题", required = true)
    private String artTitle;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", required = true)
    private Integer sort;
    /**
     * 文章图片
     */
    @ApiModelProperty(value = "文章图片", required = true)
    private String artPic;
    /**
     * 文章描述
     */
    @ApiModelProperty(value = "文章描述", required = true)
    private String artDesc;
}
