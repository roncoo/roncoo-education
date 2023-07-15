package com.roncoo.education.system.service.api.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 站点友情链接
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class ApiWebsiteLinkResp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String linkName;
    /**
     * 链接
     */
    @ApiModelProperty(value = "链接")
    private String linkUrl;
    /**
     * 跳转方式(_blank，_self)
     */
    @ApiModelProperty(value = "跳转方式(_blank，_self)")
    private String linkTarget;
}
