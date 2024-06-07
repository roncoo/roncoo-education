package com.roncoo.education.system.service.api.req;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * API-app版本管理
 * </p>
 *
 * @author fengyw
 * @date 2024-06-07
 */
@Data
@Accessors(chain = true)
@ApiModel(description = "API-app版本管理")
public class ApiWebsiteAppReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "app类型(1:Android，2:IOS)")
    private Integer appType;

    @ApiModelProperty(value = "app版本")
    private String appVersion;
}
