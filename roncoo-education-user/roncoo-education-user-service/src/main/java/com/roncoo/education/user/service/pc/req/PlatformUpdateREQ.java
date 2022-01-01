package com.roncoo.education.user.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 平台信息-修改
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class PlatformUpdateREQ implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键", required = true)
    private Long id;

    /**
     * 状态(1:正常，0:禁用)
     */
    @ApiModelProperty(value = "状态(1:正常，0:禁用)", required = false)
    private Integer statusId;
    /**
     * 排序
     */
    @ApiModelProperty(value = "排序", required = false)
    private Integer sort;
    /**
     * 客户端ID
     */
    @ApiModelProperty(value = "客户端ID", required = false)
    private String clientId;
    /**
     * 客户端密匙
     */
    @ApiModelProperty(value = "客户端密匙", required = false)
    private String clientSecret;
    /**
     * 客户端名称
     */
    @ApiModelProperty(value = "客户端名称", required = false)
    private String clientName;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = false)
    private String remark;
}
