package com.roncoo.education.system.service.api.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 头部导航
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class WebsiteNavResp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 导航标题
     */
    @ApiModelProperty(value = "导航标题")
    private String navTitle;
    /**
     * 导航url
     */
    @ApiModelProperty(value = "导航url")
    private String navUrl;
    /**
     * 跳转方式
     */
    @ApiModelProperty(value = "跳转方式")
    private String target;
}
