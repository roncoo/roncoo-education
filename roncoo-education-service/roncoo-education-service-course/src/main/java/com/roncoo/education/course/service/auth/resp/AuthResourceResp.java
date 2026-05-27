package com.roncoo.education.course.service.auth.resp;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * API-AUTH-课程视频信息
 * </p>
 *
 * @author wujing
 * @date 2022-09-02
 */
@Data
@Accessors(chain = true)
@Schema(description = "API-AUTH-课程视频信息")
public class AuthResourceResp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "主键")
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "创建时间")
    private LocalDateTime gmtCreate;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Schema(description = "修改时间")
    private LocalDateTime gmtModified;

    @Schema(description = "状态(1:正常，0:禁用)")
    private Integer statusId;

    @Schema(description = "排序")
    private Integer sort;

    @Schema(description = "资源名称")
    private String resourceName;

    @Schema(description = "资源类型(1:视频2:音频;3:文档)")
    private Integer resourceType;

    @Schema(description = "资源大小")
    private Long resourceSize;

    @Schema(description = "资源地址")
    private String resourceUrl;

    @Schema(description = "状态(1转码中，2成功，3失败)")
    private Integer videoStatus;

    @Schema(description = "时长")
    private Integer videoLength;

    @Schema(description = "vid")
    private String videoVid;
}
