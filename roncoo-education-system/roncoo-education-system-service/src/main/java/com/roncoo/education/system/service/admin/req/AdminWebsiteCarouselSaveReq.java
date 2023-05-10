package com.roncoo.education.system.service.admin.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * ADMIN-广告信息
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "ADMIN-广告信息添加")
public class AdminWebsiteCarouselSaveReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "状态(1:正常，0:禁用)")
    private Integer statusId;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "广告标题")
    private String carouselTitle;

    @ApiModelProperty(value = "广告图片")
    private String carouselImg;

    @ApiModelProperty(value = "广告链接")
    private String carouselUrl;

    @ApiModelProperty(value = "广告跳转方式")
    private String carouselTarget;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "开始时间")
    private LocalDateTime beginTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endTime;
}
