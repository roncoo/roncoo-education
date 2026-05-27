package com.roncoo.education.course.service.admin.resp;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author fengyw
 */
@Data
@Accessors(chain = true)
@Schema(description = "Admin-上传音视频配置")
public class AdminVodConfigResp {

    @Schema(description = "使用平台")
    private Integer vodPlatform;

    @Schema(description = "视频云上传配置")
    private String vodUploadConfig;
}
