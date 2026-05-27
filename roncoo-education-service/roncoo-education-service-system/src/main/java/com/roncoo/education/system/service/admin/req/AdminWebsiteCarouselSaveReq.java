package com.roncoo.education.system.service.admin.req;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * ADMIN-广告信息
 * </p>
 *
 * @author wujing
 */
@Data
@Accessors(chain = true)
@Schema(description = "ADMIN-广告信息添加")
public class AdminWebsiteCarouselSaveReq implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @Schema(description = "状态(1:正常，0:禁用)")
    private Integer statusId;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "广告标题")
    private String carouselTitle;

    @Schema(description = "广告图片")
    private String carouselImg;

    @Schema(description = "广告链接")
    private String carouselUrl;

    @Schema(description = "广告跳转方式")
    private Integer carouselTarget;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "开始时间")
    private LocalDateTime beginTime;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "结束时间")
    private LocalDateTime endTime;
}
