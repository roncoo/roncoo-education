package com.roncoo.education.system.service.api.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * API-广告信息
 * </p>
 *
 * @author wujing
 * @date 2022-09-16
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ApiWebsiteCarouselReq", description = "API-广告信息")
public class ApiWebsiteCarouselReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建时间")
    private LocalDateTime gmtCreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "修改时间")
    private LocalDateTime gmtModified;

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
