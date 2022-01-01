package com.roncoo.education.system.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 站点友情链接-添加
 */
@Data
@Accessors(chain = true)
public class WebsiteLinkSaveREQ implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称", required = true)
    private String linkName;
    /**
     * 链接
     */
    @ApiModelProperty(value = "链接", required = true)
    private String linkUrl;
    /**
     * 跳转方式(_blank，_self)
     */
    @ApiModelProperty(value = "跳转方式(_blank，_self)", required = true)
    private String linkTarget;

}
