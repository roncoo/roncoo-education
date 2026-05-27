package com.roncoo.education.system.service.admin.req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 站点友情链接-修改
 */
@Data
@Accessors(chain = true)
public class AdminWebsiteLinkUpdateReq implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 主键
     */
    @Schema(description = "主键id", required = true)
    private Long id;
    /**
     * 状态(1有效, 0无效)
     */
    @Schema(description = "状态(1有效, 0无效)", required = false)
    private Integer statusId;
    /**
     * 排序
     */
    @Schema(description = "排序", required = false)
    private Integer sort;
    /**
     * 名称
     */
    @Schema(description = "名称", required = false)
    private String linkName;
    /**
     * 链接
     */
    @Schema(description = "链接", required = false)
    private String linkUrl;
    /**
     * 跳转方式(_blank，_self)
     */
    @Schema(description = "跳转方式(_blank，_self)", required = false)
    private Integer linkTarget;

}
