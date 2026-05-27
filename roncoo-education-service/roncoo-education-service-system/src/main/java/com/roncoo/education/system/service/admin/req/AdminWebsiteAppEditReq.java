package com.roncoo.education.system.service.admin.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * ADMIN-app版本管理
 * </p>
 *
 * @author fengyw
 */
@Data
@Accessors(chain = true)
@Schema(description = "ADMIN-app版本管理修改")
public class AdminWebsiteAppEditReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private LocalDateTime gmtCreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "修改时间")
    private LocalDateTime gmtModified;

    @Schema(description = "状态(1有效, 0无效)")
    private Integer statusId;

    @Schema(description = "排序")
    private Integer sort;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "发布时间")
    private LocalDateTime publishTime;

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
