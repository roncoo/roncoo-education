package com.roncoo.education.system.service.pc.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 站点友情链接-删除
 */
@Data
@Accessors(chain = true)
public class WebsiteLinkDeleteREQ implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @ApiModelProperty(value = "主键id", required = true)
    private Long id;
}
