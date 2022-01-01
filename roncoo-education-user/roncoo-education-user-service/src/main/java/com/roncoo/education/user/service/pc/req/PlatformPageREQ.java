package com.roncoo.education.user.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 平台信息
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class PlatformPageREQ implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端名称
     */
    @ApiModelProperty(value = "客户端名称", required = false)
    private String clientName;
    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页", required = true)
    private int pageCurrent = 1;
    /**
     * 当前页
     */
    @ApiModelProperty(value = "当前页", required = true)
    private int pageSize = 20;
}
