package com.roncoo.education.system.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 站点友情链接-更新
 */
@Data
@Accessors(chain = true)
public class WebsiteLinkUpdateREQ implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键id", required = true)
    private Long id;
    /**
     * 状态(1有效, 0无效)
     */
    @ApiModelProperty(value = "状态(1有效, 0无效)", required = false)
    private Integer statusId;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", required = false)
    private Integer sort;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", required = false)
    private String linkName;
    /**
     * 链接
     */
    @ApiModelProperty(value = "链接", required = false)
    private String linkUrl;
    /**
     * 跳转方式(_blank，_self)
     */
    @ApiModelProperty(value = "跳转方式(_blank，_self)", required = false)
    private String linkTarget;

}
