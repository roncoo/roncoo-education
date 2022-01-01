package com.roncoo.education.system.service.pc.resq;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 底部导航-分页列出
 */
@Data
@Accessors(chain = true)
public class WebsiteNavPageRESQ implements Serializable {

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
     * 导航名称
     */
    @ApiModelProperty(value = "导航名称")
    private String navName;
    /**
     * 是否存在文章
     */
    @ApiModelProperty(value = "是否存在文章")
    private Integer isArticle;

    /**
     * 底部导航集合
     */
    @ApiModelProperty(value = "底部导航集合")
    private List<WebsiteNavPageRESQ> children;

}
