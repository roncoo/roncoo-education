package com.roncoo.education.system.service.api.resp;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 站点友情链接
 *
 * @author wuyun
 */
@Data
@Accessors(chain = true)
public class ApiWebsiteLinkResp implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @Schema(description = "主键id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long id;
    /**
     * 名称
     */
    @Schema(description = "名称")
    private String linkName;
    /**
     * 链接
     */
    @Schema(description = "链接")
    private String linkUrl;
    /**
     * 跳转方式(_blank，_self)
     */
    @Schema(description = "跳转方式")
    private Integer linkTarget;
}
