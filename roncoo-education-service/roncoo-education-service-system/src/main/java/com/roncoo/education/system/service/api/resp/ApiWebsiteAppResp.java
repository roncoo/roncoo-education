package com.roncoo.education.system.service.api.resp;

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
public class ApiWebsiteAppResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "是否需要更新(true:需要更新)")
    private Boolean needUpdate;

    @Schema(description = "app类型(1:Android，2:IOS)")
    private Integer appType;

    @Schema(description = "app版本")
    private String appVersion;

    @Schema(description = "是否强制更新(1不强制，2强制)")
    private Integer updateForce;

    @Schema(description = "app下载地址")
    private String updateUrl;

    @Schema(description = "更新内容")
    private String updateTips;
}
