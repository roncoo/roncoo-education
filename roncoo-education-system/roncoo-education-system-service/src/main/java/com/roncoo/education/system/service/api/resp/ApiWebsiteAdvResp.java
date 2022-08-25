package com.roncoo.education.system.service.api.resp;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * API-广告信息
 * </p>
 *
 * @author wujing
 * @date 2022-08-25
 */
@Data
@Accessors(chain = true)
@ApiModel(value = "ApiWebsiteAdvResp", description = "API-广告信息")
public class ApiWebsiteAdvResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    private Long id;

    @ApiModelProperty(value = "广告标题")
    private String advTitle;

    @ApiModelProperty(value = "广告图片")
    private String advImg;

    @ApiModelProperty(value = "广告链接")
    private String advUrl;

    @ApiModelProperty(value = "广告跳转方式")
    private String advTarget;
}
