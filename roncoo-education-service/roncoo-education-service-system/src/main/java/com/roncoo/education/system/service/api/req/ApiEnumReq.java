package com.roncoo.education.system.service.api.req;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 站点导航文章
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class ApiEnumReq implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 文章ID
     */
    @ApiModelProperty(value = "枚举名称")
    private String enumName;
}
