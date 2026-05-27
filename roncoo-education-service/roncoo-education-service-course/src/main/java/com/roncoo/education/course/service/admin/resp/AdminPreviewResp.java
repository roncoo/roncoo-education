package com.roncoo.education.course.service.admin.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author fengyw
 */
@Data
@Accessors(chain = true)
@Schema(description = "Admin-预览配置")
public class AdminPreviewResp {

    @Schema(description = "资源ID")
    private Long id;

    @Schema(description = "播放vid")
    private String vid;

    @Schema(description = "点播平台")
    private Integer vodPlatform;

    @Schema(description = "点播播放配置参数")
    private String vodPlayConfig;

    @Schema(description = "文档学习配置参数")
    private String docStudyConfig;
}
