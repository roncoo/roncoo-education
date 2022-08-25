package com.roncoo.education.system.service.api.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 站点导航
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class ApiWebsiteArticleResp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 状态(1有效, 0无效)
     */
    @ApiModelProperty(value = "状态(1有效, 0无效)")
    private Integer statusId;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序")
    private Integer sort;
    /**
     * 父ID
     */
    @ApiModelProperty(value = "父ID")
    private Long parentId;
    /**
     * 文章名称
     */
    @ApiModelProperty(value = "文章名称")
    private String artName;
    /**
     * 文章标题
     */
    @ApiModelProperty(value = "文章标题", required = true)
    private String artTitle;
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

    /**
     * 站点导航集合
     */
    @ApiModelProperty(value = "集合")
    private List<ApiWebsiteArticleResp> websiteArticleList = new ArrayList<>();
}
