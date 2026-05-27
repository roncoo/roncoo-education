package com.roncoo.education.system.service.admin.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 站点友情链接-查看
 */
@Data
@Accessors(chain = true)
public class AdminWebsiteLinkViewReq implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @Schema(description = "主键id", required = true)
    private Long id;
}
