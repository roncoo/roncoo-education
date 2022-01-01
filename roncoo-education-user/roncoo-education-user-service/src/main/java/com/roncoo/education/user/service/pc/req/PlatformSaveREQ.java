package com.roncoo.education.user.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 平台信息-添加
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
public class PlatformSaveREQ implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端名称
     */
    @ApiModelProperty(value = "客户端名称", required = true)
    private String clientName;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注", required = false)
    private String remark;
}
