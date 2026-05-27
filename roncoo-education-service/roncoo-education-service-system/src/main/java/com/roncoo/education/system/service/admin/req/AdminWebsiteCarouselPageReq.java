package com.roncoo.education.system.service.admin.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * ADMIN-广告信息
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@Schema(description = "ADMIN-广告信息分页")
public class AdminWebsiteCarouselPageReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "状态(1:正常，0:禁用)")
    private Integer statusId;

    @Schema(description = "广告跳转方式")
    private Integer carouselTarget;

    @Schema(description = "当前页")
    private int pageCurrent = 1;

    @Schema(description = "每页条数")
    private int pageSize = 20;
}
