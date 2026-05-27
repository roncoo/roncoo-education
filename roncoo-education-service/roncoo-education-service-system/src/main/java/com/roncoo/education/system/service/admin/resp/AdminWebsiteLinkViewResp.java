package com.roncoo.education.system.service.admin.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * ADMIN-站点友情链接
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@Schema(description = "ADMIN-站点友情链接查看")
public class AdminWebsiteLinkViewResp implements Serializable {

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

    @Schema(description = "名称")
    private String linkName;

    @Schema(description = "链接")
    private String linkUrl;

    @Schema(description = "跳转方式")
    private Integer linkTarget;
}
