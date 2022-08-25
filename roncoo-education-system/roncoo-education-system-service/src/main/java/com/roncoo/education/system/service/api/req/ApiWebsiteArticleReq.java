package com.roncoo.education.system.service.api.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * API-站点导航文章
 *
 * @author wujing
 * @date 2022-08-25
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ApiWebsiteArticleReq", description = "API-站点导航文章")
public class ApiWebsiteArticleReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime gmtModified;

    @ApiModelProperty(value = "状态(1有效, 0无效)")
    private Integer statusId;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "父ID")
    private Long parentId;

    @ApiModelProperty(value = "文章名称")
    private String artName;

    @ApiModelProperty(value = "文章标题")
    private String artTitle;

    @ApiModelProperty(value = "文章图片")
    private String artPic;

    @ApiModelProperty(value = "文章描述")
    private String artDesc;
}
