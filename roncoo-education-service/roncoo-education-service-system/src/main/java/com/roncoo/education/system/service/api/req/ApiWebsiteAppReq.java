package com.roncoo.education.system.service.api.req;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(description = "API-app版本管理")
public class ApiWebsiteAppReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "app类型(1:Android，2:IOS)")
    private Integer appType;

    @Schema(description = "app版本")
    private String appVersion;
}
