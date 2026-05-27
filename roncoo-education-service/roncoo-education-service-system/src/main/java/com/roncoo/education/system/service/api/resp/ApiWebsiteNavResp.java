package com.roncoo.education.system.service.api.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * API-头部导航
 * </p>
 *
 * @author wujing
 * @date 2022-08-25
 */
@Data
@Accessors(chain = true)
@Schema(description = "API-头部导航")
public class ApiWebsiteNavResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "导航标题")
    private String navTitle;

    @Schema(description = "导航url")
    private String navUrl;

    @Schema(description = "跳转方式")
    private Integer navTarget;
}
